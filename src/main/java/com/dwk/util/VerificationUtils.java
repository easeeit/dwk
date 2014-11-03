package com.dwk.util;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * Verification utils
 * 
 * @author: xp
 * @data : 2014-10-10
 * @since : 1.5
 */
public class VerificationUtils {

  private VerificationUtils() {};

  public static boolean isMobile(String value) {
    if (StringUtils.isBlank(value)) {
      return false;
    }
    return Pattern.matches("^1\\d{10}$", value);
  }

  public static boolean isEmail(String value) {
    if (StringUtils.isBlank(value)) {
      return false;
    }
    return Pattern.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$", value);
  }

}
