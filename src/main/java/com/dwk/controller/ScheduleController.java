package com.dwk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwk.constant.APIConstant;
import com.dwk.constant.SystemConstant;
import com.dwk.exception.DaoException;
import com.dwk.exception.ServiceException;
import com.dwk.service.product.ProductService;

/**
 * Controller for product.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController extends BaseController {

  @Autowired
  private ProductService productService;

  @RequestMapping(value = "/list/{pageNum}/{rowNum}", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String list(HttpServletRequest request, @PathVariable String pageNum, @PathVariable String rowNum) throws Exception {
    try {
      Integer pn = SystemConstant.PN;
      Integer rn = SystemConstant.RN;
      if (pageNum != null && !"0".equals(pageNum)) {
        pn = Integer.parseInt(pageNum);
      }
      if (rowNum != null) {
        rn = Integer.parseInt(rowNum);
      }
      return outResponse(productService.scheduleList(null, pn, rn));
    } catch (ServiceException sex) {
      return outResponse("schedule list", sex);
    } catch (DaoException dex) {
      return outResponse("schedule list", dex);
    } catch (Exception ex) {
      return outResponse("schedule list", ex);
    }
  }

}
