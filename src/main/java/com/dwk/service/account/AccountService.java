package com.dwk.service.account;

import com.dwk.dao.MongodbDao;
import com.dwk.model.auth.AuthContext;
import com.dwk.model.user.Account;

/**
 * Individual account info,recharge,withdrawals and system gold coin.
 * 
 * @author: xp
 * @data : 2014-8-28
 * @since : 1.5
 */
public class AccountService {

  private MongodbDao dao;
  
  public Object getAccountInfo() {
    return null;
  }

  public Account getAccountByPhone(String phone) {
    return dao.selectOne("getAccountByPhone", phone);
  }
  
  public Account getAccountByAuthInfo(AuthContext context) {
    return dao.selectOne("getAccountByAuthInfo", context);
  }
  
  public String initAccount(Account account) {
    String id = dao.insert("initAccount", account);
    account.setId(id);
    return id;
  }
  
  public void updateLastLoginTime(String accountID, long time) {
    Account account = new Account();
    account.setId(accountID);
    account.setLastLoginTime(time);
    dao.update("updateLastLoginTime", account);
  }
  
  public void updateLastOperateTime(String accountID, long time) {
    Account account = new Account();
    account.setId(accountID);
    account.setLastOperateTime(time);
    dao.update("updateLastOperateTime", account);
  }
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }

}
