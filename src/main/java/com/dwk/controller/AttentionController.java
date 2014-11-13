package com.dwk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwk.constant.APIConstant;
import com.dwk.constant.AttentionStatus;
import com.dwk.exception.DaoException;
import com.dwk.exception.ServiceException;
import com.dwk.model.user.LoginUser;
import com.dwk.service.attention.AttentionService;

/**
 * Controller for attention.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
@Controller
@RequestMapping("/attention")
public class AttentionController extends BaseController {

  @Autowired
  private AttentionService attentionService;

  @RequestMapping(value = "/update", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String update(HttpServletRequest request, @RequestParam(required=false) String product_id, 
          @RequestParam(required=false) String status) throws Exception {
    try {
      LoginUser user = getUser();
      AttentionStatus as = AttentionStatus.parse(status);
      return outResponse(attentionService.update(user, product_id, as));
    } catch (ServiceException sex) {
      return outResponse("attention create", sex);
    } catch (DaoException dex) {
      return outResponse("attention create", dex);
    } catch (Exception ex) {
      return outResponse("attention create", ex);
    }
  }


}
