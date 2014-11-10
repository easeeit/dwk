package com.dwk.model.user;

import com.dwk.constant.APIConstant;

public class UserInfo extends User {
  private Integer code = APIConstant.RETURN_CODE_OK;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
