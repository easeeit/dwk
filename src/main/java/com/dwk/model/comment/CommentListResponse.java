package com.dwk.model.comment;

import java.util.List;

import com.dwk.model.BasicResponse;

public class CommentListResponse extends BasicResponse {
  private List<CommentInfo> comment;

  public List<CommentInfo> getComment() {
    return comment;
  }

  public void setComment(List<CommentInfo> comment) {
    this.comment = comment;
  }

  
}
