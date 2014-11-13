package com.dwk.model.trade;

import java.util.Map;

import com.dwk.constant.DataConstant;
import com.dwk.model.user.LoginUser;

public class Trade {
  private String id;
  private String product_name;
  private String status;
  private String platform;
  private String version;
  private String language;
  private String integrity;
  private Double price;
  private String haggle;
  private String exchange;
  private String description;
  private String remark;
  private String user_id;
  private String contact ;
  private String area;
  private String photo_url;
  private Integer comment_count;
  private Long create_time;
  private Long update_time;
  
  public static Trade create(LoginUser user, Map<String, Object> map) {
    Trade t = new Trade();
    t.create_time = System.currentTimeMillis();
    if (map.containsKey("area")) {
      t.area = ((String[])map.get("area"))[0];
    }
    if (map.containsKey("description")) {
      t.description = ((String[])map.get("description"))[0];
    }
    if (map.containsKey("integrity")) {
      t.integrity = ((String[])map.get("integrity"))[0];
    }
    if (map.containsKey("language")) {
      t.language = ((String[])map.get("language"))[0];
    }
    if (map.containsKey("platform")) {
      t.platform = ((String[])map.get("platform"))[0];
    }
    if (map.containsKey("price")) {
      t.price = Double.parseDouble(((String[])map.get("price"))[0]);
    }
    if (map.containsKey("product_name")) {
      t.product_name = ((String[])map.get("product_name"))[0];
    }
    if (map.containsKey("remark")) {
      t.remark = ((String[])map.get("remark"))[0];
    }
    if (map.containsKey("version")) {
      t.version = ((String[])map.get("version"))[0];
    }
    if (map.containsKey("contact")) {
      t.contact = ((String[])map.get("contact"))[0];
    }
    if (map.containsKey("exchange")) {
      t.exchange = ((String[])map.get("exchange"))[0];
    }
    if (map.containsKey("haggle")) {
      t.haggle = ((String[])map.get("haggle"))[0];
    }
    if (map.containsKey("photo_url")) {
      t.photo_url = ((String[])map.get("photo_url"))[0];
    }
    t.status = DataConstant.STATUS_ENABLE;
    t.user_id = user.getId();
    t.comment_count = 0;
    return t;
  }
  
  public static Trade update(LoginUser user, Map<String, Object> map) {
    Trade t = new Trade();
    t.update_time = System.currentTimeMillis();
    if (map.containsKey("id")) {
      t.id = ((String[])map.get("id"))[0];
    }
    if (map.containsKey("area")) {
      t.area = ((String[])map.get("area"))[0];
    }
    if (map.containsKey("description")) {
      t.description = ((String[])map.get("description"))[0];
    }
    if (map.containsKey("integrity")) {
      t.integrity = ((String[])map.get("integrity"))[0];
    }
    if (map.containsKey("language")) {
      t.language = ((String[])map.get("language"))[0];
    }
    if (map.containsKey("platform")) {
      t.platform = ((String[])map.get("platform"))[0];
    }
    if (map.containsKey("price")) {
      t.price = Double.parseDouble(((String[])map.get("price"))[0]);
    }
    if (map.containsKey("product_name")) {
      t.product_name = ((String[])map.get("product_name"))[0];
    }
    if (map.containsKey("remark")) {
      t.remark = ((String[])map.get("remark"))[0];
    }
    if (map.containsKey("version")) {
      t.version = ((String[])map.get("version"))[0];
    }
    if (map.containsKey("contact")) {
      t.contact = ((String[])map.get("contact"))[0];
    }
    if (map.containsKey("exchange")) {
      t.exchange = ((String[])map.get("exchange"))[0];
    }
    if (map.containsKey("haggle")) {
      t.haggle = ((String[])map.get("haggle"))[0];
    }
    if (map.containsKey("photo_url")) {
      t.photo_url = ((String[])map.get("photo_url"))[0];
    }
    t.user_id = user.getId();
    return t;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getProduct_name() {
    return product_name;
  }
  public void setProduct_name(String product_name) {
    this.product_name = product_name;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public Double getPrice() {
    return price;
  }
  public void setPrice(Double price) {
    this.price = price;
  }
  public String getPlatform() {
    return platform;
  }
  public void setPlatform(String platform) {
    this.platform = platform;
  }
  public String getVersion() {
    return version;
  }
  public void setVersion(String version) {
    this.version = version;
  }
  public String getLanguage() {
    return language;
  }
  public void setLanguage(String language) {
    this.language = language;
  }
  public String getIntegrity() {
    return integrity;
  }
  public void setIntegrity(String integrity) {
    this.integrity = integrity;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
  public String getUser_id() {
    return user_id;
  }
  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }
  public String getArea() {
    return area;
  }
  public void setArea(String area) {
    this.area = area;
  }
  public Long getCreate_time() {
    return create_time;
  }
  public void setCreate_time(Long create_time) {
    this.create_time = create_time;
  }
  public String getHaggle() {
    return haggle;
  }
  public void setHaggle(String haggle) {
    this.haggle = haggle;
  }
  public String getExchange() {
    return exchange;
  }
  public void setExchange(String exchange) {
    this.exchange = exchange;
  }
  public String getContact() {
    return contact;
  }
  public void setContact(String contact) {
    this.contact = contact;
  }
  public String getPhoto_url() {
    return photo_url;
  }
  public void setPhoto_url(String photo_url) {
    this.photo_url = photo_url;
  }
  public Integer getComment_count() {
    return comment_count;
  }
  public void setComment_count(Integer comment_count) {
    this.comment_count = comment_count;
  }
  public Long getUpdate_time() {
    return update_time;
  }
  public void setUpdate_time(Long update_time) {
    this.update_time = update_time;
  }
}
