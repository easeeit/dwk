package com.dwk.model.friend;

import com.lenovo.supernote.orm.cosure.Convertable;

/**
 * Friend ship.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class FriendShip {
  
  private String id;
  private String requestUserID;
  private String requestUserPhone;
  private String requestMessage;
  private String targetUserID;
  private String targetUserPhone;
  private Integer status;
  private Long requestTime;
  private Long acceptionTime;

  public static final Convertable<FriendShip, String> to_request_id = new Convertable<FriendShip, String>() {
    @Override
    public String convert(FriendShip q) {
      return q.getRequestUserID();
    }
  };
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRequestUserID() {
    return requestUserID;
  }

  public void setRequestUserID(String requestUserID) {
    this.requestUserID = requestUserID;
  }

  public String getRequestUserPhone() {
    return requestUserPhone;
  }

  public void setRequestUserPhone(String requestUserPhone) {
    this.requestUserPhone = requestUserPhone;
  }

  public String getRequestMessage() {
    return requestMessage;
  }

  public void setRequestMessage(String requestMessage) {
    this.requestMessage = requestMessage;
  }

  public String getTargetUserID() {
    return targetUserID;
  }

  public void setTargetUserID(String targetUserID) {
    this.targetUserID = targetUserID;
  }

  public String getTargetUserPhone() {
    return targetUserPhone;
  }

  public void setTargetUserPhone(String targetUserPhone) {
    this.targetUserPhone = targetUserPhone;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getRequestTime() {
    return requestTime;
  }

  public void setRequestTime(Long requestTime) {
    this.requestTime = requestTime;
  }

  public Long getAcceptionTime() {
    return acceptionTime;
  }

  public void setAcceptionTime(Long acceptionTime) {
    this.acceptionTime = acceptionTime;
  }

}
