package com.dwk.service.article;

import java.util.List;

import com.dwk.dao.MongodbDao;
import com.dwk.model.article.Article;
import com.dwk.model.article.ArticleListResponse;

/**
 * Article service.
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class ArticleService {

  private MongodbDao dao;
  /**
   * @param pn 
   * @param rn
   * @return
   */
  public ArticleListResponse list(int pn, int rn) {
    ArticleListResponse result = new ArticleListResponse();
    List<Article> articles =dao.selectList("getArticleList", null, rn, (pn -1) * rn);
    result.setArticle(articles);
    return result;
  }
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }
  
}
