package com.dwk.model.product.request;

import java.util.List;

/**
 * Request for product.
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class UserProductRequest {
    
    private String userID;
    private Integer status;
    private List<String> categoryIDs;
    private Integer limited;
    public String getUserID() {
      return userID;
    }
    public void setUserID(String userID) {
      this.userID = userID;
    }
    public Integer getStatus() {
      return status;
    }
    public void setStatus(Integer status) {
      this.status = status;
    }
    public List<String> getCategoryIDs() {
      return categoryIDs;
    }
    public void setCategoryIDs(List<String> categoryIDs) {
      this.categoryIDs = categoryIDs;
    }
    public Integer getLimited() {
      return limited;
    }
    public void setLimited(Integer limited) {
      this.limited = limited;
    }
    
    

}
