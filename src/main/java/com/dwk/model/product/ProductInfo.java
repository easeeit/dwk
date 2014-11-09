package com.dwk.model.product;

import java.util.List;

import com.dwk.model.comment.CommentInfo;

public class ProductInfo extends Product {
  List<CommentInfo> comment;

  public List<CommentInfo> getComment() {
    return comment;
  }

  public void setComment(List<CommentInfo> comment) {
    this.comment = comment;
  }
}
