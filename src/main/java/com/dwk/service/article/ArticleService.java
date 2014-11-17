package com.dwk.service.article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.dwk.constant.APIConstant;
import com.dwk.constant.ArticleType;
import com.dwk.dao.MongodbDao;
import com.dwk.model.article.Article;
import com.dwk.model.article.ArticleInfoResponse;
import com.dwk.model.article.ArticleListResponse;
import com.dwk.service.comment.CommentService;

/**
 * Article service.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class ArticleService {

  private MongodbDao dao;
  private CommentService commentService;

  /**
   * @param pn
   * @param rn
   * @return
   */
  // TODO cache
  public ArticleListResponse list(int pn, int rn) {
    ArticleListResponse result = new ArticleListResponse();
    List<Article> articles = dao.selectList("getArticleList", null, rn, (pn - 1) * rn);
    // hot comment
    for (Article art : articles) {
      art.setComment(commentService.getHotComment(art.getId()));
    }

    result.setArticle(articles);
    return result;
  }
  
  public ArticleInfoResponse info(String articleID) {
    ArticleInfoResponse res = null;
    if (StringUtils.isBlank(articleID)) {
      res = new ArticleInfoResponse();
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    res = dao.selectOne("getArticleByID", articleID);
    if (res == null) {
      res = new ArticleInfoResponse();
      res.setCode(APIConstant.RETURN_CODE_DATA_NOT_FOUND);
      return res;
    }
    return res;
  }
  
  // TODO cache
  public List<Article> getListByProductID(String productID, ArticleType type, int pn, int rn) {
    if (StringUtils.isBlank(productID) || type == null) {
      return null;
    }
    Map<String, Object> map = new HashMap<String, Object>(2);
    map.put("productID", productID);
    map.put("type", type.getValue());
    List<Article> articles = dao.selectList("getArticleListByProductID", map, rn, (pn - 1) * rn);
    return articles;
  }

  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }

  public void setCommentService(CommentService commentService) {
    this.commentService = commentService;
  }

}
