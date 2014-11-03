package com.dwk.constant;

import org.springframework.http.MediaType;

/**
 * API constants.
 * 
 * @author: xp
 * @data : 2014-8-28
 * @since : 1.5
 */
public class APIConstant {

  private APIConstant() {}

  /** The Constant CONTENT_TYPE_JSON. */
  public static final String CONTENT_TYPE_JSON = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8";

  public static final String RETURN_CODE_KEY = "code";
  public static final String RETURN_MESSAGE_KEY = "msg";

  public static final int RETURN_CODE_OK = 200;
  public static final int RETURN_CODE_PARTICAL_SUCCESS = 201;
  public static final int RETURN_CODE_PARAMETER_NOT_FOUND = 400;
  public static final int RETURN_CODE_DATA_NOT_FOUND = 404;
  
  public static final int RETURN_CODE_ERROR = 500;
  public static final int RETURN_CODE_DB_EXCEPTION = 508;
  public static final int RETURN_CODE_OPERATE_PERMISSION_INVAILD = 511;
  public static final int RETURN_CODE_PARAMETER_INVAILD = 512;
  
  public static final String HEADER_TOKEN_KEY = "AuthToken";
  public static final String HEADER_KEY_KEY = "key";
  public static final String HEADER_KEYSECRET_KEY = "keySecret";
  
  
  public static final int DEFAULT_ORDER_VALUE = 1;
  public static final int PRODUCT_DEPLOY_STATUS_OPEN = 1;
  public static final int PRODUCT_DEPLOY_STATUS_CLOSE = 2;
  public static final int PRODUCT_DEPLOY_STATUS_ONDEAL = 3;
  public static final int PRODUCT_DEPLOY_STATUS_DELETE = 0;
  
  public static final boolean AS_OWNER = true;
  public static final boolean NOT_OWNER = false;
  
  public static final double LOCATION_MAX_DISTANCE_SMALL = (double)500 / (double)6371;
  public static final double LOCATION_MAX_DISTANCE_MIDDLE = (double)800 / (double)6371;
  public static final double LOCATION_MAX_DISTANCE_LARGE = (double)1000 /(double)6371;
  
  public static double getLoacationMaxDistanceByType(int type){
    switch (type) {
      case 1:
        return LOCATION_MAX_DISTANCE_SMALL;
        
      case 2:
        return LOCATION_MAX_DISTANCE_MIDDLE;
        
      case 3:
        return LOCATION_MAX_DISTANCE_LARGE;
        
      default:
        return LOCATION_MAX_DISTANCE_SMALL;
    }
  }
}
