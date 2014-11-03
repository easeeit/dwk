package com.dwk.model.category;

/**
 * Category info.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class Category {

  private String id;
  private String name;
  private String summary;
  private String parentID;
  private Integer orderValue;

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

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getParentID() {
    return parentID;
  }

  public void setParentID(String parentID) {
    this.parentID = parentID;
  }

  public Integer getOrderValue() {
    return orderValue;
  }

  public void setOrderValue(Integer orderValue) {
    this.orderValue = orderValue;
  }

}
