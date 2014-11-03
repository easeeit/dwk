package com.dwk.model.message.request;

public class ProductProcessRequest extends SendMessageRequest {

  private String targetProductID;
  private Integer signal;
  
  public String getTargetProductID() {
    return targetProductID;
  }
  public void setTargetProductID(String targetProductID) {
    this.targetProductID = targetProductID;
  }
  public Integer getSignal() {
    return signal;
  }
  public void setSignal(Integer signal) {
    this.signal = signal;
  }

}
