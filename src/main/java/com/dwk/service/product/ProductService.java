package com.dwk.service.product;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.dwk.constant.APIConstant;
import com.dwk.constant.ArticleType;
import com.dwk.constant.DataConstant;
import com.dwk.dao.MongodbDao;
import com.dwk.model.article.ArticleListResponse;
import com.dwk.model.product.Product;
import com.dwk.model.product.ProductInfo;
import com.dwk.model.product.ProductOverview;
import com.dwk.model.product.commend.Commend;
import com.dwk.model.product.score.ScoreResponse;
import com.dwk.service.article.ArticleService;
import com.dwk.service.comment.CommentService;
import com.dwk.util.NumberUtil;

/**
 * Product service.
 * 
 * @author: xp
 */
public class ProductService {

  private MongodbDao dao;
  private CommentService commentService;
  private ArticleService articleService;

  // TODO 缓存
  public ProductInfo getProduct(String productID) {
    ProductInfo p = null;
    if (StringUtils.isBlank(productID)) {
      p = new ProductInfo();
      p.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return p;
    }

    p = dao.selectOne("getProductByID", productID);
    if (p != null) {
      p.setComment(commentService.getHotComment(productID));
    } else {
      p = new ProductInfo();
      p.setCode(APIConstant.RETURN_CODE_DATA_NOT_FOUND);
    }
    return p;
  }

  // TODO 缓存
  public ArticleListResponse getNewsList(String productID, int pn, int rn) {
    ArticleListResponse res = new ArticleListResponse();
    res.setArticle(articleService.getListByProductID(productID, ArticleType.news, pn, rn));
    return res;
  }

  // TODO 缓存
  public ArticleListResponse getTipList(String productID, int pn, int rn) {
    ArticleListResponse res = new ArticleListResponse();
    res.setArticle(articleService.getListByProductID(productID, ArticleType.tip, pn, rn));
    return res;
  }

  /**
   * 周推荐
   * 
   * @return
   */
  // TODO cache
  public ProductOverview getWeekCommend() {
    ProductOverview pView = null;
    System.out.println(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
    Commend list = dao.selectOne("getWeekCommendProduct", Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
    if (list != null) {
      ProductInfo p = getProduct(list.getProduct_id());
      pView = ProductOverview.parse(p);
    }
    return pView;
  }

  // cache
  public List<ProductOverview> getHotTopN(int n) {
    List<ProductOverview> topList = null;
    List<Product> list = dao.selectList("getHotTopProduct", new HashMap<String, Object>(0), n, 0);
    if (!CollectionUtils.isEmpty(list)) {
      topList = new ArrayList<ProductOverview>(list.size());
      for (Product p : list) {
        if (p != null) {
          topList.add(ProductOverview.parse(p));
        }
      }
    }
    return topList;
  }

  public ScoreResponse score(String productID, Double score) {
    ScoreResponse res = new ScoreResponse();
    if (StringUtils.isBlank(productID) || score == null || score < DataConstant.SCORE_LIMIT_LOWER || score > DataConstant.SCORE_LIMIT_UPPER) {
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    // 获得当前评分
    ProductInfo productInfo = getProduct(productID);
    if (productInfo.getCode() != APIConstant.RETURN_CODE_OK) {
      res.setCode(productInfo.getCode());
      return res;
    }
    // 计算平均值
    Integer count = productInfo.getScore_time();
    count = count == null ? 0 : count;
    Double oldScore = productInfo.gScore_4();
    oldScore = oldScore == null ? 0D : oldScore;
    double newScore_4 = (oldScore * count + score) / (count + 1) * 1.0D;
    // 四舍五入
    newScore_4 = NumberUtil.digit(newScore_4, 4);
    double newScore = NumberUtil.digit(newScore_4, 1);
    // 更新新的分数和评分人数
    Map<String,Object> map = new HashMap<String, Object>(2);
    map.put("productID", productID);
    map.put("score_4", newScore_4);
    map.put("score", newScore);
    int rowCount = dao.update("updateProductScore", map);
    if (rowCount != 1) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    }
    return res;
  }

  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }

  public void setCommentService(CommentService commentService) {
    this.commentService = commentService;
  }

  public void setArticleService(ArticleService articleService) {
    this.articleService = articleService;
  }

}
