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
import com.dwk.service.product.ProductService;

/**
 * Controller for product.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
@Controller
@RequestMapping("/product")
@SuppressWarnings("rawtypes")
public class ProductController extends BaseController {

  @Autowired
  private ProductService productService;

  @RequestMapping(value = {"/info/{productID}", "/info"}, produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String info(HttpServletRequest request) throws Exception {
    try {
      String productID = null;
      Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
      if (pathVariables != null) {
        if (pathVariables.containsKey("productID")) {
          productID = "" + pathVariables.get("productID") ;
        }
      }
      return outResponse(productService.getProduct(productID)); 
    } catch (ServiceException sex) {
      return outResponse("product info", sex);
    } catch (DaoException dex) {
      return outResponse("product info", dex);
    } catch (Exception ex) {
      return outResponse("product info", ex);
    }
  }
  
  @RequestMapping(value = {"/news/{productID}/{pageNum}/{rowNum}" ,"/news/{productID}/{pageNum}", "/news/{productID}", "/news"}, produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String news(HttpServletRequest request) throws Exception {
    try {
      Integer pageNum = DataConstant.PN;
      Integer rowNum = DataConstant.RN;
      String productID = null;
      Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
      if (pathVariables != null) {
        if (pathVariables.containsKey("productID")) {
          productID = "" + pathVariables.get("productID") ;
        }
        if (pathVariables.containsKey("pageNum")) {
          pageNum = NumberUtils.toInt("" + pathVariables.get("pageNum"), pageNum);
          pageNum = pageNum > 0 ? pageNum : DataConstant.PN;
        }
        if (pathVariables.containsKey("rowNum")) {
          rowNum = NumberUtils.toInt("" + pathVariables.get("rowNum"), rowNum);
          rowNum = rowNum > 0 ? rowNum : DataConstant.RN;
        }
      }
      return outResponse(productService.getNewsList(productID, pageNum, rowNum)); 
    } catch (ServiceException sex) {
      return outResponse("product news list", sex);
    } catch (DaoException dex) {
      return outResponse("product news list", dex);
    } catch (Exception ex) {
      return outResponse("product news list", ex);
    }
  }
  
  @RequestMapping(value = {"/tip/{productID}/{pageNum}/{rowNum}" ,"/tip/{productID}/{pageNum}", "/tip/{productID}", "/tip"}, produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String tip(HttpServletRequest request) throws Exception {
    try {
      Integer pageNum = DataConstant.PN;
      Integer rowNum = DataConstant.RN;
      String productID = null;
      Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
      if (pathVariables != null) {
        if (pathVariables.containsKey("productID")) {
          productID = "" + pathVariables.get("productID") ;
        }
        if (pathVariables.containsKey("pageNum")) {
          pageNum = NumberUtils.toInt("" + pathVariables.get("pageNum"), pageNum);
          pageNum = pageNum > 0 ? pageNum : DataConstant.PN;
        }
        if (pathVariables.containsKey("rowNum")) {
          rowNum = NumberUtils.toInt("" + pathVariables.get("rowNum"), rowNum);
          rowNum = rowNum > 0 ? rowNum : DataConstant.RN;
        }
      }
      return outResponse(productService.getTipList(productID, pageNum, rowNum)); 
    } catch (ServiceException sex) {
      return outResponse("product news list", sex);
    } catch (DaoException dex) {
      return outResponse("product news list", dex);
    } catch (Exception ex) {
      return outResponse("product news list", ex);
    }
  }
  
  @RequestMapping(value = "/score", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String score(HttpServletRequest request, @RequestParam(required=false) String id, @RequestParam(required=false) Double score) throws Exception {
    try {
      return outResponse(productService.score(id, score)); 
    } catch (ServiceException sex) {
      return outResponse("product news list", sex);
    } catch (DaoException dex) {
      return outResponse("product news list", dex);
    } catch (Exception ex) {
      return outResponse("product news list", ex);
    }
  }
  
}
