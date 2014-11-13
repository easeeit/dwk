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
import com.dwk.service.trade.TradeService;

/**
 * Controller for trade.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
@Controller
@RequestMapping("/trade")
@SuppressWarnings("rawtypes")
public class TradeController extends BaseController {

  @Autowired
  private TradeService tradeService;

  @RequestMapping(value = { "/list/{pageNum}/{rowNum}", "/list/{pageNum}", "/list" }, produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String list(HttpServletRequest request) throws Exception {
    try {
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
      return outResponse(tradeService.list(pageNum, rowNum));
    } catch (ServiceException sex) {
      return outResponse("trade list", sex);
    } catch (DaoException dex) {
      return outResponse("trade list", dex);
    } catch (Exception ex) {
      return outResponse("trade list", ex);
    }
  }

  @RequestMapping(value = "/create", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String create(HttpServletRequest request) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(tradeService.create(user, request.getParameterMap()));
    } catch (ServiceException sex) {
      return outResponse("trade create", sex);
    } catch (DaoException dex) {
      return outResponse("trade create", dex);
    } catch (Exception ex) {
      return outResponse("trade create", ex);
    }
  }

  @RequestMapping(value = "/update", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String update(HttpServletRequest request) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(tradeService.update(user, request.getParameterMap()));
    } catch (ServiceException sex) {
      return outResponse("trade update", sex);
    } catch (DaoException dex) {
      return outResponse("trade update", dex);
    } catch (Exception ex) {
      return outResponse("trade update", ex);
    }
  }

  @RequestMapping(value = "/update_status", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String updateStatus(HttpServletRequest request, @RequestParam(required=false) String id, @RequestParam(required=false) String status)
      throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(tradeService.updateTradeStatus(user, id, status));
    } catch (ServiceException sex) {
      return outResponse("trade update status", sex);
    } catch (DaoException dex) {
      return outResponse("trade update status", dex);
    } catch (Exception ex) {
      return outResponse("trade update status", ex);
    }
  }

}
