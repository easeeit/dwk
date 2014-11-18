package com.dwk.model.article;

import java.util.List;

import com.dwk.constant.APIConstant;
import com.dwk.model.comment.CommentInfo;

public class ArticleInfoResponse {
  private Integer code = APIConstant.RETURN_CODE_OK;
  private String product_id;
  private String product_name;
  private String id;
  private List<CommentInfo> comment;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public Integer getCode() {
    return code;
  }
  public void setCode(Integer code) {
    this.code = code;
  }
  public String getProduct_id() {
    return product_id;
  }
  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }
  public List<CommentInfo> getComment() {
    return comment;
  }
  public void setComment(List<CommentInfo> comment) {
    this.comment = comment;
  }
  public String getProduct_name() {
    return product_name;
  }
  public void setProduct_name(String product_name) {
    this.product_name = product_name;
  }
}
