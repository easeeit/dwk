package com.dwk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwk.constant.APIConstant;
import com.dwk.constant.SystemConstant;
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
public class TradeController extends BaseController {

  @Autowired
  private TradeService tradeService;

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
      return outResponse(tradeService.list(pn, rn));
    } catch (ServiceException sex) {
      return outResponse("trade list", sex);
    } catch (DaoException dex) {
      return outResponse("trade list", dex);
    } catch (Exception ex) {
      return outResponse("trade list", ex);
    }
  }

  @RequestMapping(value = "/info/{tradeID}", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String info(HttpServletRequest request, @PathVariable String tradeID) throws Exception {
    try {
      LoginUser user = getUser();
      return outResponse(tradeService.info(tradeID));
    } catch (ServiceException sex) {
      return outResponse("trade info", sex);
    } catch (DaoException dex) {
      return outResponse("trade info", dex);
    } catch (Exception ex) {
      return outResponse("trade info", ex);
    }
  }
  
  @RequestMapping(value = "/create", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String create(HttpServletRequest request ) throws Exception {
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
  public String updateStatus(HttpServletRequest request, @RequestParam String id, @RequestParam String status) throws Exception {
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
