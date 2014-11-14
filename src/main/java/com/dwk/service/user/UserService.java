package com.dwk.service.user;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.dwk.common.Cache;
import com.dwk.constant.APIConstant;
import com.dwk.constant.DataConstant;
import com.dwk.constant.SystemConstant;
import com.dwk.dao.MongodbDao;
import com.dwk.model.BasicResponse;
import com.dwk.model.comment.UserCommentInfo;
import com.dwk.model.comment.UserCommentListResponse;
import com.dwk.model.user.AttentionListResponse;
import com.dwk.model.user.BasicUserInfo;
import com.dwk.model.user.LoginUser;
import com.dwk.model.user.SigninResponse;
import com.dwk.model.user.User;
import com.dwk.model.user.UserInfo;
import com.dwk.service.auth.AuthService;
import com.dwk.service.product.ScheduleService;

/**
 * User info service.
 * 
 * @author: xp
 */
public class UserService {

  private Cache cache;
  private MongodbDao dao;
  private AuthService authService;
  private ScheduleService scheduleService;
  
  public String initUser(User user) {
    String id = dao.insert("initUser", user);
    user.setId(id);
    return id;
  }
  
  public User getUserByAccountID(String accountID) {
    return dao.selectOne("getUserByAccountID", accountID);
  }
  
  // TODO 缓存
  public User getUserByID(String userID) {
    return dao.selectOne("getUserByID", userID);
  }
  
  public User getUserByName(String name) {
    return dao.selectOne("getUserByName", name);
  }
  
  public List<BasicUserInfo> findBasicUserInfoByIDs(List<String> ids) {
    return dao.selectList("findBasicUserInfoByIDs", ids);
  }

  public BasicUserInfo findBasicUserByID(String id) {
    return dao.selectOne("findBasicUserInfoByIDs", Arrays.asList(id));
  }
  
  public User login(String source, String openid, String nickname) {
    UserInfo res = null;
    if (StringUtils.isBlank(openid)) {
      res = new UserInfo();
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    // 检索用户
    res = getUserByOpenid(openid);
    // 检索不到,创建用户
    if (res == null) {
      res = new UserInfo();
      long now = System.currentTimeMillis();
      res.setCreate_time(now);
      res.setLast_login_time(now);
      res.setNickname(nickname);
      res.setOpenid(openid);
      res.setScore(0);
      res.setSource(source);
      res.setStatus(DataConstant.STATUS_ENABLE);
      String userID = dao.insert("createUser", res);
      if (StringUtils.isBlank(userID)) {
        res.setCode(APIConstant.RETURN_CODE_ERROR);
        return res;
      } else {
        res.setId(userID);
      }
    }
    LoginUser user = authService.putSession(res);
    res.setToken(user.getAuthToken());
    return res;
  }  
  
  public UserInfo getUserByOpenid(String openid) {
    return dao.selectOne("getUserByOpenid", openid);
  }
  
  public BasicResponse logout(String token) {
    BasicResponse res = authService.logout(token);
    return res;
  }
  
  public SigninResponse signin(LoginUser user) {
    SigninResponse res = new SigninResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    // TODO 已签到过滤
    Object o = cache.get(SystemConstant.SIGNIN_CACHE_KEY+user.getId());
    if (o != null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      res.setScore(user.getScore());
      return res;
    }
    Map<String,Object> map = new HashMap<String, Object>(2);
    map.put("userID", user.getId());
    map.put("score", DataConstant.SIGNIN_SCORE);
    int count = dao.update("inscreaseUserScore", map);
    if (count == 1) {
      int score = user.getScore() + DataConstant.SIGNIN_SCORE;
      res.setScore(score);
      user.setScore(score);
      authService.resetSession(user);
      // 设置当天 已签到 标志
      cache.setToday(SystemConstant.SIGNIN_CACHE_KEY+user.getId(),'1');
    }
    return res;
  }
  
  // TODO cache
  public AttentionListResponse getUserAttention(LoginUser user,int pageNum, int rowNum) {
    AttentionListResponse res = new AttentionListResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    // search x_attention 
    List<String> productList = 
        dao.selectList("getUserAttentionProduct", user.getId(),  rowNum, (pageNum - 1) * rowNum);
    // search product and schedule
    res.setGame(scheduleService.getScheduleInfo(productList));
    return res;
  }

  public BasicResponse update(LoginUser user, String nickname, String phone, String email, 
      String signature, String logo_url) {
    BasicResponse res = new BasicResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    Map<String, Object> map = new HashMap<String, Object>(6);
    map.put("id", user.getId());
    map.put("nickname", nickname);
    map.put("phone", phone);
    map.put("email", email);
    map.put("signature", signature);
    map.put("logo_url", logo_url);
    int count = dao.update("updateUser", map);
    if (count <= 0) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    }
    return res;
  }
  
  
//  public void updateLastOperateTime(String userID) {
//    Map<String,Object> map = new HashMap<String, Object>(2);
//    map.put("userID", userID);
//    map.put("lastLoginTime", System.currentTimeMillis());
//    dao.update("updateLastLoginTime", map);
//  }
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }

  public void setAuthService(AuthService authService) {
    this.authService = authService;
  }

  public void setScheduleService(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

  public void setCache(Cache cache) {
    this.cache = cache;
  }
  
}
