package com.dwk.service.attention;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.dwk.constant.APIConstant;
import com.dwk.constant.AttentionStatus;
import com.dwk.dao.MongodbDao;
import com.dwk.model.BasicResponse;
import com.dwk.model.user.LoginUser;

/**
 * Attention service.
 * 
 * @author: xp
 */
public class AttentionService {

  private MongodbDao dao;
  
  public BasicResponse switchStatus(LoginUser user, String productID, String status) {
    BasicResponse res = new BasicResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    if (!StringUtils.isBlank(productID) && !AttentionStatus.valid(status)) {
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    Map<String, Object> map = new HashMap<String, Object>(3);
    map.put("userID", user.getId());
    map.put("productID", productID);
    map.put("status", status);
    int count = dao.update("updateUserAttention", map);
    if (count <= 0 ) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    }
    return res;
  }
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }

}
