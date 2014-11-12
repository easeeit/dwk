package com.dwk.constant;

public enum DelayStatus {
  normal("1", "正常"),
  delay("2", "延期");
  
  private String v;
  private String name;
  
  private DelayStatus(String v, String name) {
    this.v = v;
    this.name = name;
  }
  
  public String getValue() {
    return this.v;
  }
  
  public String getName() {
    return this.name;
  }
  
  public static String valueToName(String delayStatus) {
    if (delayStatus == null) {
      return null;
    }
    for (DelayStatus pf : values()) {
      if (delayStatus.equals(pf.v)) {
        return pf.name;
      }
    }
    return null;
  }
}
