package com.dwk.common.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * System sms sender.
 * 
 * @author: xiangping_yu
 * @data : 2014-8-28
 * @since : 1.5
 */
public class SmsSender {

  static final Logger logger = LoggerFactory.getLogger(SmsSender.class);
  
  public boolean send(String phone, String message) {
    logger.debug("Send sms to phone '"+phone+"', message '"+message+"'.");
    return EmaySmsPlatform.sendSms(phone, message);
  }
  
}
