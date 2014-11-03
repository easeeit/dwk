package com.dwk.model.product;

import java.util.Date;

/**
 * Product for favor.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class FavorProduct {

  private String userID;
  private String productID;
  private String productName;
  private Integer status;
  private Date favorDate;

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public String getProductID() {
    return productID;
  }

  public void setProductID(String productID) {
    this.productID = productID;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Date getFavorDate() {
    return favorDate;
  }

  public void setFavorDate(Date favorDate) {
    this.favorDate = favorDate;
  }

}
