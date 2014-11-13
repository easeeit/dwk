package com.dwk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwk.constant.APIConstant;
import com.dwk.exception.DaoException;
import com.dwk.exception.ServiceException;
import com.dwk.model.user.LoginUser;
import com.dwk.service.user.FeedbackService;

/**
 * Controller for laud.
 * 
 * @author: xp
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController extends BaseController {

  @Autowired
  private FeedbackService feedbackService;

  @RequestMapping(value = "/create", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String update(HttpServletRequest request, @RequestParam(required=false) String contact,
      @RequestParam(required=false) String content) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(feedbackService.createFeedback(user, contact, content));
    } catch (ServiceException sex) {
      return outResponse("feedback create", sex);
    } catch (DaoException dex) {
      return outResponse("feedback create", dex);
    } catch (Exception ex) {
      return outResponse("feedback create", ex);
    }
  }


}
