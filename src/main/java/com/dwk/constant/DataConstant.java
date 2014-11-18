package com.dwk.constant;


public class DataConstant {
  private DataConstant() {}
  
  public static final String DATE_FORMAT = "yyyy-MM-dd";
  public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  
  public static final String STATUS_ENABLE = "1";
  public static final String STATUS_DELETE = "2";  
  public static final int PN = 1;
  public static final int RN = 10;
  public static final int RN_UNLIMIT = 9999999;
  
  public static final int HOT_COMMENT_COUNT = 3;
  public static final int SCHEDULE_TOP_COUNT = 5;
  
  public static final int PRODUCT_TOP_COUNT = 3;

  // 签到积分
  public static final int SIGNIN_SCORE = 5;
  
  // 评论级别
  public static final int COMMENT_LEVEL_1 = 1;
  public static final int COMMENT_LEVEL_2 = 2;

  // 评分上下限
  public static final int SCORE_LIMIT_LOWER = 1;
  public static final int SCORE_LIMIT_UPPER = 5;
  
}
