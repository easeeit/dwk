package com.dwk.constant;

public enum ScheduleStatus {
  will("1", "未发售"),
  done("2", "已发售");
  
  private String v;
  private String name;
  
  private ScheduleStatus(String v, String name) {
    this.v = v;
    this.name = name;
  }
  
  public String getValue() {
    return this.v;
  }
  
  public String getName() {
    return this.name;
  }
  
  public static String valueToName(String scheduleStatus) {
    if (scheduleStatus == null) {
      return null;
    }
    for (ScheduleStatus pf : values()) {
      if (scheduleStatus.equals(pf.v)) {
        return pf.name;
      }
    }
    return null;
  }
}
