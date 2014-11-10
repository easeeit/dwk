package com.dwk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class ProductController extends BaseController {

  @Autowired
  private ProductService productService;

  @RequestMapping(value = "/info/{productID}", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String info(HttpServletRequest request, @PathVariable String productID) throws Exception {
    try {
      return outResponse(productService.getProduct(productID)); 
    } catch (ServiceException sex) {
      return outResponse("product info", sex);
    } catch (DaoException dex) {
      return outResponse("product info", dex);
    } catch (Exception ex) {
      return outResponse("product info", ex);
    }
  }
  
  @RequestMapping(value = "/news/{productID}/{pageNum}/{rowNum}", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String news(HttpServletRequest request, @PathVariable String productID, 
      @PathVariable String pageNum, @PathVariable String rowNum) throws Exception {
    try {
      Integer pn = DataConstant.PN;
      Integer rn = DataConstant.RN;
      if (pageNum != null && !"0".equals(pageNum)) {
        pn = Integer.parseInt(pageNum);
      }
      if (rowNum != null) {
        rn = Integer.parseInt(rowNum);
      }
      return outResponse(productService.getNewsList(productID, pn, rn)); 
    } catch (ServiceException sex) {
      return outResponse("product news list", sex);
    } catch (DaoException dex) {
      return outResponse("product news list", dex);
    } catch (Exception ex) {
      return outResponse("product news list", ex);
    }
  }
  
  @RequestMapping(value = "/tip/{productID}/{pageNum}/{rowNum}", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String tip(HttpServletRequest request, @PathVariable String productID, 
      @PathVariable String pageNum, @PathVariable String rowNum) throws Exception {
    try {
      Integer pn = DataConstant.PN;
      Integer rn = DataConstant.RN;
      if (pageNum != null && !"0".equals(pageNum)) {
        pn = Integer.parseInt(pageNum);
      }
      if (rowNum != null) {
        rn = Integer.parseInt(rowNum);
      }
      return outResponse(productService.getTipList(productID, pn, rn)); 
    } catch (ServiceException sex) {
      return outResponse("product news list", sex);
    } catch (DaoException dex) {
      return outResponse("product news list", dex);
    } catch (Exception ex) {
      return outResponse("product news list", ex);
    }
  }
  
  @RequestMapping(value = "/commend", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String commend(HttpServletRequest request) throws Exception {
    try {
      return outResponse(productService.getCommend()); 
    } catch (ServiceException sex) {
      return outResponse("product news list", sex);
    } catch (DaoException dex) {
      return outResponse("product news list", dex);
    } catch (Exception ex) {
      return outResponse("product news list", ex);
    }
  }
  
  @RequestMapping(value = "/top/{n}", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String top(HttpServletRequest request, @PathVariable Integer n) throws Exception {
    try {
      if (n <= 0 ) {
        n = DataConstant.PRODUCT_TOP_COUNT;
      }
      return outResponse(productService.getHotTopN(n)); 
    } catch (ServiceException sex) {
      return outResponse("product news list", sex);
    } catch (DaoException dex) {
      return outResponse("product news list", dex);
    } catch (Exception ex) {
      return outResponse("product news list", ex);
    }
  }
}
