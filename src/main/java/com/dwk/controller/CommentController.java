package com.dwk.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

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
@SuppressWarnings("rawtypes")
public class CommentController extends BaseController {

  @Autowired
  private CommentService commentService;

  @RequestMapping(value = {"/list/{subjectID}/{pageNum}/{rowNum}",
          "/list/{subjectID}/{pageNum}",
          "/list/{subjectID}",
          "/list"
  }, produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String list(HttpServletRequest request) throws Exception {
    try {
      Integer pageNum = DataConstant.PN;
      Integer rowNum= DataConstant.RN;
      String subjectID = null;
      Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
      if (pathVariables != null) {
        if (pathVariables.containsKey("pageNum")) {
          pageNum = NumberUtils.toInt("" + pathVariables.get("pageNum"), pageNum);
          pageNum = pageNum > 0 ? pageNum : DataConstant.PN;
        }
        if (pathVariables.containsKey("rowNum")) {
          rowNum = NumberUtils.toInt("" + pathVariables.get("rowNum"), rowNum);
          rowNum = rowNum > 0 ? rowNum : DataConstant.RN;
        }
        if (pathVariables.containsKey("subjectID")) {
          subjectID = "" + pathVariables.get("subjectID");
        }
      }
      return outResponse(commentService.list(subjectID, pageNum, rowNum));
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
  public String create(HttpServletRequest request, @RequestParam(required = false) String subject_type, 
          @RequestParam(required = false) String subject_id, @RequestParam(required = false) String content, 
          @RequestParam(required = false) Long cluster) throws Exception {
    try {
      LoginUser user = getUser();
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
  public String update(HttpServletRequest request, @RequestParam(required = false) String id, 
          @RequestParam(required = false) String content) throws Exception {
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
  public String delete(HttpServletRequest request, @RequestParam(required = false) String id) throws Exception {
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
