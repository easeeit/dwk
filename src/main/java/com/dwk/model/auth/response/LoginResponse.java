package com.dwk.model.auth.response;

import com.dwk.model.BasicResponse;
import com.dwk.model.user.BasicUserInfo;

public class LoginResponse extends BasicResponse {

  private String authToken;

  private BasicUserInfo userInfo;

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  public BasicUserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(BasicUserInfo userInfo) {
    this.userInfo = userInfo;
  }

}
