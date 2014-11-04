package com.dwk.service.friend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.dwk.constant.APIConstant;
import com.dwk.dao.MongodbDao;
import com.dwk.model.BasicResponse;
import com.dwk.model.ListRequest;
import com.dwk.model.friend.FriendShip;
import com.dwk.model.friend.request.InviteFriendRequest;
import com.dwk.model.friend.request.InviteInfo;
import com.dwk.model.friend.response.FriendListResponse;
import com.dwk.model.friend.response.FriendRequestList;
import com.dwk.model.friend.response.FriendRequestList.RequestInfo;
import com.dwk.model.user.BasicUserInfo;
import com.dwk.model.user.LoginUser;
import com.dwk.model.user.User;
import com.dwk.service.user.UserService;
import com.lenovo.supernote.orm.cosure.CosureUtils;

/**
 * Friend service.
 * 
 * @author: xp
 * @data : 2014-8-28
 * @since : 1.5
 */
public class FriendService {

  private MongodbDao dao;
  private UserService userService;

  public FriendListResponse friendList(LoginUser user, ListRequest request) {
    FriendListResponse response = new FriendListResponse();

    Map<String, Object> parameter = new HashMap<String, Object>();
    parameter.put("userID", user.getId());
    List<String> friendIDs = dao.selectList("findFriendUserID", parameter, request.getLimit(), request.getSkip());
    if (CollectionUtils.isEmpty(friendIDs)) {
      return response;
    }

    List<BasicUserInfo> friends = userService.findBasicUserInfoByIDs(friendIDs);
    response.setFriendList(friends);

    return response;
  }

  public BasicResponse inviteFriend(LoginUser user, InviteFriendRequest request) {
    List<InviteInfo> addUserList = request.getAddUserList();

    BasicResponse response = new BasicResponse();
    if (CollectionUtils.isEmpty(addUserList)) {
      return response;
    }

    long currentTime = System.currentTimeMillis();
    String requestMessage = request.getRequestMessage();
    for (InviteInfo invite : addUserList) {
      String phone = invite.getAddUserPhoneNum();

      User _user = userService.getUserByName(phone);

      // if not exists
      if (isNewInvite(user, _user, phone)) {
        FriendShip fs = new FriendShip();
        fs.setRequestUserID(user.getId());
        fs.setRequestUserPhone(user.getUserPhone());
        fs.setRequestMessage(requestMessage);
        fs.setRequestTime(currentTime);
        fs.setTargetUserPhone(phone);

        if (_user != null) {
          fs.setStatus(2);
          fs.setAcceptionTime(currentTime);
          fs.setTargetUserID(_user.getId());
        }
        dao.insert("addFriendShip", fs);
      }
    }

    return response;
  }

  /**
   * un used.
   */
  @Deprecated
  public FriendRequestList friendRequestList(LoginUser user, ListRequest parameter) {
    FriendRequestList response = new FriendRequestList();

    List<FriendShip> requestList = dao.selectList("findFriendRequestList", user.getId());
    if (CollectionUtils.isEmpty(requestList)) {
      return response;
    }

    List<String> requestIDs = CosureUtils.convert(requestList, FriendShip.to_request_id);
    List<BasicUserInfo> requestUsers = userService.findBasicUserInfoByIDs(requestIDs);

    List<RequestInfo> list = new ArrayList<RequestInfo>();
    for (FriendShip fs : requestList) {
      RequestInfo ri = new RequestInfo();
      ri.setRequestUserID(fs.getRequestUserID());
      ri.setRequestUserPhone(fs.getRequestUserPhone());
      ri.setRequestMessage(fs.getRequestMessage());
      ri.setRequestTime(fs.getRequestTime());

      BasicUserInfo requestUser = CosureUtils.filter(requestUsers, fs.getRequestUserID(), BasicUserInfo.to_user_id);
      if (requestUser != null) {
        ri.setRequestUserName(requestUser.getName());
        ri.setRequestUserAvatar(requestUser.getAvatar());
      }
      list.add(ri);
    }
    response.setRequestList(list);
    return response;
  }

  /**
   * un used.
   */
  @Deprecated
  public BasicResponse confirmFriend(LoginUser user, String friendShipID) {
    BasicResponse response = new BasicResponse();

    Map<String, Object> parameter = new HashMap<String, Object>();
    parameter.put("id", friendShipID);
    parameter.put("userID", user.getId());
    parameter.put("time", System.currentTimeMillis());

    int row = dao.update("confirmFriend", parameter);
    if (row <= 0) {
      response.setCode(APIConstant.RETURN_CODE_DATA_NOT_FOUND);
    }
    return response;
  }

  public void supplementFriendShip(User user) {
    FriendShip fs = dao.selectOne("getInviteRecordByPhone", user.getName());
    if (fs != null) {
      fs.setTargetUserID(user.getId());
      fs.setAcceptionTime(System.currentTimeMillis());
      dao.update("supplementFriendShip", fs);
    }
  }

  private boolean isNewInvite(LoginUser user, User targetUser, String targetPhone) {
    Map<String, Object> parameter = new HashMap<String, Object>();
    parameter.put("userID", user.getId());
    parameter.put("phone", targetPhone);

    FriendShip fs = dao.selectOne("getFriendShipRelation", parameter);
    if (fs != null) {
      return false;
    }

    if (targetUser != null) {
      parameter.put("userID", targetUser.getId());
      parameter.put("phone", user.getUserPhone());
      fs = dao.selectOne("getFriendShipRelation", parameter);
      if (fs != null) {
        return false;
      }
    }

    return true;
  }

  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

}
