package com.dwk.service.laud;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.dwk.constant.APIConstant;
import com.dwk.constant.DataConstant;
import com.dwk.constant.LaudStatus;
import com.dwk.constant.SubjectType;
import com.dwk.dao.MongodbDao;
import com.dwk.model.BasicResponse;
import com.dwk.model.laud.Laud;
import com.dwk.model.user.LoginUser;
import com.dwk.service.product.ScheduleService;

/**
 * User info service.
 * 
 * @author: xp
 */
public class LaudService {

  private MongodbDao dao;
  private ScheduleService scheduleService;
  
  public BasicResponse update(LoginUser user, String subjectType, String subjectID, LaudStatus status) {
    BasicResponse res = new BasicResponse();
    if (StringUtils.isBlank(subjectType) || !SubjectType.valid(subjectType) 
        || StringUtils.isBlank(subjectID) || status == null) {
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    boolean flag = false;
    Laud p = null;
    if (user == null) {
      flag = true; // 未登录用户点赞不记录x_laud表
    } else {
      p = new Laud();
      p.setSubject_id(subjectID);
      p.setSubject_type(subjectType);
      p.setUser_id(user.getId());
      
      //TODO 需校验是否已经赞过
    }
    int laudCount = 0;
    switch (status) {
      case on:
        flag = flag ? flag : (!StringUtils.isBlank(dao.insert("createLaud", p)));
        laudCount = 1;
        break;
      case off:
        flag = flag ? flag : ((laudCount = -1 * dao.delete("deleteLaud", p)) != 0);
        break;
      default:
        break;
    }
    if (!flag) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    } else {
      // update laud_count
      if (SubjectType.article.getValue().equals(subjectType)) {
        incArticleLaudCount(subjectID, laudCount);
      } else if (SubjectType.topic.getValue().equals(subjectType)) {
        incTopicLaudCount(subjectID, laudCount);
      } else if (SubjectType.comment.getValue().equals(subjectType)) {
        incCommentLaudCount(subjectID, laudCount);
      } else if (SubjectType.product.getValue().equals(subjectType)) {
        incProductLaudCount(subjectID, laudCount);
      } else if (SubjectType.user.getValue().equals(subjectType)) {
        // nothing 
      }
    }
    return res;
  }
  
  public void incArticleLaudCount(String articleID, int count) {
    Map<String, Object> map = new HashMap<String, Object>(2);
    map.put("articleID", articleID);
    map.put("count", count);
    dao.update("incArticleLaudCount", map);
  }
  
  public void incTopicLaudCount(String topicID, int count) {
    Map<String, Object> map = new HashMap<String, Object>(2);
    map.put("topicID", topicID);
    map.put("count", count);
    dao.update("incTopicLaudCount", map);
  }
  
  public void incCommentLaudCount(String commentID, int count) {
    Map<String, Object> map = new HashMap<String, Object>(2);
    map.put("commentID", commentID);
    map.put("count", count);
    dao.update("incCommentLaudCount", map);
  }
  
  public void incProductLaudCount(String productID, int count) {
    Map<String, Object> map = new HashMap<String, Object>(2);
    map.put("productID", productID);
    map.put("count", count);
    if (dao.update("incProductLaudCount", map) > 0) {
      scheduleService.updateScheduleHot(productID);
    };
  }
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }

  public void setScheduleService(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }
  
}
