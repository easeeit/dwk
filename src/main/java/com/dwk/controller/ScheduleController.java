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
@SuppressWarnings("rawtypes")
public class ScheduleController extends BaseController {

  @Autowired
  private ScheduleService scheduleService;
  @Autowired
  private ProductService productService;

  @RequestMapping(value = {"/will/{pageNum}/{rowNum}", "/will/{pageNum}", "/will"}, produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String will(HttpServletRequest request, @RequestParam(required=false) String order) throws Exception {
    try {
      OrderBy ob = OrderBy.parse(order);

      Integer pageNum = DataConstant.PN;
      Integer rowNum = DataConstant.RN;
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
      }
      return outResponse(scheduleService.willList(pageNum, rowNum, ob)); 
    } catch (ServiceException sex) {
      return outResponse("schedule will", sex);
    } catch (DaoException dex) {
      return outResponse("schedule will", dex);
    } catch (Exception ex) {
      return outResponse("schedule will", ex);
    }
  }
  
  @RequestMapping(value = {"/done/{pageNum}/{rowNum}", "/done/{pageNum}", "/done"}, produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String done(HttpServletRequest request, @RequestParam(required=false) String order) throws Exception {
    try {
      OrderBy ob = OrderBy.parse(order);

      Integer pageNum = DataConstant.PN;
      Integer rowNum = DataConstant.RN;
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
      }
      return outResponse(scheduleService.doneList(pageNum, rowNum, ob)); 
    } catch (ServiceException sex) {
      return outResponse("schedule done", sex);
    } catch (DaoException dex) {
      return outResponse("schedule done", dex);
    } catch (Exception ex) {
      return outResponse("schedule done", ex);
    }
  }
  
  @RequestMapping(value = {"/top/{topNum}" , "/top"}, produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String list(HttpServletRequest request) throws Exception {
    try {
      Integer topNum = DataConstant.SCHEDULE_TOP_COUNT;
      Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
      if (pathVariables != null) {
        if (pathVariables.containsKey("topNum")) {
          topNum = NumberUtils.toInt("" + pathVariables.get("topNum"), topNum);
          topNum = topNum > 0 ? topNum : DataConstant.SCHEDULE_TOP_COUNT;
        }
      }
      ScheduleTopResponse res = new ScheduleTopResponse();
      res.setWeek_best(productService.getWeekCommend());
      res.setHot(productService.getHotTopN(DataConstant.HOT_COMMENT_COUNT));
      res.setWill(scheduleService.willList(1, topNum, OrderBy.time).getWill());
      res.setDone(scheduleService.doneList(1, topNum, OrderBy.time).getDone());
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
