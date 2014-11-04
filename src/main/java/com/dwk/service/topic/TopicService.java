package com.dwk.service.topic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.dwk.constant.APIConstant;
import com.dwk.constant.Platform;
import com.dwk.dao.MongodbDao;
import com.dwk.model.BasicResponse;
import com.dwk.model.topic.Topic;
import com.dwk.model.topic.TopicListResponse;
import com.dwk.model.topic.TopicResponse;
import com.dwk.model.user.LoginUser;
import com.dwk.service.tag.TagService;

/**
 * Topic service.
 * 
 * @author: xp
 */
public class TopicService {

  private MongodbDao dao;
  private TagService tagService;
  
  public TopicListResponse list(int pageNum, int rowNum) {
    TopicListResponse res = new TopicListResponse();
    List<Topic> result = dao.selectList("getTopicList", null, rowNum, (pageNum - 1) * rowNum);
    res.setTopic(result);
    return res;
  }
  
  public TopicResponse create(LoginUser user, String title, String content, String platform, String tags) {
    TopicResponse res = new TopicResponse();
    if (StringUtils.isBlank(title) || StringUtils.isBlank(content) || !Platform.valid(platform)) {
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    Topic topic = Topic.create(user, title, platform, content);
    
    String topicID = dao.insert("createTopic", topic);
    if (StringUtils.isBlank(topicID)) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    } else {
      res.setId(topicID);
      tagService.updateSubjectTag(topicID, tags);
    }
    return res;
  }
  
  public BasicResponse update(LoginUser user, String topicID, String title, String content, String tags) {
    BasicResponse res = new BasicResponse();
    if (StringUtils.isBlank(topicID) || StringUtils.isBlank(title) || StringUtils.isBlank(content)) {
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    Map<String, Object> param = new HashMap<String, Object>(4);
    param.put("id", topicID);
    param.put("title", title);
    param.put("content", content);
    param.put("create_time", System.currentTimeMillis());
    int count = dao.update("updateTopic", param);
    if (count > 0 ) {
      tagService.updateSubjectTag(topicID, tags);
    } else {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    }
    return res;
  }

  public BasicResponse delete(LoginUser user, String topicID) {
    BasicResponse res = new BasicResponse();
    if (StringUtils.isBlank(topicID)) {
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    Map<String , Object> param = new HashMap<String, Object>(1);
    param.put("id", topicID);
    int count = dao.update("deleteTopic", param);
    if (count <= 0 ) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    }
    return res;
  }
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }
  public void setTagService(TagService tagService) {
    this.tagService = tagService;
  }

}
