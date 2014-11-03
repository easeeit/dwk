package com.dwk.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * For get request and response in runtime.
 * 
 * @author: xp
 * @data : 2013-8-29
 * @since : 1.5
 */
public class HttpMutualGetFilter implements Filter {

  private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
  private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletResponse res = (HttpServletResponse) response;
    res.setDateHeader("Expires", 0);
    res.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
    res.addHeader("Cache-Control", "post-check=0, pre-check=0");
    res.setHeader("Pragma", "no-cache");

    requestLocal.set((HttpServletRequest) request);
    responseLocal.set((HttpServletResponse) response);
    try {
      chain.doFilter(request, response);
    } finally {
      requestLocal.remove();
      responseLocal.remove();
    }
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void destroy() {}

  public static HttpServletRequest getReq() {
    return requestLocal.get();
  }

  public static HttpServletResponse getRes() {
    return responseLocal.get();
  }
}
