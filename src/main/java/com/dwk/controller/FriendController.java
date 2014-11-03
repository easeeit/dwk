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
import com.dwk.model.ListRequest;
import com.dwk.model.friend.request.InviteFriendRequest;
import com.dwk.model.user.LoginUser;
import com.dwk.service.friend.FriendService;

/**
 * Controller for friend.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
@Controller
@RequestMapping("/friend")
public class FriendController extends BaseController {

  @Autowired
  private FriendService friendService;

  @RequestMapping(value = "/friendList", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String friendList(HttpServletRequest request, @RequestBody ListRequest parameter) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(friendService.friendList(user, parameter));
    } catch (ServiceException sex) {
      return outResponse("friendList", sex);
    } catch (DaoException dex) {
      return outResponse("friendList", dex);
    } catch (Exception ex) {
      return outResponse("friendList", ex);
    }
  }

  @RequestMapping(value = "/addFriend", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String inviteFriend(HttpServletRequest request, @RequestBody InviteFriendRequest parameter) throws Exception {
    try {
      LoginUser user = getUser();
      Object result = friendService.inviteFriend(user, parameter);
      return outResponse(result);
    } catch (ServiceException sex) {
      return outResponse("addFriend", sex);
    } catch (DaoException dex) {
      return outResponse("addFriend", dex);
    } catch (Exception ex) {
      return outResponse("addFriend", ex);
    }
  }

  @RequestMapping(value = "/friendRequestList", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String friendRequestList(HttpServletRequest request, @RequestBody ListRequest parameter) throws Exception {
    try {
      LoginUser user = getUser();
      Object result = friendService.friendRequestList(user, parameter);
      return outResponse(result);
    } catch (ServiceException sex) {
      return outResponse("friendRequestList", sex);
    } catch (DaoException dex) {
      return outResponse("friendRequestList", dex);
    } catch (Exception ex) {
      return outResponse("friendRequestList", ex);
    }
  }

  @RequestMapping(value = "/confirmUser2Friend", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String confirmUser2Friend(HttpServletRequest request, @RequestBody String friendShipID) throws Exception {
    try {
      LoginUser user = getUser();
      Object result = friendService.confirmFriend(user, friendShipID);
      return outResponse(result);
    } catch (ServiceException sex) {
      return outResponse("confirmUser2Friend", sex);
    } catch (DaoException dex) {
      return outResponse("confirmUser2Friend", dex);
    } catch (Exception ex) {
      return outResponse("confirmUser2Friend", ex);
    }
  }

}
