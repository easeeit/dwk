package com.dwk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwk.constant.APIConstant;
import com.dwk.exception.DaoException;
import com.dwk.exception.ServiceException;
import com.dwk.service.deal.DealService;

/**
 * Controller for product deal.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
@Controller
@RequestMapping("/deal")
public class DealController extends BaseController {

  @Autowired
  private DealService dealService;

  @RequestMapping(value = "/lend", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String lend(HttpServletRequest request, @RequestBody String productID) throws Exception {
    try {
      return "111";
    } catch (ServiceException sex) {
      return outResponse("lend", sex);
    } catch (DaoException dex) {
      return outResponse("lend", dex);
    } catch (Exception ex) {
      return outResponse("lend", ex);
    }
  }

  @RequestMapping(value = "/borrow", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String borrow(HttpServletRequest request, @RequestBody String productID) throws Exception {
    try {
      return "";
    } catch (ServiceException sex) {
      return outResponse("borrow", sex);
    } catch (DaoException dex) {
      return outResponse("borrow", dex);
    } catch (Exception ex) {
      return outResponse("borrow", ex);
    }
  }

 

}
