package com.dwk.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception for service.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class ServiceException extends RuntimeException {

  private static final long serialVersionUID = 8591472698280865724L;

  private static final Logger logger = LoggerFactory.getLogger(ServiceException.class);

  private int code;
  private String message;

  public ServiceException(int code, String message) {
    super(message);
    this.code = code;
    this.message = message;

    logger.error("Service has exception, code is '" + code + "', Cause:" + message);
  }

  public ServiceException(int code, String message, Exception ex) {
    super(message, ex);
    this.code = code;
    this.message = message;

    logger.error("Service has exception, code is '" + code + "', Cause:" + message, ex);
  }

  @Override
  public String getMessage() {
    return message;
  }

  public int getCode() {
    return code;
  }

}
