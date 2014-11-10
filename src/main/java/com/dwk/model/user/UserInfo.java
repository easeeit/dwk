package com.dwk.model.user;

import com.dwk.constant.APIConstant;

public class UserInfo extends User {
  private Integer code = APIConstant.RETURN_CODE_OK;
  private String token;
  
  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }
  public void setCode(Integer code) {
    this.code = code;
  }
  public Integer getCode() {
    return code;
  }
}
