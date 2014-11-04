package com.dwk.common.sms;

import com.dwk.common.sms.EmaySmsPlatform.ResponseCode;

/**
 * Emay sms response model
 * 
 * @author: xp
 * @data : 2014-4-4
 * @since : 1.5
 */
public class EmaySmsResponseModel {

  private int code;
  private String msg;

  public boolean success() {
    return ResponseCode.ok.getCode() == code;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  /**
   * Emay receive sms response model
   */
  public static class EmaySmsReceiveResponseModel extends EmaySmsResponseModel {

  }
}
