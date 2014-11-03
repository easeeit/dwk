package com.dwk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwk.constant.APIConstant;
import com.dwk.exception.DaoException;
import com.dwk.exception.ServiceException;
import com.dwk.service.user.UserService;

/**
 * Controller for user.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

  @Autowired
  private UserService userService;
  
  @RequestMapping(value = "/getUserInfo", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String getUserInfo(HttpServletRequest request, @RequestBody String userID) throws Exception {
    try {
      return "";
    } catch (ServiceException sex) {
      return outResponse("getUserInfo", sex);
    } catch (DaoException dex) {
      return outResponse("getUserInfo", dex);
    } catch (Exception ex) {
      return outResponse("getUserInfo", ex);
    }
  }

}
