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
import com.dwk.constant.TradeStatus;
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
@SuppressWarnings({"rawtypes", "unchecked" })
public class TradeController extends BaseController {

  @Autowired
  private TradeService tradeService;

  @RequestMapping(value = {"/list/{pageNum}/{rowNum}", "/list/{pageNum}", "/list"}, produces = APIConstant.CONTENT_TYPE_JSON)
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

  @RequestMapping(value = "/close", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String close(HttpServletRequest request, @RequestParam(required = false) String id) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(tradeService.updateTradeStatus(user, id, TradeStatus.close.getValue()));
    } catch (ServiceException sex) {
      return outResponse("trade close", sex);
    } catch (DaoException dex) {
      return outResponse("trade close", dex);
    } catch (Exception ex) {
      return outResponse("trade close", ex);
    }
  }

  @RequestMapping(value = "/delete", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String delete(HttpServletRequest request, @RequestParam(required = false) String id) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(tradeService.updateTradeStatus(user, id, TradeStatus.delete.getValue()));
    } catch (ServiceException sex) {
      return outResponse("trade delete", sex);
    } catch (DaoException dex) {
      return outResponse("trade delete", dex);
    } catch (Exception ex) {
      return outResponse("trade delete", ex);
    }
  }
  
  @RequestMapping(value = "/refresh", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String refresh(HttpServletRequest request, @RequestParam(required = false) String id) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(tradeService.refresh(user, id));
    } catch (ServiceException sex) {
      return outResponse("trade delete", sex);
    } catch (DaoException dex) {
      return outResponse("trade delete", dex);
    } catch (Exception ex) {
      return outResponse("trade delete", ex);
    }
  }

}
