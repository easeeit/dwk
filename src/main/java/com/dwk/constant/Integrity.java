package com.dwk.constant;

public enum Integrity {
  brandNew("1", "全新"),
  ninety("2", "九成新"),
  eighty("3", "八成新"),
  seventy("4", "七成新"),
  sixty("5", "六成新");
  
  private String v;
  private String name;
  
  private Integrity(String v, String name) {
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
    for (Integrity pf : values()) {
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
    for (Integrity pf : values()) {
      if (platform.equals(pf.v)) {
        return pf.name;
      }
    }
    return null;
    
  }
}
