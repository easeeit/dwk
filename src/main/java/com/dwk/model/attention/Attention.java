package com.dwk.model.attention;

public class Attention {
  private String id;
  private String product_id;
  private String user_id;
  private Long create_time;
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
  public String getUser_id() {
    return user_id;
  }
  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }
  public Long getCreate_time() {
    return create_time;
  }
  public void setCreate_time(Long create_time) {
    this.create_time = create_time;
  }
}
