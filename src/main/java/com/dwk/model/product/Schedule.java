package com.dwk.model.product;

public class Schedule {
  private String id;
  private String product_id;
  private String version;
  private String timetomarket;
  private String status;
  private String delay_status;
  private String create_time;
  
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
  public String getTimetomarket() {
    return timetomarket;
  }
  public void setTimetomarket(String timetomarket) {
    this.timetomarket = timetomarket;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getCreate_time() {
    return create_time;
  }
  public void setCreate_time(String create_time) {
    this.create_time = create_time;
  }
  public String getVersion() {
    return version;
  }
  public void setVersion(String version) {
    this.version = version;
  }
  public String getDelay_status() {
    return delay_status;
  }
  public void setDelay_status(String delay_status) {
    this.delay_status = delay_status;
  }
}
