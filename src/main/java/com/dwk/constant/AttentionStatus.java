package com.dwk.constant;

public enum AttentionStatus {
  on("1"),
  off("0");
  
  private String v;
  
  private AttentionStatus(String v) {
    this.v = v;
  }
  public String getValue() {
    return this.v;
  }
  
  public static boolean valid(String status) {
    if (status == null) {
      return false;
    }
    for (AttentionStatus as : values()) {
      if (status.equals(as.v)) {
        return true;
      }
    }
    return false;
  }
  
}
