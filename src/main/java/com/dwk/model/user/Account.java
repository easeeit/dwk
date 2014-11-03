package com.dwk.model.user;

/**
 * Account info.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class Account {

  private String id;
  private String phone;
  private String passwordHash;
  private String account;
  private Integer source;
  private String subSource;
  private String location;
  private Integer status;
  private Long createTime;
  private Long lastLoginTime;
  private Long lastOperateTime;

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

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public Integer getSource() {
    return source;
  }

  public void setSource(Integer source) {
    this.source = source;
  }

  public String getSubSource() {
    return subSource;
  }

  public void setSubSource(String subSource) {
    this.subSource = subSource;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
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

}
