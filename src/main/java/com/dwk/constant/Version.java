package com.dwk.constant;

public enum Version {
  cn("1", "国行"),
  us("2", "美版"),
  hk("3", "港版"),
  jp("4", "日版"),
  eu("5", "欧版");
  
  private String v;
  private String name;
  
  private Version(String v, String name) {
    this.v = v;
    this.name = name;
  }
  
  public String getValue() {
    return this.v;
  }
  
  public String getName() {
    return this.name;
  }
  
  public static String valueToName(String version) {
    if (version == null) {
      return null;
    }
    for (Version pf : values()) {
      if (version.equals(pf.v)) {
        return pf.name;
      }
    }
    return null;
  }
}
