package com.dwk.model.article;

import com.dwk.constant.APIConstant;

public class ArticleInfoResponse extends Article {
  private Integer code = APIConstant.RETURN_CODE_OK;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }
}
