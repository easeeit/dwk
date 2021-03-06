package com.dwk.constant;

import org.apache.commons.lang.StringUtils;

public enum AttentionStatus {
  on("1"),
  off("2");
  
  private String v;
  
  private AttentionStatus(String v) {
    this.v = v;
  }
  public String getValue() {
    return this.v;
  }
  
  public static AttentionStatus parse(String value) {
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
    for (AttentionStatus as : values()) {
      if (status.equals(as.v)) {
        return true;
      }
    }
    return false;
  }
  
}
