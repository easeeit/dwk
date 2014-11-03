package com.dwk.controller;

import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwk.exception.ServiceException;
import com.dwk.model.RandomRequest;
import com.dwk.service.SupportService;

/**
 * Controller for client support.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
@Controller
@RequestMapping("/support")
public class SupportController extends BaseController {

  @Autowired
  private SupportService supportService;

  @RequestMapping(value = "/random")
  @ResponseBody
  public void random(HttpServletRequest request, HttpServletResponse response, RandomRequest parameter) throws Exception {
    try {
      ByteArrayInputStream image = supportService.random(parameter);
      byte[] data = new byte[(int) image.available()];
      image.read(data);
      outIMGStream(response, data);
    } catch (ServiceException sex) {
      logger.error("Controller error: [random] has exception in service, Cause: " + sex.getMessage(), sex);
    } catch (Exception ex) {
      logger.error("Controller error: [random] has exception, Cause: " + ex.getMessage(), ex);
    }
  }

}
