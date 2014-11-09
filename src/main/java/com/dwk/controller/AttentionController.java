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

  @RequestMapping(value = "/create", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String create(HttpServletRequest request, @RequestParam String product_id, @RequestParam String status) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(attentionService.create(user, product_id));
    } catch (ServiceException sex) {
      return outResponse("attention create", sex);
    } catch (DaoException dex) {
      return outResponse("attention create", dex);
    } catch (Exception ex) {
      return outResponse("attention create", ex);
    }
  }
  
  @RequestMapping(value = "/delete", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String delete(HttpServletRequest request, @RequestParam String product_id, @RequestParam String status) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(attentionService.delete(user, product_id));
    } catch (ServiceException sex) {
      return outResponse("attention delete", sex);
    } catch (DaoException dex) {
      return outResponse("attention delete", dex);
    } catch (Exception ex) {
      return outResponse("attention delete", ex);
    }
  }


}
