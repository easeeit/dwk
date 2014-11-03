package com.dwk.model.deal;

import java.util.Date;

/**
 * Log for user deal.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class UserDealLog {

  private String userIDLend;
  private String userIDBorrow;
  private String productID;
  private String productName;
  private Date dealDate;
  private Date closeDate;
  private Integer dealStatus;

  public String getUserIDLend() {
    return userIDLend;
  }

  public void setUserIDLend(String userIDLend) {
    this.userIDLend = userIDLend;
  }

  public String getUserIDBorrow() {
    return userIDBorrow;
  }

  public void setUserIDBorrow(String userIDBorrow) {
    this.userIDBorrow = userIDBorrow;
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

  public Date getDealDate() {
    return dealDate;
  }

  public void setDealDate(Date dealDate) {
    this.dealDate = dealDate;
  }

  public Date getCloseDate() {
    return closeDate;
  }

  public void setCloseDate(Date closeDate) {
    this.closeDate = closeDate;
  }

  public Integer getDealStatus() {
    return dealStatus;
  }

  public void setDealStatus(Integer dealStatus) {
    this.dealStatus = dealStatus;
  }

}
