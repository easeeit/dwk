package com.dwk.model.message;

public class MessageInfo {

  private String id;
  private String title;
  private String subtitle;
  private String msgFromUserID;
  private String msgFromUserName;
  private String message;
  private String upperMessageID;
  private String rootMessageID;
  private Long sendTime;
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  public String getMsgFromUserID() {
    return msgFromUserID;
  }

  public void setMsgFromUserID(String msgFromUserID) {
    this.msgFromUserID = msgFromUserID;
  }

  public String getMsgFromUserName() {
    return msgFromUserName;
  }

  public void setMsgFromUserName(String msgFromUserName) {
    this.msgFromUserName = msgFromUserName;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getUpperMessageID() {
    return upperMessageID;
  }

  public void setUpperMessageID(String upperMessageID) {
    this.upperMessageID = upperMessageID;
  }

  public String getRootMessageID() {
    return rootMessageID;
  }

  public void setRootMessageID(String rootMessageID) {
    this.rootMessageID = rootMessageID;
  }

  public Long getSendTime() {
    return sendTime;
  }

  public void setSendTime(Long sendTime) {
    this.sendTime = sendTime;
  }

}
