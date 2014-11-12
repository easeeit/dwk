package com.dwk.service.attention;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.dwk.constant.APIConstant;
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
  
  public BasicResponse create(LoginUser user, String productID) {
    BasicResponse res = new BasicResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    if (!StringUtils.isBlank(productID)) {
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
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
      scheduleService.updateScheduleHot(productID);
    }
    return res;
  }
  
  public BasicResponse delete(LoginUser user, String productID) {
    BasicResponse res = new BasicResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    if (!StringUtils.isBlank(productID)) {
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    Map<String, Object> map = new HashMap<String, Object>(3);
    map.put("userID", user.getId());
    map.put("productID", productID);
    int count = dao.delete("deleteUserAttention", map);
    if (count <= 0 ) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    }
    return res;
  }
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }

  public void setScheduleService(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

}
