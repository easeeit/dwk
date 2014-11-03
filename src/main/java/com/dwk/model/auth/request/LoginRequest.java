package com.dwk.model.auth.request;

/**
 * Request parameter for login.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class LoginRequest {

  private String userName;
  private String passwordHash;
  private String sourceName;
  private String sourceKey;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public String getSourceName() {
    return sourceName;
  }

  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
  }

  public String getSourceKey() {
    return sourceKey;
  }

  public void setSourceKey(String sourceKey) {
    this.sourceKey = sourceKey;
  }

}
