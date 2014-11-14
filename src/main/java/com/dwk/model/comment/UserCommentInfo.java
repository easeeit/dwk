package com.dwk.model.comment;


public class UserCommentInfo {
  private String id ;
  private String subject_type;
  private String subject_id;
  private String content;
  private String user_id ;
  private String nickname ;
  private String logo_url ;
  private Long create_time ;
  private String p_content ;
  
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

  public Long getCreate_time() {
    return create_time;
  }

  public void setCreate_time(Long create_time) {
    this.create_time = create_time;
  }

  public String getP_content() {
    return p_content;
  }

  public void setP_content(String p_content) {
    this.p_content = p_content;
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

  
}
