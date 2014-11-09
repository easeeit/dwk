package com.dwk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwk.constant.APIConstant;
import com.dwk.exception.DaoException;
import com.dwk.exception.ServiceException;
import com.dwk.model.user.LoginUser;
import com.dwk.service.laud.LaudService;

/**
 * Controller for laud.
 * 
 * @author: xp
 */
@Controller
@RequestMapping("/laud")
public class LaudController extends BaseController {

  @Autowired
  private LaudService laudService;

  @RequestMapping(value = "/update", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String list(HttpServletRequest request, @RequestBody String subject_id,
      @RequestParam String subject_type, @RequestParam String status) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(laudService.update(user, subject_type, subject_id, status));
    } catch (ServiceException sex) {
      return outResponse("accountMobileAuth", sex);
    } catch (DaoException dex) {
      return outResponse("accountMobileAuth", dex);
    } catch (Exception ex) {
      return outResponse("accountMobileAuth", ex);
    }
  }


}
