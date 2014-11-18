package com.dwk.model.article;

import java.util.List;

import com.dwk.model.BasicResponse;

public class ArticleListResponse extends BasicResponse {
  private List<ArticleList> article;

  public List<ArticleList> getArticle() {
    return article;
  }

  public void setArticle(List<ArticleList> article) {
    this.article = article;
  }

}
