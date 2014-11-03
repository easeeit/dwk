package com.dwk.model.auth.response;


public class AuthResponse extends LoginResponse {
  
  private Integer authStatus;

  public Integer getAuthStatus() {
    return authStatus;
  }

  public void setAuthStatus(Integer authStatus) {
    this.authStatus = authStatus;
  }
  
}
