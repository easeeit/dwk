package com.dwk.util;

import java.math.BigDecimal;

public class NumberUtil {
  private NumberUtil() {}

  public static double digit(double d, int i) {
    if (i >0) {
      BigDecimal b = new BigDecimal(d);
      return b.setScale(i, BigDecimal.ROUND_HALF_UP).doubleValue();
    } else {
      return d;
    }
  }
}
