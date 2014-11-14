package com.dwk.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.dwk.constant.APIConstant;
import com.dwk.constant.DataConstant;
import com.dwk.exception.DaoException;
import com.dwk.exception.ServiceException;
import com.dwk.model.user.LoginUser;
import com.dwk.service.comment.CommentService;
import com.dwk.service.trade.TradeService;
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
@SuppressWarnings("rawtypes")
public class UserController extends BaseController {

  @Autowired
  private UserService userService;
  @Autowired
  private TradeService tradeService;
  @Autowired
  private CommentService commentService;
  
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
  
  @RequestMapping(value = "/login", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String login(HttpServletRequest request, @RequestParam String source,
      @RequestParam String openid, @RequestParam String nickname) throws Exception {
    try {
      Object result = userService.login(source, openid, nickname);
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
      Object result = userService.logout(token);
      return outResponse(result);
    } catch (ServiceException sex) {
      return outResponse("logout", sex);
    } catch (DaoException dex) {
      return outResponse("logout", dex);
    } catch (Exception ex) {
      return outResponse("logout", ex);
    }
  }
  
  @RequestMapping(value = "/signin", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String signin(HttpServletRequest request) throws Exception {
    try {
      LoginUser user = getUser();
      Object result = userService.signin(user);
      return outResponse(result);
    } catch (ServiceException sex) {
      return outResponse("logout", sex);
    } catch (DaoException dex) {
      return outResponse("logout", dex);
    } catch (Exception ex) {
      return outResponse("logout", ex);
    }
  }

  @RequestMapping(value = {"/game/{pageNum}/{rowNum}", "/game/{pageNum}", "/game"}, produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String game(HttpServletRequest request) throws Exception {
    try {
      Integer pageNum = DataConstant.PN;
      Integer rowNum = DataConstant.RN;
      Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
      if (pathVariables != null) {
        if (pathVariables.containsKey("pageNum")) {
          pageNum = NumberUtils.toInt("" + pathVariables.get("pageNum"), pageNum);
          pageNum = pageNum > 0 ? pageNum : DataConstant.PN;
        }
        if (pathVariables.containsKey("rowNum")) {
          rowNum = NumberUtils.toInt("" + pathVariables.get("rowNum"), rowNum);
          rowNum = rowNum > 0 ? rowNum : DataConstant.RN;
        }
      }
      LoginUser user = getUser();
      return outResponse(userService.getUserAttention(user, pageNum, rowNum));
    } catch (ServiceException sex) {
      return outResponse("trade list", sex);
    } catch (DaoException dex) {
      return outResponse("trade list", dex);
    } catch (Exception ex) {
      return outResponse("trade list", ex);
    }
  }
  
  @RequestMapping(value = {"/comment/{pageNum}/{rowNum}","/comment/{pageNum}","/comment" }, produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String comment(HttpServletRequest request) throws Exception {
    try {
      Integer pageNum = DataConstant.PN;
      Integer rowNum = DataConstant.RN;
      Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
      if (pathVariables != null) {
        if (pathVariables.containsKey("pageNum")) {
          pageNum = NumberUtils.toInt("" + pathVariables.get("pageNum"), pageNum);
          pageNum = pageNum > 0 ? pageNum : DataConstant.PN;
        }
        if (pathVariables.containsKey("rowNum")) {
          rowNum = NumberUtils.toInt("" + pathVariables.get("rowNum"), rowNum);
          rowNum = rowNum > 0 ? rowNum : DataConstant.RN;
        }
      }
      LoginUser user = getUser();
      return outResponse(commentService.getUserComment(user, pageNum, rowNum));
    } catch (ServiceException sex) {
      return outResponse("trade list", sex);
    } catch (DaoException dex) {
      return outResponse("trade list", dex);
    } catch (Exception ex) {
      return outResponse("trade list", ex);
    }
  }
  
  @RequestMapping(value = "/trade/{pageNum}/{rowNum}", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String trade(HttpServletRequest request, @PathVariable Integer pageNum, @PathVariable Integer rowNum) throws Exception {
    try {
      if (pageNum <= 0) {
        pageNum = DataConstant.PN;
      }
      if (rowNum <= 0) {
        rowNum = DataConstant.RN;
      }
      LoginUser user = getUser();
      return outResponse(tradeService.getUserTrade(user, pageNum, rowNum));
    } catch (ServiceException sex) {
      return outResponse("trade list", sex);
    } catch (DaoException dex) {
      return outResponse("trade list", dex);
    } catch (Exception ex) {
      return outResponse("trade list", ex);
    }
  }

  @RequestMapping(value = "/update", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String update(HttpServletRequest request, @RequestParam String nickname, @RequestParam String logo_url,
      @RequestParam String phone, @RequestParam String email, @RequestParam String signature) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(userService.update(user, nickname, phone, email, signature, logo_url));
    } catch (ServiceException sex) {
      return outResponse("topic create", sex);
    } catch (DaoException dex) {
      return outResponse("topic create", dex);
    } catch (Exception ex) {
      return outResponse("topic create", ex);
    }
  }

}
