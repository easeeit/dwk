package com.dwk.model.laud;

/**
 * 点赞
 * @author xp
 *
 */
public class Laud {
  private String id;
  private String user_id;
  private String subject_id;
  private String subject_type;
  
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
  public String getSubject_id() {
    return subject_id;
  }
  public void setSubject_id(String subject_id) {
    this.subject_id = subject_id;
  }
  public String getSubject_type() {
    return subject_type;
  }
  public void setSubject_type(String subject_type) {
    this.subject_type = subject_type;
  }
}
