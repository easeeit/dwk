package com.dwk.model.auth;

import java.io.Serializable;

import com.dwk.constant.SystemConstant;

public class VerificationCode implements Serializable {

  private static final long serialVersionUID = -5878096295795208788L;

  private String code;
  private long sendTime;

  public boolean isTimeOut() {
    return (System.currentTimeMillis() - sendTime) > SystemConstant.VERIFICATION_CODE_RESEND_TIME;
  }
  
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public long getSendTime() {
    return sendTime;
  }

  public void setSendTime(long sendTime) {
    this.sendTime = sendTime;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
