package com.dwk.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwk.constant.APIConstant;
import com.dwk.constant.SystemConstant;
import com.dwk.exception.DaoException;
import com.dwk.exception.ServiceException;
import com.dwk.model.auth.request.MobilAuthRequest;
import com.dwk.service.article.ArticleService;

/**
 * Controller for auth.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

//  private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
  @Autowired
  private ArticleService articleService;

  @RequestMapping(value = "/list", produces = APIConstant.CONTENT_TYPE_JSON)
  @ResponseBody
  public String list(HttpServletRequest request, @PathVariable String pageNum, @PathVariable String rowNum,
          @RequestBody MobilAuthRequest parameter) throws Exception {
    try {
      if (true) {
        return "-----------------------";
      }
//      log.debug("==============================");
      Integer pn = SystemConstant.PN;
      Integer rn = SystemConstant.RN;
      if (pageNum != null && !"0".equals(pageNum)) {
        pn = Integer.parseInt(pageNum);
      }
      if (rowNum != null) {
        rn = Integer.parseInt(rowNum);
      }
      return outResponse(articleService.list(pn, rn));
    } catch (ServiceException sex) {
      return outResponse("accountMobileAuth", sex);
    } catch (DaoException dex) {
      return outResponse("accountMobileAuth", dex);
    } catch (Exception ex) {
      return outResponse("accountMobileAuth", ex);
    }
  }


}
