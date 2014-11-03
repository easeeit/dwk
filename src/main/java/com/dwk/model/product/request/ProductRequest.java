package com.dwk.model.product.request;

import java.util.List;

import com.dwk.model.product.Location;

/**
 * Request for product.
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class ProductRequest {
    
    private Location location;
    private int maxDistanceType;
    //private String requestUserID;
    private Integer status;
    private List<String> categoryIDs;
    private Integer limited;
    public Location getLocation() {
      return location;
    }
    public void setLocation(Location location) {
      this.location = location;
    }
/*    public String getRequestUserID() {
      return requestUserID;
    }
    public void setRequestUserID(String requestUserID) {
      this.requestUserID = requestUserID;
    }*/
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
    public int getMaxDistanceType() {
      return maxDistanceType;
    }
    public void setMaxDistanceType(int maxDistanceType) {
      this.maxDistanceType = maxDistanceType;
    }
    
    
    
}
