package com.dwk.model.user;

import com.lenovo.supernote.orm.cosure.Convertable;

public class BasicUserInfo {

  private String userID;
  private String name;
  private String nickName;
  private String avatar;
  private Double rpValue;

  public static final Convertable<BasicUserInfo, String> to_user_id = new Convertable<BasicUserInfo, String>() {
    @Override
    public String convert(BasicUserInfo q) {
      return q.getUserID();
    }
  };
  
  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Double getRpValue() {
    return rpValue;
  }

  public void setRpValue(Double rpValue) {
    this.rpValue = rpValue;
  }

}
