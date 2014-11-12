package com.dwk.model.comment;

import com.dwk.constant.DataConstant;
import com.dwk.model.user.LoginUser;

public class Comment {
  private String id ;
  private String subject_type;
  private String subject_id;
  private String content;
  private String status ;
  private String user_id ;
  private String nickname ;
  private String logo_url ;
  private Integer laud_count ;
  private Integer comment_count ;
  private Integer hot ;
  private Long create_time ;
  private Long cluster ;
  
  public static Comment create(LoginUser user, String subjectID, String subjectType, String content, Long cluster) {
    long now = System.currentTimeMillis();
    Comment c = new Comment();
    c.subject_id = subjectID;
    c.subject_type = subjectType;
    c.content = content;
    c.status = DataConstant.STATUS_ENABLE;
    c.laud_count = 0;
    c.comment_count = 0;
    c.hot = 0;
    c.create_time = now;
    c.user_id = user.getId();
    c.nickname = user.getNickName();
    c.logo_url = user.getLogoUrl();
    if (cluster != null && cluster > 0) {
      c.cluster = cluster;
    } else {
      c.cluster = now;
    }
    return c;
  }
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSubject_type() {
    return subject_type;
  }

  public void setSubject_type(String subject_type) {
    this.subject_type = subject_type;
  }

  public String getSubject_id() {
    return subject_id;
  }

  public void setSubject_id(String subject_id) {
    this.subject_id = subject_id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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

  public Integer getHot() {
    return hot;
  }

  public void setHot(Integer hot) {
    this.hot = hot;
  }

  public Integer getComment_count() {
    return comment_count;
  }

  public void setComment_count(Integer comment_count) {
    this.comment_count = comment_count;
  }
  
}
