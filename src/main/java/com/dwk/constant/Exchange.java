package com.dwk.constant;

public enum Exchange {
  yes("1", "可换"),
  no("2", "不可换");
  
  private String v;
  private String name;
  
  private Exchange(String v, String name) {
    this.v = v;
    this.name = name;
  }
  public String getValue() {
    return this.v;
  }
  public String getName() {
    return name;
  }
  
  public static boolean valid(String value) {
    if (value == null) {
      return false;
    }
    for (Exchange pf : values()) {
      if (value.equals(pf.v)) {
        return true;
      }
    }
    return false;
  }
  
  public static String valueToName(String platform) {
    if (platform == null) {
      return null;
    }
    for (Exchange pf : values()) {
      if (platform.equals(pf.v)) {
        return pf.name;
      }
    }
    return null;
    
  }
}
