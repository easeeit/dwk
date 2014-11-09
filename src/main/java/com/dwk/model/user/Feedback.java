package com.dwk.model.user;

public class Feedback {
  private String id;
  private String user_id;
  private String contact;
  private String content;
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getUser_id() {
    return user_id;
  }
  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }
  public String getContact() {
    return contact;
  }
  public void setContact(String contact) {
    this.contact = contact;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
}
