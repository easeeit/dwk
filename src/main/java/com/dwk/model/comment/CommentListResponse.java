package com.dwk.model.comment;

import java.util.List;

import com.dwk.model.BasicResponse;

public class CommentListResponse extends BasicResponse {
  private List<Comment> comment;

  public List<Comment> getComment() {
    return comment;
  }

  public void setComment(List<Comment> comment) {
    this.comment = comment;
  }
  
}
