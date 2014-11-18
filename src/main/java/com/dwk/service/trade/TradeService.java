package com.dwk.service.trade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.dwk.common.Cache;
import com.dwk.constant.APIConstant;
import com.dwk.constant.SystemConstant;
import com.dwk.constant.TradeStatus;
import com.dwk.dao.MongodbDao;
import com.dwk.model.BasicResponse;
import com.dwk.model.trade.Trade;
import com.dwk.model.trade.TradeInfo;
import com.dwk.model.trade.TradeListResponse;
import com.dwk.model.trade.TradeResponse;
import com.dwk.model.user.LoginUser;
import com.dwk.model.user.User;
import com.dwk.service.user.UserService;

public class TradeService {

  private Cache cache;
  private MongodbDao dao;
  private UserService userService;
  
  // TODO 缓存
  public TradeListResponse list(int pageNum, int rowNum) {
    TradeListResponse res = new TradeListResponse();
    List<TradeInfo> result = dao.selectList("getTradeList", null, rowNum, (pageNum - 1) * rowNum);
    for (TradeInfo t : result) {
      User u = userService.getUserByID(t.getUser_id());
      if (u != null) {
        t.setNickname(u.getNickname());
      }
    }
    res.setTrade(result);
    return res;
  }
  
  // TODO 清缓存
  public TradeResponse create(LoginUser user, Map<String, Object> param) {
    TradeResponse res = new TradeResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    // TODO 校验参数
    Trade trade = Trade.create(user, param);
    String tradeID = dao.insert("createTrade", trade);
    if (StringUtils.isBlank(tradeID)) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    } else {
      res.setId(tradeID);
    }
    return res;
  }
  
  public BasicResponse update(LoginUser user, Map<String, Object> param) {
    BasicResponse res = new BasicResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    if (param.get("id") == null ){
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    Trade trade = Trade.update(user, param);
    int count = dao.update("updateTrade", trade);
    if (count <= 0 ) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    }
    return res;
  }
  
  public BasicResponse updateTradeStatus(LoginUser user, String tradeID, String status) {
    BasicResponse res = new BasicResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    if (StringUtils.isBlank(tradeID) || !TradeStatus.valid(status)){
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    Map<String, Object> map = new HashMap<String, Object>(3);
    map.put("user_id", user.getId());
    map.put("tradeID", tradeID);
    map.put("status", status);
    map.put("update_time", System.currentTimeMillis());
    int count = dao.update("updateTradeStatus", map);
    if (count <= 0 ) {
      res.setCode(APIConstant.RETURN_CODE_DATA_NOT_FOUND);
    }
    return res;
  }
  
  public BasicResponse refresh(LoginUser user, String tradeID) {
    BasicResponse res = new BasicResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    if (StringUtils.isBlank(tradeID)){
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    //  已签到过滤
    Object o = (Object)cache.get(SystemConstant.REFRESH_TRADE_CACHE_KEY+tradeID);
    if (o != null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    Map<String, Object> map = new HashMap<String, Object>(3);
    map.put("user_id", user.getId());
    map.put("tradeID", tradeID);
    map.put("update_time", System.currentTimeMillis());
    int count = dao.update("refreshTrade", map);
    if (count == 1 ) {
      // 设置当天 已签到 标志
      cache.setToday(SystemConstant.REFRESH_TRADE_CACHE_KEY+tradeID,"1");
    } else {
      res.setCode(APIConstant.RETURN_CODE_DATA_NOT_FOUND);
    }
    return res;
  }
  
  // TODO cache
  public TradeListResponse getUserTrade(LoginUser user, int pageNum, int rowNum) {
    TradeListResponse res = new TradeListResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    List<TradeInfo> result = dao.selectList("getUserTrade", user.getId(), rowNum, (pageNum - 1) * rowNum);
    for (TradeInfo t : result) {
      User u= userService.getUserByID(t.getUser_id());
      if (u != null) {
        t.setNickname(u.getNickname());
      }
    }
    res.setTrade(result);
    return res;
  }
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
  public void setCache(Cache cache) {
    this.cache = cache;
  }

}
