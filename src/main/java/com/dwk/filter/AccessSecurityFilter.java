package com.dwk.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dwk.constant.APIConstant;
import com.dwk.service.auth.AuthService;

/**
 * Access security filter.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class AccessSecurityFilter implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(AccessSecurityFilter.class);

  /**
   * 不需要登录拦截的url请求
   */
  private List<String> notUrlList = Collections.emptyList();

  private AuthService authService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;

    String key = req.getHeader(APIConstant.HEADER_KEY_KEY);
    if (StringUtils.isBlank(key)) {
      outNoLoginResponse(res, "No '"+APIConstant.HEADER_KEY_KEY+"' in http head.");
      return;
    }
    
    String secret = req.getHeader(APIConstant.HEADER_KEYSECRET_KEY);
    if (StringUtils.isBlank(secret)) {
      outNoLoginResponse(res, "No '"+APIConstant.HEADER_KEYSECRET_KEY+"' in http head.");
      return;
    }
    
    String uri = req.getRequestURI();
    if (!isIgnore(uri)) {
      if(!security(req, res, key, secret)) {
        return;
      }
    }

    chain.doFilter(request, response);
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    String notUrl = filterConfig.getInitParameter("notUrl");
    if (!StringUtils.isBlank(notUrl)) {
      String url[] = notUrl.split(",");
      notUrlList = new ArrayList<String>(url.length);
      notUrlList.addAll(Arrays.asList(url));
    }
    logger.info("Not filter access security request url:" + notUrl);
    
    ServletContext context = filterConfig.getServletContext();
    ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(context);
    authService = ac.getBean(AuthService.class);
    logger.info("Access security filter is inited.");
  }

  @Override
  public void destroy() {}

  private boolean security(HttpServletRequest req, HttpServletResponse res, String key, String secret) {
    String serverSecret = authService.getSecret(key);
    if(StringUtils.isBlank(serverSecret)) {
      outNoLoginResponse(res, "Consumer client unavailable, "+APIConstant.HEADER_KEY_KEY+" is '" + key + "'.");
      return false;
    }
    
    // TODO auth content secret
    
    return true;
  }
  
  private static void outNoLoginResponse(HttpServletResponse res, String message) {
    res.setContentType("application/json;charset=UTF-8");
    try {
      JSONObject json = new JSONObject();
      json.put(APIConstant.RETURN_CODE_KEY, APIConstant.RETURN_CODE_OPERATE_PERMISSION_INVAILD);
      json.put(APIConstant.RETURN_MESSAGE_KEY, message);
      PrintWriter out = res.getWriter();
      out.write(json.toString());
      out.close();
    } catch (Exception e) {
      logger.error("Access security print response error.", e);
    }
  }

  private boolean isIgnore(String uri) {
    if ("/".equals(uri)) {
      return true;
    }

    if (!CollectionUtils.isEmpty(notUrlList)) {
      if (notUrlList.contains(uri)) {
        return true;
      }

      for (String notUrl : notUrlList) {
        if (uri.indexOf(notUrl) != -1) {
          return true;
        }

        if (uri.matches(notUrl)) {
          return true;
        }
      }
    }
    return false;
  }

}
