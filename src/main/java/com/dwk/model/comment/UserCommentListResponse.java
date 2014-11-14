package com.dwk.model.comment;

import java.util.List;

import com.dwk.model.BasicResponse;

public class UserCommentListResponse extends BasicResponse {
  private List<UserCommentInfo> comment;

  public List<UserCommentInfo> getComment() {
    return comment;
  }

  public void setComment(List<UserCommentInfo> comment) {
    this.comment = comment;
  }

  
}
