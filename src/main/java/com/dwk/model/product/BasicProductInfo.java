package com.dwk.model.product;

import java.util.List;

public class BasicProductInfo {
   
    private String _id;
    private String name;
    private String summary;
    private Integer distance;
    private Integer releaseType;
    private List<String> previewImages;
    private Integer status;
    private String ownerName;
    private String ownerAvatar;
    private Integer pcs;
    private Integer favorCount;
    private Double fee;
    private Double deposit;
    private Integer orderValue;
    private Integer quality ;
    private Integer score ;
    

    public String get_id() {
      return _id;
    }
    public void set_id(String _id) {
      this._id = _id;
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
    public Integer getDistance() {
      return distance;
    }
    public void setDistance(Integer distance) {
      this.distance = distance;
    }
    public Integer getReleaseType() {
      return releaseType;
    }
    public void setReleaseType(Integer releaseType) {
      this.releaseType = releaseType;
    }
    public List<String> getPreviewImages() {
      return previewImages;
    }
    public void setPreviewImages(List<String> previewImages) {
      this.previewImages = previewImages;
    }
    public Integer getStatus() {
      return status;
    }
    public void setStatus(Integer status) {
      this.status = status;
    }
    public String getOwnerName() {
      return ownerName;
    }
    public void setOwnerName(String ownerName) {
      this.ownerName = ownerName;
    }
    public String getOwnerAvatar() {
      return ownerAvatar;
    }
    public void setOwnerAvatar(String ownerAvatar) {
      this.ownerAvatar = ownerAvatar;
    }
    public Integer getPcs() {
      return pcs;
    }
    public void setPcs(Integer pcs) {
      this.pcs = pcs;
    }
    public Integer getFavorCount() {
      return favorCount;
    }
    public void setFavorCount(Integer favorCount) {
      this.favorCount = favorCount;
    }
    public Double getFee() {
      return fee;
    }
    public void setFee(Double fee) {
      this.fee = fee;
    }
    
    public Double getDeposit() {
      return deposit;
    }
    public void setDeposit(Double deposit) {
      this.deposit = deposit;
    }
    public Integer getOrderValue() {
      return orderValue;
    }
    public void setOrderValue(Integer orderValue) {
      this.orderValue = orderValue;
    }
    public Integer getQuality() {
      return quality;
    }
    public void setQuality(Integer quality) {
      this.quality = quality;
    }
    public Integer getScore() {
      return score;
    }
    public void setScore(Integer score) {
      this.score = score;
    }
    
    
    
    
  
}
