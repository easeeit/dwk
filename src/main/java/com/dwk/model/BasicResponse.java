package com.dwk.model;

import com.dwk.constant.APIConstant;

public class BasicResponse {
  
    private Integer code = APIConstant.RETURN_CODE_OK;
    public Integer getCode() {
      return code;
    }
    public void setCode(Integer code) {
      this.code = code;
    }
    
}
