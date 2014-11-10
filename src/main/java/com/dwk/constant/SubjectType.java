package com.dwk.constant;

public enum SubjectType {
  article("1"),
  topic("2"),
  trade("3"),
  comment("4"),
  product("5"),
  user("6");
  
  private String v;
  
  private SubjectType(String v) {
    this.v = v;
  }
  public String getValue() {
    return this.v;
  }
  
  public static boolean valid(String subjectType) {
    if (subjectType == null) {
      return false;
    }
    for (SubjectType type : values()) {
      if (subjectType.equals(type.v)) {
        return true;
      }
    }
    return false;
  }
}
