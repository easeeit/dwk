package com.dwk.model.product;

import java.util.List;

import com.dwk.constant.APIConstant;
import com.dwk.model.comment.CommentInfo;

public class ProductInfo extends Product {
  private Integer code = APIConstant.RETURN_CODE_OK;
  private List<CommentInfo> comment;
  
  public Integer getCode() {
    return code;
  }
  public void setCode(Integer code) {
    this.code = code;
  };
  
  public List<CommentInfo> getComment() {
    return comment;
  }

  public void setComment(List<CommentInfo> comment) {
    this.comment = comment;
  }
}
