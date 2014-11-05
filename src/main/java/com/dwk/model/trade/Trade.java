package com.dwk.model.trade;

import java.util.Map;

import com.dwk.constant.DataConstant;
import com.dwk.model.user.LoginUser;

public class Trade {
  private String id;
  private String product_name;
  private String mode;
  private String status;
  private Double price;
  private String platform;
  private String version;
  private String language;
  private String quality;
  private String integrity;
  private String attachment;
  private String description;
  private String remark;
  private String user_id;
  private String phone;
  private String area;
  private Long create_time;
  
  public static Trade create(LoginUser user, Map<String, Object> map) {
    Trade t = new Trade();
    t.area = (String)map.get("area");
    t.create_time = System.currentTimeMillis();
    t.description = (String)map.get("description");
    t.integrity = (String)map.get("integrity");
    t.language = (String)map.get("language");
    t.mode = (String)map.get("mode");
    t.phone = (String)map.get("phone");
    t.platform = (String)map.get("platform");
    t.price = Double.parseDouble(String.valueOf(map.get("price")));
    t.product_name = (String)map.get("product_name");
    t.quality = (String)map.get("quality");
    t.remark = (String)map.get("remark");
    t.status = DataConstant.STATUS_ENABLE;
    t.user_id = user.getId();
    t.version = (String)map.get("version");
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
  public String getMode() {
    return mode;
  }
  public void setMode(String mode) {
    this.mode = mode;
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
  public String getQuality() {
    return quality;
  }
  public void setQuality(String quality) {
    this.quality = quality;
  }
  public String getIntegrity() {
    return integrity;
  }
  public void setIntegrity(String integrity) {
    this.integrity = integrity;
  }
  public String getAttachment() {
    return attachment;
  }
  public void setAttachment(String attachment) {
    this.attachment = attachment;
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
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
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
}
