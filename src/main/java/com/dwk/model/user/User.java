package com.dwk.model.user;

/**
 * User info.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class User {

  private String id;
  private String phone;
  private String name;
  private String nickName;
  private Integer sex;
  private String accountID;
  private String avatar;
  private Double rpValue;
  private Integer status;
  private Long createTime;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
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

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

}
