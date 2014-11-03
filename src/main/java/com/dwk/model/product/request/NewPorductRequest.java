package com.dwk.model.product.request;

import java.util.List;

import com.dwk.model.product.Location;

public class NewPorductRequest {

   private String name;
   private String summary ;
   private Location location;
   private Integer releaseType ;
   private List<String> previewImages ;
   private Integer pcs ;
   private Double fee ;
   private Double deposit;
   private String requirement ;
   private String categoryName ;
   private String categoryID ;
   private Integer quality;
   private Integer score;
   
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
  public Location getLocation() {
    return location;
  }
  public void setLocation(Location location) {
    this.location = location;
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
  public Integer getPcs() {
    return pcs;
  }
  public void setPcs(Integer pcs) {
    this.pcs = pcs;
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
  public String getRequirement() {
    return requirement;
  }
  public void setRequirement(String requirement) {
    this.requirement = requirement;
  }
  public String getCategoryName() {
    return categoryName;
  }
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
  public String getCategoryID() {
    return categoryID;
  }
  public void setCategoryID(String categoryID) {
    this.categoryID = categoryID;
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
