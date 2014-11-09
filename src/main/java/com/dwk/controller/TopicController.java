package com.dwk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwk.constant.APIConstant;
import com.dwk.constant.DataConstant;
import com.dwk.exception.DaoException;
import com.dwk.exception.ServiceException;
import com.dwk.model.user.LoginUser;
import com.dwk.service.topic.TopicService;

/**
 * Controller for topic.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
@Controller
@RequestMapping("/topic")
public class TopicController extends BaseController {

  @Autowired
  private TopicService topicService;

  @RequestMapping(value = "/list/{pageNum}/{rowNum}", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String list(HttpServletRequest request, @PathVariable String pageNum, @PathVariable String rowNum) throws Exception {
    try {
      Integer pn = DataConstant.PN;
      Integer rn = DataConstant.RN;
      if (pageNum != null && !"0".equals(pageNum)) {
        pn = Integer.parseInt(pageNum);
      }
      if (rowNum != null) {
        rn = Integer.parseInt(rowNum);
      }
      return outResponse(topicService.list(pn, rn));
    } catch (ServiceException sex) {
      return outResponse("topic list", sex);
    } catch (DaoException dex) {
      return outResponse("topic list", dex);
    } catch (Exception ex) {
      return outResponse("topic list", ex);
    }
  }

  @RequestMapping(value = "/create", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String create(HttpServletRequest request, @RequestParam String subject_type,
      @RequestParam String subject_id, @RequestParam String content , @RequestParam String parent_id ) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(topicService.create(user, subject_type, subject_id, content, parent_id));
    } catch (ServiceException sex) {
      return outResponse("topic create", sex);
    } catch (DaoException dex) {
      return outResponse("topic create", dex);
    } catch (Exception ex) {
      return outResponse("topic create", ex);
    }
  }

  @RequestMapping(value = "/update", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String update(HttpServletRequest request, @RequestParam String id, 
      @RequestParam String content, @RequestParam String title, @RequestParam String tag ) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(topicService.update(user, id, title, content, tag));
    } catch (ServiceException sex) {
      return outResponse("topic update", sex);
    } catch (DaoException dex) {
      return outResponse("topic update", dex);
    } catch (Exception ex) {
      return outResponse("topic update", ex);
    }
  }

  @RequestMapping(value = "/delete", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String delete(HttpServletRequest request, @RequestParam String id) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(topicService.delete(user, id));
    } catch (ServiceException sex) {
      return outResponse("topic delete", sex);
    } catch (DaoException dex) {
      return outResponse("topic delete", dex);
    } catch (Exception ex) {
      return outResponse("topic delete", ex);
    }
  }

 

}
