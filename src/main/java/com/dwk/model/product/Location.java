package com.dwk.model.product;

import net.sf.json.JSONObject;

public class Location {
  private double lgt;
  private double lat;

  


  public double getLgt() {
    return lgt;
  }




  public void setLgt(double lgt) {
    this.lgt = lgt;
  }




  public double getLat() {
    return lat;
  }




  public void setLat(double lat) {
    this.lat = lat;
  }




  public JSONObject toJsonObject(){
    JSONObject job = new JSONObject();
    job.put("lgt", getLgt());
    job.put("lat", getLat());
    return job;
  }
  
}
