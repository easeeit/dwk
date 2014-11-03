package com.dwk.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dwk.constant.APIConstant;
import com.dwk.exception.DaoException;
import com.dwk.exception.ServiceException;
import com.dwk.filter.AuthFilter;
import com.dwk.model.user.LoginUser;

/**
 * The system controller.
 * 
 * @author: xp
 * @data : 2014-8-28
 * @since : 1.5
 */
public class BaseController {

  protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

  /** Get current login user. */
  protected LoginUser getUser() {
    return AuthFilter.getLoginUser();
  }

  protected String outResponse(String tag, ServiceException sex) {
    logger.error("Controller error: [" + tag + "] has exception in service, Cause: " + sex.getMessage(), sex);
    JSONObject response = new JSONObject();
    response.put(APIConstant.RETURN_CODE_KEY, sex.getCode());
    response.put(APIConstant.RETURN_MESSAGE_KEY, sex.getMessage());
    return response.toString();
  }

  protected String outResponse(String tag, DaoException dex) {
    logger.error("Controller error: [" + tag + "] has exception in dao, Cause: " + dex.getMessage(), dex);

    JSONObject response = new JSONObject();
    response.put(APIConstant.RETURN_CODE_KEY, dex.getCode());
    response.put(APIConstant.RETURN_MESSAGE_KEY, dex.getMessage());
    return response.toString();
  }

  protected String outResponse(String tag, Exception ex) {
    logger.error("Controller error: [" + tag + "] has exception, Cause: " + ex.getMessage(), ex);

    JSONObject response = new JSONObject();
    response.put(APIConstant.RETURN_CODE_KEY, APIConstant.RETURN_CODE_ERROR);
    response.put(APIConstant.RETURN_MESSAGE_KEY, "Service unavailable.");
    return response.toString();
  }

/*  protected String outResponse(String valueKey, Object result) {
    JSONObject response = new JSONObject();
    response.put(APIConstant.RETURN_CODE_KEY, APIConstant.RETURN_CODE_OK);
    response.put(valueKey, result);
    return response.toString();
  }*/

  protected String outResponse(Object result) {
    JSONObject response = new JSONObject();
    //response.put(APIConstant.RETURN_CODE_KEY, APIConstant.RETURN_CODE_OK);  
    if(result != null)
      response.putAll(JSONObject.fromObject(result));
    return response.toString();
  }
  
/*  protected String outResponse(Integer code) {
    JSONObject response = new JSONObject();
    response.put(APIConstant.RETURN_CODE_KEY, code);
    return response.toString();
  }  */

  public static void outIMGStream(HttpServletResponse response, byte[] data) throws IOException {
    response.setContentType("image/png");

    OutputStream stream = response.getOutputStream();
    stream.write(data);
    stream.flush();
    stream.close();
  }

  /**
   * Get client ip.
   */
  protected String getRemoteIP(HttpServletRequest req) {
    String ip = req.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = req.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = req.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = req.getRemoteAddr();
    }

    if (ip == null) {
      return "unknown";
    }
    // proxy multiple forward
    int index = ip.indexOf(",");
    return (index != -1) ? ip.substring(0, index) : ip;
  }

}
