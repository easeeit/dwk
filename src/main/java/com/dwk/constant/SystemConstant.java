package com.dwk.constant;

/**
 * System constant.
 * 
 * @author: xp
 * @data : 2014-8-28
 * @since : 1.5
 */
public class SystemConstant {

  private SystemConstant() {}

  public static final String SYSTEM_CACHE_ROOT_KEY = "dwk.cache.root.";
  public static final String LOGIN_USER_CACHE_KEY = SYSTEM_CACHE_ROOT_KEY + "login.";
  public static final String RANDOM_CODE_CACHE_KEY = SYSTEM_CACHE_ROOT_KEY + "random.";
  
  public static final int AUTH_ACCOUNT_SOURCE_SYSTEM = 0;
  public static final int AUTH_ACCOUNT_SOURCE_LENOVO = 1;
  
  public static final int AUTH_ACCOUNT_SUCCESS = 1;
  public static final int AUTH_ACCOUNT_ERROR = 0;
  
  public static final String VERIFICATION_CODE_CACHE_KEY = SYSTEM_CACHE_ROOT_KEY + "verification.";
  
  /**
   * resend time 1 min
   */
  public static final int VERIFICATION_CODE_RESEND_TIME = 60;
  
  /**
   * timeout 3 min
   */
  public static final int VERIFICATION_CODE_CACHE_TIME = 60 * 3;
  
  public static final String VERIFICATION_MESSAGE = "【dwl】您好，您的注册验证码为【%s】。";

  
}
