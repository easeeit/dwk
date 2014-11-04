package com.dwk.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwk.constant.APIConstant;
import com.dwk.exception.DaoException;
import com.dwk.exception.ServiceException;
import com.dwk.model.auth.request.LoginRequest;
import com.dwk.model.auth.request.MobilAuthRequest;
import com.dwk.model.auth.request.RegistRequest;
import com.dwk.model.auth.request.SendVerificationCodeRequest;
import com.dwk.service.auth.AuthService;

/**
 * Controller for auth.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {

  @Autowired
  private AuthService authService;

  @RequestMapping(value = "/accountMobileAuth", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String accountMobileAuth(HttpServletRequest request, @RequestBody MobilAuthRequest parameter) throws Exception {
    try {
    	
      return outResponse(authService.mobilAuth(parameter));
    } catch (ServiceException sex) {
      return outResponse("accountMobileAuth", sex);
    } catch (DaoException dex) {
      return outResponse("accountMobileAuth", dex);
    } catch (Exception ex) {
      return outResponse("accountMobileAuth", ex);
    }
  }
  
  @RequestMapping(value = "/login", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String login(HttpServletRequest request, @RequestBody LoginRequest login) throws Exception {
    try {
      Object result = authService.login(login);
      return outResponse(result);
    } catch (ServiceException sex) {
      return outResponse("login", sex);
    } catch (DaoException dex) {
      return outResponse("login", dex);
    } catch (Exception ex) {
      return outResponse("login", ex);
    }
  }

  @RequestMapping(value = "/logout", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String logout(HttpServletRequest request) throws Exception {
    try {
      String token = request.getHeader(APIConstant.HEADER_TOKEN_KEY);
      
      Object result = authService.logout(token);
      return outResponse(result);
    } catch (ServiceException sex) {
      return outResponse("logout", sex);
    } catch (DaoException dex) {
      return outResponse("logout", dex);
    } catch (Exception ex) {
      return outResponse("logout", ex);
    }
  }

  @RequestMapping(value = "/sendVerificationCode", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String sendVerificationCode(HttpServletRequest request, @RequestBody SendVerificationCodeRequest parameter) throws Exception {
    try {
      Object result = authService.sendVerificationCode(parameter);
      return outResponse(result);
    } catch (ServiceException sex) {
      return outResponse("sendVerificationCode", sex);
    } catch (DaoException dex) {
      return outResponse("sendVerificationCode", dex);
    } catch (Exception ex) {
      return outResponse("sendVerificationCode", ex);
    }
  }
  
  @RequestMapping(value = "/regist", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String regist(HttpServletRequest request, @RequestBody RegistRequest regist) throws Exception {
    try {
      Object result = authService.regist(regist);
      return outResponse(result);
    } catch (ServiceException sex) {
      return outResponse("regist", sex);
    } catch (DaoException dex) {
      return outResponse("regist", dex);
    } catch (Exception ex) {
      return outResponse("regist", ex);
    }
  }

}
