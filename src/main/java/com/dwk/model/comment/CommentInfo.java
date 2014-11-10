package com.dwk.model.comment;


public class CommentInfo {
  private String id ;
  private String content;
  private String user_id ;
  private String nickname ;
  private String logo_url ;
  private Integer laud_count ;
  private Long create_time ;
  private Long cluster ;
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getUser_id() {
    return user_id;
  }

  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getLogo_url() {
    return logo_url;
  }

  public void setLogo_url(String logo_url) {
    this.logo_url = logo_url;
  }

  public Integer getLaud_count() {
    return laud_count;
  }

  public void setLaud_count(Integer laud_count) {
    this.laud_count = laud_count;
  }

  public Long getCreate_time() {
    return create_time;
  }

  public void setCreate_time(Long create_time) {
    this.create_time = create_time;
  }

  public Long getCluster() {
    return cluster;
  }

  public void setCluster(Long cluster) {
    this.cluster = cluster;
  }
  
}
