package com.dwk.model.friend.request;

public class InviteInfo {

  private String addUserPhoneNum;
  private String addUserSign;
  private Integer signType;

  public String getAddUserPhoneNum() {
    return addUserPhoneNum;
  }

  public void setAddUserPhoneNum(String addUserPhoneNum) {
    this.addUserPhoneNum = addUserPhoneNum;
  }

  public String getAddUserSign() {
    return addUserSign;
  }

  public void setAddUserSign(String addUserSign) {
    this.addUserSign = addUserSign;
  }

  public Integer getSignType() {
    return signType;
  }

  public void setSignType(Integer signType) {
    this.signType = signType;
  }

}
