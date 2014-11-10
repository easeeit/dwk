package com.dwk.service.product;

import java.util.ArrayList;
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
import com.dwk.model.product.commend.Commend;
import com.dwk.model.product.commend.CommendListResponse;
import com.dwk.model.product.commend.CommendProduct;
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
  public CommendListResponse getCommend() {
    CommendListResponse res = new CommendListResponse();
    List<Commend> list = dao.selectList("getCommendProduct", null, 1, 0);
    if (!CollectionUtils.isEmpty(list)) {
      List<CommendProduct> info = new ArrayList<CommendProduct>(list.size());
      for (Commend commend : list) {
        if (commend != null) {
          ProductInfo p = getProduct(commend.getProduct_id());
          info.add(CommendProduct.parse(p));
        }
      }
    }
    return res;
  }
  
  public CommendListResponse getHotTopN(int n) {
    CommendListResponse res = new CommendListResponse();
    List<Product> list = dao.selectList("getHotTopProduct", new HashMap<String,Object>(0), n, 0);
    if (!CollectionUtils.isEmpty(list)) {
      List<CommendProduct> info = new ArrayList<CommendProduct>(list.size());
      for (Product p : list) {
        if (p != null) {
          info.add(CommendProduct.parse(p));
        }
      }
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
