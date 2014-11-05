package com.dwk.constant;

public enum TradeStatus {
  open("1"),
  close("2");
  
  private String v;
  
  private TradeStatus(String v) {
    this.v = v;
  }
  public String getValue() {
    return this.v;
  }
  
  public static boolean valid(String status) {
    if (status == null) {
      return false;
    }
    for (TradeStatus as : values()) {
      if (status.equals(as.v)) {
        return true;
      }
    }
    return false;
  }
  
}
