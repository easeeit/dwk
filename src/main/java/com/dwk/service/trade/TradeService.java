package com.dwk.service.trade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.dwk.constant.APIConstant;
import com.dwk.constant.TradeStatus;
import com.dwk.dao.MongodbDao;
import com.dwk.model.BasicResponse;
import com.dwk.model.trade.Trade;
import com.dwk.model.trade.TradeInfo;
import com.dwk.model.trade.TradeListResponse;
import com.dwk.model.trade.TradeResponse;
import com.dwk.model.user.LoginUser;
import com.dwk.service.user.UserService;

public class TradeService {

  private MongodbDao dao;
  private UserService userService;
  
  public TradeListResponse list(int pageNum, int rowNum) {
    TradeListResponse res = new TradeListResponse();
    List<TradeInfo> result = dao.selectList("getTradeList", null, rowNum, (pageNum - 1) * rowNum);
    // TODO 缓存
    for (TradeInfo t : result) {
      t.setNickname(userService.getUserByID(t.getUser_id()).getNickName());
    }
    res.setTrade(result);
    return res;
  }
  
  public TradeResponse create(LoginUser user, Map<String, Object> param) {
    TradeResponse res = new TradeResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
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
    if (param.get("id") == null ){
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    param.put("user_id", user.getId());
    param.put("update_time", System.currentTimeMillis());
    int count = dao.update("updateTrade", param);
    if (count <= 0 ) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    }
    return res;
  }
  
  public BasicResponse updateTradeStatus(LoginUser user, String tradeID, String status) {
    BasicResponse res = new BasicResponse();
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
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    }
    return res;
  }

  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

}
