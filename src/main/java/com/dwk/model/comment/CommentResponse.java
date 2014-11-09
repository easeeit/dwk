package com.dwk.model.comment;

import com.dwk.model.BasicResponse;

public class CommentResponse extends BasicResponse {
  private String id;
  private Long cluster;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getCluster() {
    return cluster;
  }

  public void setCluster(Long cluster) {
    this.cluster = cluster;
  }

  
}
