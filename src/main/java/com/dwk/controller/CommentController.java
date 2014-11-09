package com.dwk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwk.constant.APIConstant;
import com.dwk.constant.DataConstant;
import com.dwk.exception.DaoException;
import com.dwk.exception.ServiceException;
import com.dwk.model.user.LoginUser;
import com.dwk.service.comment.CommentService;

/**
 * Controller for comment.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {

  @Autowired
  private CommentService commentService;

  @RequestMapping(value = "/list/{subjectID}/{pageNum}/{rowNum}", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String list(HttpServletRequest request, @RequestBody String subjectID, 
      @PathVariable String pageNum, @PathVariable String rowNum) throws Exception {
    try {
      Integer pn = DataConstant.PN;
      Integer rn = DataConstant.RN;
      if (pageNum != null && !"0".equals(pageNum)) {
        pn = Integer.parseInt(pageNum);
      }
      if (rowNum != null) {
        rn = Integer.parseInt(rowNum);
      }
      return outResponse(commentService.list(subjectID, pn, rn));
    } catch (ServiceException sex) {
      return outResponse("comment list", sex);
    } catch (DaoException dex) {
      return outResponse("comment list", dex);
    } catch (Exception ex) {
      return outResponse("comment list", ex);
    }
  }

  @RequestMapping(value = "/create", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String create(HttpServletRequest request, @RequestParam String subject_type,
      @RequestParam String subject_id, @RequestParam String content , @RequestParam String strCluster ) throws Exception {
    try {
      LoginUser user = getUser();
      Long cluster = null;
      if (strCluster != null) {
        cluster = Long.parseLong(strCluster);
      }
      return outResponse(commentService.create(user, subject_type, subject_id, content, cluster));
    } catch (ServiceException sex) {
      return outResponse("comment create", sex);
    } catch (DaoException dex) {
      return outResponse("comment create", dex);
    } catch (Exception ex) {
      return outResponse("comment create", ex);
    }
  }

  @RequestMapping(value = "/update", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String update(HttpServletRequest request, @RequestParam String id, @RequestParam String content ) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(commentService.update(user, id, content));
    } catch (ServiceException sex) {
      return outResponse("comment update", sex);
    } catch (DaoException dex) {
      return outResponse("comment update", dex);
    } catch (Exception ex) {
      return outResponse("comment update", ex);
    }
  }

  @RequestMapping(value = "/delete", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String delete(HttpServletRequest request, @RequestParam String id) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(commentService.delete(user, id));
    } catch (ServiceException sex) {
      return outResponse("comment delete", sex);
    } catch (DaoException dex) {
      return outResponse("comment delete", dex);
    } catch (Exception ex) {
      return outResponse("comment delete", ex);
    }
  }

 

}
