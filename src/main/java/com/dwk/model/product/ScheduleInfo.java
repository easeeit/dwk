package com.dwk.model.product;

public class ScheduleInfo extends Schedule {
  private String name;
  private String platform;
  private String surl;
  private Double score ;
  private Integer attention_count;
  private Integer laud_count;
  private Integer comment_count;
  
  public void copyInfo(Product p) {
    this.name = p.getName_cn();
    this.platform = p.getPlatform();
    this.surl = p.getSurl();
    this.score = p.getScore();
    this.attention_count = p.getAttention_count();
    this.laud_count = p.getLaud_count();
    this.comment_count = p.getComment_count();
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPlatform() {
    return platform;
  }
  public void setPlatform(String platform) {
    this.platform = platform;
  }
  public String getSurl() {
    return surl;
  }
  public void setSurl(String surl) {
    this.surl = surl;
  }
  public Double getScore() {
    return score;
  }
  public void setScore(Double score) {
    this.score = score;
  }
  public Integer getAttention_count() {
    return attention_count;
  }
  public void setAttention_count(Integer attention_count) {
    this.attention_count = attention_count;
  }
  public Integer getLaud_count() {
    return laud_count;
  }
  public void setLaud_count(Integer laud_count) {
    this.laud_count = laud_count;
  }
  public Integer getComment_count() {
    return comment_count;
  }
  public void setComment_count(Integer comment_count) {
    this.comment_count = comment_count;
  }
}
