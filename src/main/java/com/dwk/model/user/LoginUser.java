package com.dwk.model.user;

import java.io.Serializable;

/**
 * Current login user.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class LoginUser implements Serializable {

  private static final long serialVersionUID = -183549504329128133L;
  
  private String id;
  private String name;
  private String nickName;
  private Integer sex;
  private String accountID;
  private String avatar;
  private Double rpValue;
  private String userPhone;
  private Long lastLoginTime;
  private Long lastOperateTime;
  private String logoUrl;
  
  private String authToken;
  private Integer score;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public String getAccountID() {
    return accountID;
  }

  public void setAccountID(String accountID) {
    this.accountID = accountID;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Double getRpValue() {
    return rpValue;
  }

  public void setRpValue(Double rpValue) {
    this.rpValue = rpValue;
  }

  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }

  public Long getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(Long lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  public Long getLastOperateTime() {
    return lastOperateTime;
  }

  public void setLastOperateTime(Long lastOperateTime) {
    this.lastOperateTime = lastOperateTime;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  public String getLogoUrl() {
    return logoUrl;
  }

  public void setLogoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }
  
}
