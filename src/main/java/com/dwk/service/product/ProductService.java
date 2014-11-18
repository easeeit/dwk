package com.dwk.service.product;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.dwk.constant.APIConstant;
import com.dwk.constant.ArticleType;
import com.dwk.dao.MongodbDao;
import com.dwk.model.article.ArticleListResponse;
import com.dwk.model.product.Product;
import com.dwk.model.product.ProductInfo;
import com.dwk.model.product.ProductOverview;
import com.dwk.model.product.commend.Commend;
import com.dwk.service.article.ArticleService;
import com.dwk.service.comment.CommentService;

/**
 * Product  service.
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
    List<Product> list = dao.selectList("getHotTopProduct", new HashMap<String,Object>(0), n, 0);
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
