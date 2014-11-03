package com.dwk.model.auth.request;

/**
 * Request parameter for login.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class RegistRequest {

  private String phone;
  private String passwordHash;
  private String code;

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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

}
