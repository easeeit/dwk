package com.dwk.model.user;

/**
 * User info.
 * 
 * @author: xp
 */
public class User {

  private String id;
  private String openid;
  private String nickname;
  private String source;
  private Integer score;
  private String phone;
  private String status;
  private String email;
  private String signature;
  private String logo_url;
  private Long create_time;
  private Long last_login_time;
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getOpenid() {
    return openid;
  }
  public void setOpenid(String openid) {
    this.openid = openid;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public String getSource() {
    return source;
  }
  public void setSource(String source) {
    this.source = source;
  }
  public Integer getScore() {
    return score;
  }
  public void setScore(Integer score) {
    this.score = score;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getSignature() {
    return signature;
  }
  public void setSignature(String signature) {
    this.signature = signature;
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
  public Long getLast_login_time() {
    return last_login_time;
  }
  public void setLast_login_time(Long last_login_time) {
    this.last_login_time = last_login_time;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

}
