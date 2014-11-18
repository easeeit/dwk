package com.dwk.model.product.commend;

/**
 * 推荐表
 * @author xp
 *
 */
public class Commend {
  private String id;
  private String product_id;
  private Long create_time;
  private Integer week;
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getProduct_id() {
    return product_id;
  }
  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }
  public Long getCreate_time() {
    return create_time;
  }
  public void setCreate_time(Long create_time) {
    this.create_time = create_time;
  }
  public Integer getWeek() {
    return week;
  }
  public void setWeek(Integer week) {
    this.week = week;
  }
  
}
