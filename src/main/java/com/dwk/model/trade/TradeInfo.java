package com.dwk.model.trade;

import com.dwk.constant.APIConstant;

public class TradeInfo extends Trade {

  private Integer code = APIConstant.RETURN_CODE_OK;
  public Integer getCode() {
    return code;
  }
  public void setCode(Integer code) {
    this.code = code;
  }
  
}
