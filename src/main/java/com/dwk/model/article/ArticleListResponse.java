package com.dwk.model.article;

import java.util.List;

import com.dwk.model.BasicResponse;

public class ArticleListResponse extends BasicResponse {
  private List<Article> article;

  public List<Article> getArticle() {
    return article;
  }

  public void setArticle(List<Article> article) {
    this.article = article;
  }
  
}
