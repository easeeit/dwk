package com.dwk.service.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwk.dao.MongodbDao;
import com.dwk.model.article.Article;
import com.dwk.model.article.ArticleListResponse;

/**
 * User product deal service.
 * @author: xiangping_yu
 * @data : 2014-9-1
 * @since : 1.5
 */
@Service("ArticleService")
public class ArticleService {

  @Autowired
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
}
