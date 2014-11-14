package com.dwk.service.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.dwk.constant.APIConstant;
import com.dwk.constant.DataConstant;
import com.dwk.constant.SubjectType;
import com.dwk.dao.MongodbDao;
import com.dwk.model.BasicResponse;
import com.dwk.model.comment.Comment;
import com.dwk.model.comment.CommentInfo;
import com.dwk.model.comment.CommentListResponse;
import com.dwk.model.comment.CommentResponse;
import com.dwk.model.comment.UserCommentInfo;
import com.dwk.model.comment.UserCommentListResponse;
import com.dwk.model.user.LoginUser;
import com.dwk.service.product.ScheduleService;

/**
 * User product deal service.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class CommentService {

  private MongodbDao dao;
  private ScheduleService scheduleService;
  
  public CommentListResponse list(String subjectID, int pageNum, int rowNum) {
    CommentListResponse res = new CommentListResponse();
    if (StringUtils.isBlank(subjectID)) {
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    Map<String, Object> param = new HashMap<String, Object>(1);
    param.put("subject_id", subjectID);
    List<CommentInfo> result = dao.selectList("getCommentList", param, rowNum, (pageNum - 1) * rowNum);
    res.setComment(result);
    return res;
  }
  
  // TOTO 缓存
  public List<CommentInfo> getHotComment(String subjectID) {
    if (StringUtils.isBlank(subjectID)) {
      return null;
    }
    return dao.selectList("getHotComment", subjectID, DataConstant.HOT_COMMENT_COUNT, 0);
  }
  
  public CommentResponse create(LoginUser user, String subjectType, String subjectID, String content, String p_id) {
    CommentResponse res = new CommentResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    if (StringUtils.isBlank(subjectType) || !SubjectType.valid(subjectType) || StringUtils.isBlank(subjectID) || StringUtils.isBlank(content)) {
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    String p_uid = null;
    String p_content = null;
    if (p_id != null) {
      Comment parent = getCommentByID(p_id);
      if (parent != null) {
        p_uid = parent.getUser_id();
        p_content = parent.getContent();
      }
    }
    Comment comment = Comment.create(user, subjectID, subjectType, content, p_id, p_uid, p_content);
    String commentID = dao.insert("createComment", comment);
    if (StringUtils.isBlank(commentID)) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    } else {
      res.setId(commentID);
      // TODO 增加评论数/热度
      updateCommentCount(subjectType, subjectID, 1);
      if (StringUtils.isBlank(p_id)) {
        // 更新 p_uid 为自己,方便检索"我参与的回复"
        Map<String,Object> map = new HashMap<String, Object>(2);
        map.put("id", commentID);
        map.put("p_uid", user.getId());
        dao.update("updateCommentAfterCreate", map);
      }
    }
    return res;
  }
  
  public BasicResponse update(LoginUser user, String commentID, String content) {
    BasicResponse res = new BasicResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    if (StringUtils.isBlank(commentID) || StringUtils.isBlank(content)) {
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    Map<String, Object> param = new HashMap<String, Object>(3);
    param.put("id", commentID);
    param.put("content", content);
    param.put("user_id", user.getId());
    param.put("create_time", System.currentTimeMillis());
    int count = dao.update("updateComment", param);
    if (count <= 0 ) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    }
    return res;
  }

  public BasicResponse delete(LoginUser user, String commentID) {
    BasicResponse res = new BasicResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    if (StringUtils.isBlank(commentID)) {
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    Map<String , Object> param = new HashMap<String, Object>(1);
    param.put("id", commentID);
    param.put("user_id", user.getId());
    int count = dao.update("deleteComment", param);
    if (count <= 0 ) {
      res.setCode(APIConstant.RETURN_CODE_ERROR);
    } else {
      // 减少数量
      //updateCommentCount(subjectType, subjectID, count)
    }
    return res;
  }
  
  /**
   * 更新评论数
   * @param subjectType
   * @param subjectID
   */
  public void updateCommentCount(String subjectType, String subjectID, int count) {
    if (subjectID == null || subjectType == null) {
      return ;
    }
    String sql = null;
    Map<String, Object> map = new HashMap<String, Object>(2);
    map.put("subjectID", subjectID);
    map.put("count", count);
    if (SubjectType.article.getValue().equals(subjectType)) { // 文章
      sql = "updateArticleCommentCount";
    } else if (SubjectType.topic.getValue().equals(subjectType)) { // 话题
      sql = null;
    } else if (SubjectType.trade.getValue().equals(subjectType)) { // 交易
      sql = "updateTradeCommentCount";
    } else if (SubjectType.comment.getValue().equals(subjectType)) { // 评论
      sql = "updateCommentCommentCount";
    } else if (SubjectType.product.getValue().equals(subjectType)) { // 产品
      sql = "updateProductCommentCount";
    } else if (SubjectType.user.getValue().equals(subjectType)){ // 用户
      sql = null;
    }
    if (sql != null) {
      dao.update(sql, map);
     if (SubjectType.product.getValue().equals(subjectType)) { // 产品
       scheduleService.updateScheduleHot(subjectID);
     }
    }
    return;
  }
  
  // TODO 短期缓存
  public UserCommentListResponse getUserComment(LoginUser user, int pageNum, int rowNum) {
    UserCommentListResponse res = new UserCommentListResponse();
    if (user == null) {
      res.setCode(APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      return res;
    }
    List<UserCommentInfo> result = dao.selectList("getUserComment", user.getId(), rowNum, (pageNum - 1) * rowNum);
    // 加工列表 xx回复了我 / 我评论了...
    
    res.setComment(result);
    return res;
  }
  
  public Comment getCommentByID(String commentID) {
    return dao.selectOne("getCommentByID", commentID);
  }
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }

  public void setScheduleService(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

}
