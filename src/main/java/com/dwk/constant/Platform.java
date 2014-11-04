package com.dwk.constant;

public enum Platform {
  xbox("1", "XBox"),
  ps("2", "PS");
  
  private String v;
  private String name;
  
  private Platform(String v, String name) {
    this.v = v;
    this.name = name;
  }
  public String getValue() {
    return this.v;
  }
  public String getName() {
    return name;
  }
  
  public static boolean valid(String platform) {
    if (platform == null) {
      return false;
    }
    for (Platform pf : values()) {
      if (platform.equals(pf.v)) {
        return true;
      }
    }
    return false;
  }
  
  public static String valueToName(String platform) {
    if (platform == null) {
      return null;
    }
    for (Platform pf : values()) {
      if (platform.equals(pf.v)) {
        return pf.name;
      }
    }
    return null;
    
  }
}
