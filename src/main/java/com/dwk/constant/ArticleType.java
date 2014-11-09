package com.dwk.constant;

public enum ArticleType {
  news("1"),
  tip("2");
  
  private String value;
  private ArticleType(String vaule) {
    this.value = vaule;
  }
  public String getValue() {
    return value;
  }
  
}
