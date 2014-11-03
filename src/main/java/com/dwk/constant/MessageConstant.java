package com.dwk.constant;

/**
 * Constant for return message.
 * @author: xp
 * @data : 2014-9-2
 * @since : 1.5
 */
public class MessageConstant {
  
  public static final String PHONE_EXISTS = "该号码已经被注册。";
  public static final String PARAMETER_NOT_FOUND_FORMAT = "参数'%s'不能为空。";
  public static final String USER_NOT_FOUND = "该帐号不存在。";
  public static final String PARAMETER_INVAILD = "参数不合法。";
  public static final String VERIFICATION_CODE_NOT_TIMEOUT = "请60s后再次发送验证码。";
  public static final String VERIFICATION_CODE_ERROR = "验证码不正确。";
  
  public static final int MESSAGE_TYPE_NORMAL = 0;
  
  public static final int MESSAGE_TYPE_REQUEST_PRODUCT = 1;
  public static final int MESSAGE_TYPE_CONFIRM_PRODUCT = 2;
  public static final int MESSAGE_TYPE_REJECT_REQUEST = 3;
  public static final int MESSAGE_TYPE_RETURN_PRODUCT = 4;
  public static final int MESSAGE_TYPE_RETURN_CONFIRM = 5; // or closeDeal
  public static final int MESSAGE_TYPE_REJECT_RETURN = 6;
  
}
