package com.dwk.service.auth;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.dwk.common.Cache;
import com.dwk.common.sms.SmsSender;
import com.dwk.constant.APIConstant;
import com.dwk.constant.SystemConstant;
import com.dwk.model.BasicResponse;
import com.dwk.model.auth.AuthContext;
import com.dwk.model.auth.VerificationCode;
import com.dwk.model.auth.request.LoginRequest;
import com.dwk.model.auth.request.MobilAuthRequest;
import com.dwk.model.auth.request.RegistRequest;
import com.dwk.model.auth.request.SendVerificationCodeRequest;
import com.dwk.model.auth.response.AuthResponse;
import com.dwk.model.auth.response.LoginResponse;
import com.dwk.model.user.Account;
import com.dwk.model.user.BasicUserInfo;
import com.dwk.model.user.LoginUser;
import com.dwk.model.user.User;
import com.dwk.service.account.AccountService;
import com.dwk.service.friend.FriendService;
import com.dwk.service.user.UserService;
import com.dwk.util.IDCompress;

/**
 * System auth service. Login,logout and register.
 * 
 * @author: xp
 * @data : 2014-8-28
 * @since : 1.5
 */
public class AuthService {

  private Cache cache;
//  private LenovoID lenovoid;
  private AccountService accountService;
  private UserService userService;
  private FriendService friendService;
  private SmsSender smsSender;

  /**
   * User login
   */
  public AuthResponse mobilAuth(MobilAuthRequest auth) {
    AuthResponse response = new AuthResponse();
    String st = auth.getLenovoST();
    if (StringUtils.isBlank(st)) {
      response.setCode(APIConstant.RETURN_CODE_PARAMETER_NOT_FOUND);
      return response;
    }

    String realm = auth.getRealm();
    if (StringUtils.isBlank(realm)) {
      response.setCode(APIConstant.RETURN_CODE_PARAMETER_NOT_FOUND);
      return response;
    }

    // auth lenovo id
//    AuthContext context = lenovoid.auth(st, realm);
    AuthContext context = new AuthContext(); // TODO
    if (!context.isSuccess()) {
      response.setAuthStatus(SystemConstant.AUTH_ACCOUNT_ERROR);
      return response;
    }

    User user = null;
    Account account = accountService.getAccountByAuthInfo(context);
    if (account == null) {
      account = initAccount(context);
      user = initUser(context, account.getId());
    } else {
      user = userService.getUserByAccountID(account.getId());
      accountService.updateLastLoginTime(account.getId(), System.currentTimeMillis());
    }

    // add to session
    LoginUser loginUser = putSession(user);

    response.setAuthStatus(SystemConstant.AUTH_ACCOUNT_SUCCESS);
    response.setAuthToken(loginUser.getAuthToken());
    BasicUserInfo userInfo = new BasicUserInfo();
    userInfo.setUserID(loginUser.getId());
    userInfo.setAvatar(loginUser.getAvatar());
    userInfo.setName(loginUser.getName());
    userInfo.setNickName(loginUser.getNickName());
    userInfo.setRpValue(loginUser.getRpValue());
    response.setUserInfo(userInfo);

    return response;
  }

  public BasicResponse sendVerificationCode(SendVerificationCodeRequest request) {
    BasicResponse response = new BasicResponse();

    String phone = request.getPhone();
    if (StringUtils.isBlank(phone)) {
      response.setCode(APIConstant.RETURN_CODE_PARAMETER_NOT_FOUND);
      return response;
    }

    Account account = accountService.getAccountByPhone(phone);
    if (account != null) {
      response.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return response;
    }

    String cacheKey = SystemConstant.VERIFICATION_CODE_CACHE_KEY + phone;
    VerificationCode vc = (VerificationCode)cache.get(cacheKey);
    // TOOD(VerificationCode)cache.get(cacheKey);
    if (vc != null && !vc.isTimeOut()) {
      response.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return response;
    }

    String code = IDCompress.compressID(phone)[0].toLowerCase();
    smsSender.send(phone, String.format(SystemConstant.VERIFICATION_MESSAGE, code));
    
    vc = new VerificationCode();
    vc.setCode(code);
    vc.setSendTime(System.currentTimeMillis());
    
    cache.set(cacheKey, vc, SystemConstant.VERIFICATION_CODE_CACHE_TIME);
    
    return response;
  }

