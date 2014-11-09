package com.dwk.service.user;

import org.apache.commons.lang.StringUtils;

import com.dwk.constant.APIConstant;
import com.dwk.dao.MongodbDao;
import com.dwk.model.BasicResponse;
import com.dwk.model.user.Feedback;
import com.dwk.model.user.LoginUser;

/**
 * User info service.
 * 
 * @author: xp
 */
public class FeedbackService {

  private MongodbDao dao;
  
  public BasicResponse createFeedback(LoginUser user, String contact, String content) {
    BasicResponse res = new BasicResponse();
    if(StringUtils.isBlank(contact) || StringUtils.isBlank(content)) {
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    Feedback fb = new Feedback();
    fb.setContact(contact);
    fb.setContent(content);
    if (user != null) {
      fb.setUser_id(user.getId());
    }
    String id = dao.insert("createFeedback", fb);
    if (StringUtils.isBlank(id)) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    }
    return res;
  }
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }
  
}
