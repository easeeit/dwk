package com.dwk.model.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.dwk.constant.DataConstant;
import com.dwk.model.product.Product;

public class ScheduleInfo extends Schedule {
  private String name;
  private String platform;
  private String surl;
  private Double score ;
  private Integer attention_count;
  private Integer laud_count;
  private Integer comment_count;
  private String sellTime;
  
  public void copyInfo(Product p) {
    this.name = p.getName_cn();
//    this.platform = Platform.valueToName(p.getPlatform());
    this.platform = p.getPlatform();
//    setStatus(ScheduleStatus.valueToName(getStatus()));
//    setDelay_status(DelayStatus.valueToName(getDelay_status()));
    this.sellTime = new SimpleDateFormat(DataConstant.DATE_FORMAT).format(new Date(getTimetomarket()));
//    setVersion(Version.valueToName(getVersion()));
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
  public String getSellTime() {
    return sellTime;
  }
  public void setSellTime(String sellTime) {
    this.sellTime = sellTime;
  }
}