  /**
   * User login
   */
  public LoginResponse login(LoginRequest login) {
    LoginResponse response = new LoginResponse();

    String userName = login.getUserName();
    if (StringUtils.isBlank(userName)) {
      response.setCode(APIConstant.RETURN_CODE_PARAMETER_NOT_FOUND);
      return response;
    }

    String passwordHash = login.getPasswordHash();
    if (StringUtils.isBlank(passwordHash)) {
      response.setCode(APIConstant.RETURN_CODE_PARAMETER_NOT_FOUND);
      return response;
    }

    Account account = accountService.getAccountByPhone(userName);
    if (account == null || !account.getPasswordHash().equals(passwordHash)) {
      response.setCode(APIConstant.RETURN_CODE_DATA_NOT_FOUND);
      return response;
    }
    
    User user = userService.getUserByAccountID(account.getId());
    user.setPhone(account.getPhone());
    
    // add to session
    LoginUser loginUser = putSession(user);

    response.setAuthToken(loginUser.getAuthToken());
    BasicUserInfo userInfo = new BasicUserInfo();
    userInfo.setUserID(loginUser.getId());
    userInfo.setAvatar(loginUser.getAvatar());
    userInfo.setName(loginUser.getName());
    userInfo.setNickName(loginUser.getNickName());
    userInfo.setRpValue(loginUser.getRpValue());
    response.setUserInfo(userInfo);
    
    return response;
  }

  /**
   * User logout
   */
  public BasicResponse logout(String token) {
    BasicResponse response = new BasicResponse();
    String key = SystemConstant.LOGIN_USER_CACHE_KEY + token;
    cache.delete(key);
    return response;
  }

  /**
   * User regist
   */
  public LoginResponse regist(RegistRequest regist) {
    LoginResponse response = new LoginResponse();

    String phone = regist.getPhone();
    Account account = accountService.getAccountByPhone(phone);
    if (account != null) {
      response.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return response;
    }

    String passwordHash = regist.getPasswordHash();
    if (StringUtils.isBlank(passwordHash)) {
      response.setCode(APIConstant.RETURN_CODE_PARAMETER_NOT_FOUND);
      return response;
    }

    String code = regist.getCode();
    String cacheKey = SystemConstant.VERIFICATION_CODE_CACHE_KEY + phone;
    VerificationCode vc = (VerificationCode)cache.get(cacheKey);
    // TODO (VerificationCode)cache.get(cacheKey);
    if (vc == null || !vc.getCode().equalsIgnoreCase(code)) {
      response.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return response;
    }

    // init account
    account = new Account();
    account.setSource(SystemConstant.AUTH_ACCOUNT_SOURCE_SYSTEM);
    account.setPhone(phone);
    account.setPasswordHash(passwordHash);
    account.setCreateTime(System.currentTimeMillis());
    accountService.initAccount(account);

    // init user
    User user = new User();
    user.setPhone(phone);
    userService.initUser(user);

    // add to session
    LoginUser loginUser = putSession(user);
    
    response.setAuthToken(loginUser.getAuthToken());
    BasicUserInfo userInfo = new BasicUserInfo();
    userInfo.setUserID(loginUser.getId());
    userInfo.setAvatar(loginUser.getAvatar());
    userInfo.setName(loginUser.getName());
    userInfo.setNickName(loginUser.getNickName());
    userInfo.setRpValue(loginUser.getRpValue());
    response.setUserInfo(userInfo);

    return response;
  }

  public LoginUser getLoginUser(String token) {
    if (StringUtils.isBlank(token)) {
      return null;
    }

    String key = SystemConstant.LOGIN_USER_CACHE_KEY + token;
    LoginUser user = (LoginUser) cache.get(key);
    if (user == null) {
      return null;
    }

    return user;
  }

  public String getSecret(String key) {
    // FIXME UNDO find server secret key
    return null;
  }

  // add to session
  public LoginUser putSession(User user) {
    String token = UUID.randomUUID().toString();
    String key = SystemConstant.LOGIN_USER_CACHE_KEY + token;

    LoginUser login = new LoginUser();
    login.setId(user.getId());
    login.setNickName(user.getNickname());
    login.setLogoUrl(user.getLogo_url());
    login.setUserPhone(user.getPhone());
    login.setAuthToken(token);
    login.setScore(user.getScore());

    boolean b = cache.set(key, login);
    return login;
  }

  public LoginUser resetSession(LoginUser login) {
    String key = SystemConstant.LOGIN_USER_CACHE_KEY + login.getAuthToken();
    cache.set(key, login);
    return login;
  }
  
  private Account initAccount(AuthContext context) {
    Account account = new Account();
    account.setAccount(context.getAccount());
    account.setSource(context.getType());
    account.setSubSource(context.getSource());
    account.setCreateTime(System.currentTimeMillis());

    accountService.initAccount(account);
    return account;
  }

  private User initUser(AuthContext context, String accountID) {
    User user = new User();

    userService.initUser(user);
    return user;
  }

  public void setCache(Cache cache) {
    this.cache = cache;
  }

  public void setAccountService(AccountService accountService) {
    this.accountService = accountService;
  }

//  public void setLenovoid(LenovoID lenovoid) {
//    this.lenovoid = lenovoid;
//  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  public void setFriendService(FriendService friendService) {
    this.friendService = friendService;
  }

  public void setSmsSender(SmsSender smsSender) {
    this.smsSender = smsSender;
  }

}
