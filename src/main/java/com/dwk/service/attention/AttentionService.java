package com.dwk.service.attention;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.dwk.constant.APIConstant;
import com.dwk.constant.AttentionStatus;
import com.dwk.dao.MongodbDao;
import com.dwk.model.BasicResponse;
import com.dwk.model.attention.Attention;
import com.dwk.model.user.LoginUser;
import com.dwk.service.product.ScheduleService;

/**
 * Attention service.
 * 
 * @author: xp
 */
public class AttentionService {

  private MongodbDao dao;
  private ScheduleService scheduleService;
  
  // TODO 清产品缓存
  public BasicResponse update(LoginUser user, String productID, AttentionStatus status) {
    BasicResponse res = new BasicResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    if (StringUtils.isBlank(productID) || status == null) {
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    switch (status) {
      case on:
        return create(user, productID);
      case off:
        return delete(user, productID);
      default:
        return null;
    }
  }
  
  public BasicResponse create(LoginUser user, String productID) {
    BasicResponse res = new BasicResponse();
    if (checkUserProductAttention(user.getId(), productID)) {
      res.setCode(APIConstant.RETURN_CODE_OK);
      return res;
    }
    Attention att = new Attention();
    att.setCreate_time(System.currentTimeMillis());
    att.setUser_id(user.getId());
    att.setProduct_id(productID);
    String id = dao.insert("createUserAttention", att);
    if (StringUtils.isBlank(id)) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    } else {
      updateProductAttentionCount(productID, 1);
      scheduleService.updateScheduleHot(productID);
    }
    return res;
  }
  
  public BasicResponse delete(LoginUser user, String productID) {
    BasicResponse res = new BasicResponse();
    Map<String, Object> map = new HashMap<String, Object>(3);
    map.put("userID", user.getId());
    map.put("productID", productID);
    int count = dao.delete("deleteUserAttention", map);
    if (count <= 0 ) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    } else {
      updateProductAttentionCount(productID, -1 * count);
      scheduleService.updateScheduleHot(productID);
    }
    return res;
  }
  
  private boolean checkUserProductAttention(String userID, String productID) {
    Map<String, Object> map = new HashMap<String, Object>(3);
    map.put("userID", userID);
    map.put("productID", productID);
    return dao.count("checkUserProductAttention", map) > 0;
  }
  
  private void updateProductAttentionCount(String productID, int count) {
    Map<String,Object> map = new HashMap<String, Object>(2);
    map.put("productID", productID);
    map.put("count", count);
    dao.update("updateProductAttentionCount", map);
  }
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }

  public void setScheduleService(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

}
