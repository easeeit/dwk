package com.dwk.model.message;


/**
 * Message info.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class Message {

  private String id;
  private String fromUserID;
  private String targetUserID;
  private String title;
  private String subtitle;
  private String rootID;
  private String upperID;
  private Integer deepth;
  private String content;
  private Integer status;
  private Long sendTime;
  private Long readTime;

  private MessageAction action;

  public static class MessageAction {
    private String url;

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFromUserID() {
    return fromUserID;
  }

  public void setFromUserID(String fromUserID) {
    this.fromUserID = fromUserID;
  }

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

  public String getRootID() {
    return rootID;
  }

  public void setRootID(String rootID) {
    this.rootID = rootID;
  }

  public String getUpperID() {
    return upperID;
  }

  public void setUpperID(String upperID) {
    this.upperID = upperID;
  }

  public Integer getDeepth() {
    return deepth;
  }

  public void setDeepth(Integer deepth) {
    this.deepth = deepth;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getSendTime() {
    return sendTime;
  }

  public void setSendTime(Long sendTime) {
    this.sendTime = sendTime;
  }

  public Long getReadTime() {
    return readTime;
  }

  public void setReadTime(Long readTime) {
    this.readTime = readTime;
  }

  public MessageAction getAction() {
    return action;
  }

  public void setAction(MessageAction action) {
    this.action = action;
  }

}
