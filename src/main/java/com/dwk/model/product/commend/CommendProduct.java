package com.dwk.model.product.commend;

import com.dwk.model.product.Product;

/**
 * api:推荐产品
 * @author xp
 *
 */
public class CommendProduct {
  private String id;
  private String name;
  private String surl;
  private Double score ;
  private Integer attention_count;
  private Integer laud_count;
  private Integer comment_count;
  
  public static CommendProduct parse(Product p) {
    if (p == null) {
      return null;
    }
    CommendProduct cp = new CommendProduct();
    cp.setAttention_count(p.getAttention_count());
    cp.setComment_count(p.getComment_count());
    cp.setId(p.getId());
    cp.setLaud_count(p.getLaud_count());
    cp.setName(p.getName_cn());
    cp.setScore(p.getScore());
    cp.setSurl(p.getSurl());
    return cp;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
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
