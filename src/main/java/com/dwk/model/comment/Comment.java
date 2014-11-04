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
  private Long create_time ;
  private String parent_id ;
  private String user_ip ;
  private String user_location ;
  
  public static Comment create(LoginUser user, String subjectID, String subjectType, String content, String parentID) {
    Comment c = new Comment();
    c.subject_id = subjectID;
    c.subject_type = subjectType;
    c.content = content;
    c.status = DataConstant.STATUS_ENABLE;
    c.parent_id = parentID;
    c.laud_count = 0;
    c.create_time = System.currentTimeMillis();
    c.user_id = user.getId();
    c.nickname = user.getNickName();
    c.logo_url = user.getLogoUrl();
    c.user_ip = null; //TODO
    c.user_location = null; // TODO
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

  public String getParent_id() {
    return parent_id;
  }

  public void setParent_id(String parent_id) {
    this.parent_id = parent_id;
  }

  public String getUser_ip() {
    return user_ip;
  }

  public void setUser_ip(String user_ip) {
    this.user_ip = user_ip;
  }

  public String getUser_location() {
    return user_location;
  }

  public void setUser_location(String user_location) {
    this.user_location = user_location;
  }
  
}
