package com.dwk.model.message.request;

/**
 * Request parameter for send message.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class SendMessageRequest {

  private String targetUserID;
  private String title;
  private String subtitle;
  private String message;
  private String upperMsgID;

  public String getTargetUserID() {
    return targetUserID;
  }

  public void setTargetUserID(String targetUserID) {
    this.targetUserID = targetUserID;
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

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getUpperMsgID() {
    return upperMsgID;
  }

  public void setUpperMsgID(String upperMsgID) {
    this.upperMsgID = upperMsgID;
  }

}
