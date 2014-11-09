package com.dwk.constant;

import org.apache.commons.lang.StringUtils;

public enum OrderBy {
  time("1", "timetomarket"),
  hot("2", "hot");
  private String v;
  private String column;
  private OrderBy(String v,String column) {
    this.v = v;
    this.column = column;
  }
  
  public static OrderBy parse(String value) {
    if (StringUtils.isBlank(value)) {
      return time;
    }
    if ("2".equals(value)) {
      return hot;
    } else {
      return time;
    }
  }
  
  public String getValue() {
    return this.v;
  }
  public String getColumn() {
    return this.column;
  }
}
