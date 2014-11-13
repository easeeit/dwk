package com.dwk.constant;

import org.apache.commons.lang.StringUtils;

public enum LaudStatus {
  on("1"),
  off("2");
  
  private String v;
  
  private LaudStatus(String v) {
    this.v = v;
  }
  public String getValue() {
    return this.v;
  }
  
  public static LaudStatus parse(String value) {
    if (StringUtils.isBlank(value)) {
      return null;
    }
    if ("2".equals(value)) {
      return off;
    } else if ("1".equals(value)){
      return on;
    } else {
      return null;
    }
  }
  
  public static boolean valid(String status) {
    if (status == null) {
      return false;
    }
    for (LaudStatus as : values()) {
      if (status.equals(as.v)) {
        return true;
      }
    }
    return false;
  }
  
}
