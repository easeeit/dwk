package com.dwk.model.topic;

import com.dwk.constant.DataConstant;
import com.dwk.model.user.LoginUser;

/**
 * 话题
 * @author xp
 *
 */
public class Topic {
  private String id;
  private String title;
  private String content;
  private String status;
  private String platform;
  private String user_id;
  private String nickname;
  private String logo_url;
  private Integer laud_count;
  private Long create_time;
  
  public static Topic create(LoginUser user, String title, String platform, String content) {
    Topic t = new Topic();
    t.title = title;
    t.content = content;
    t.status = DataConstant.STATUS_ENABLE;
    t.platform = platform;
    t.user_id = user.getId();
    t.nickname = user.getNickName();
    t.logo_url = user.getLogoUrl();
    t.laud_count = 0;
    t.create_time = System.currentTimeMillis();
    return t;
  }
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
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getPlatform() {
    return platform;
  }
  public void setPlatform(String platform) {
    this.platform = platform;
  }
  public String getUser_id() {
    return user_id;
  }
  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }
  public Integer getLaud_count() {
    return laud_count;
  }
  public void setLaud_count(Integer laud_count) {
    this.laud_count = laud_count;
  }
  public Long getCreate_time() {
    return create_time;
  }
  public void setCreate_time(Long create_time) {
    this.create_time = create_time;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public String getLogo_url() {
    return logo_url;
  }
  public void setLogo_url(String logo_url) {
    this.logo_url = logo_url;
  }
}
