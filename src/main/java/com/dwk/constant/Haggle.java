package com.dwk.constant;

public enum Haggle {
  yes("1", "接受小刀"),
  no("2", "一口价");
  
  private String v;
  private String name;
  
  private Haggle(String v, String name) {
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
    for (Haggle pf : values()) {
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
    for (Haggle pf : values()) {
      if (platform.equals(pf.v)) {
        return pf.name;
      }
    }
    return null;
    
  }
}
