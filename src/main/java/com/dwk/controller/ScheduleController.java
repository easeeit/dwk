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
import com.dwk.constant.OrderBy;
import com.dwk.exception.DaoException;
import com.dwk.exception.ServiceException;
import com.dwk.model.product.ScheduleTopResponse;
import com.dwk.service.product.ProductService;
import com.dwk.service.product.ScheduleService;

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
  @Autowired
  private ScheduleService scheduleService;

  @RequestMapping(value = "/will/{pageNum}/{rowNum}", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String will(HttpServletRequest request, @PathVariable String pageNum, @PathVariable String rowNum,
      @RequestParam String order) throws Exception {
    try {
      Integer pn = DataConstant.PN;
      Integer rn = DataConstant.RN;
      if (pageNum != null && !"0".equals(pageNum)) {
        pn = Integer.parseInt(pageNum);
      }
      if (rowNum != null) {
        rn = Integer.parseInt(rowNum);
      }
      OrderBy ob = OrderBy.parse(order);
      return outResponse(scheduleService.willList(pn, rn, ob)); 
    } catch (ServiceException sex) {
      return outResponse("schedule list", sex);
    } catch (DaoException dex) {
      return outResponse("schedule list", dex);
    } catch (Exception ex) {
      return outResponse("schedule list", ex);
    }
  }
  
  @RequestMapping(value = "/done/{pageNum}/{rowNum}", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String done(HttpServletRequest request, @PathVariable String pageNum, @PathVariable String rowNum,
      @RequestParam String order) throws Exception {
    try {
      Integer pn = DataConstant.PN;
      Integer rn = DataConstant.RN;
      if (pageNum != null && !"0".equals(pageNum)) {
        pn = Integer.parseInt(pageNum);
      }
      if (rowNum != null) {
        rn = Integer.parseInt(rowNum);
      }
      OrderBy ob = OrderBy.parse(order);
      return outResponse(scheduleService.doneList(pn, rn, ob)); 
    } catch (ServiceException sex) {
      return outResponse("schedule list", sex);
    } catch (DaoException dex) {
      return outResponse("schedule list", dex);
    } catch (Exception ex) {
      return outResponse("schedule list", ex);
    }
  }
  
  @RequestMapping(value = "/top/{topNum}", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String list(HttpServletRequest request, @PathVariable String topNum) throws Exception {
    try {
      Integer topCount = DataConstant.SCHEDULE_TOP_COUNT;
      if (topNum != null && !"0".equals(topNum)) {
        topCount = Integer.parseInt(topNum);
      }
      ScheduleTopResponse res = new ScheduleTopResponse();
      res.setWill(scheduleService.willList(1, topCount, OrderBy.time).getWill());
      res.setDone(scheduleService.doneList(1, topCount, OrderBy.time).getDone());
      return outResponse(res);
    } catch (ServiceException sex) {
      return outResponse("schedule list", sex);
    } catch (DaoException dex) {
      return outResponse("schedule list", dex);
    } catch (Exception ex) {
      return outResponse("schedule list", ex);
    }
  }

}
