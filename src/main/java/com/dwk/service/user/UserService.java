package com.dwk.service.user;

import java.util.Arrays;
import java.util.List;

import com.dwk.dao.MongodbDao;
import com.dwk.model.user.BasicUserInfo;
import com.dwk.model.user.User;

/**
 * User info service.
 * 
 * @author: xp
 * @data : 2014-8-28
 * @since : 1.5
 */
public class UserService {

  private MongodbDao dao;
  
  public String initUser(User user) {
    String id = dao.insert("initUser", user);
    user.setId(id);
    return id;
  }
  
  public User getUserByAccountID(String accountID) {
    return dao.selectOne("getUserByAccountID", accountID);
  }
  
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
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }
  
}
