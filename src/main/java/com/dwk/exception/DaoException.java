package com.dwk.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dwk.constant.APIConstant;

/**
 * Exception for dao.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class DaoException extends RuntimeException {

  private static final long serialVersionUID = 8591472698280865724L;

  private static final Logger logger = LoggerFactory.getLogger(ServiceException.class);

  private int code = APIConstant.RETURN_CODE_DB_EXCEPTION;
  private String sqlID;
  private String message;

  public DaoException(String sqlID, String message) {
    super(message);
    this.sqlID = sqlID;
    this.message = message;

    logger.error("Execute sql has exception, sqlID is '" + sqlID + "', Cause:" + message);
  }

  public DaoException(String sqlID, String message, Exception ex) {
    super(message, ex);
    this.sqlID = sqlID;
    this.message = message;

    logger.error("Execute sql has exception, sqlID is '" + sqlID + "', Cause:" + message, ex);
  }

  @Override
  public String getMessage() {
    return message;
  }

  public int getCode() {
    return code;
  }

  public String getSqlID() {
    return sqlID;
  }

}
