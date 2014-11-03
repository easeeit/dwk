package com.dwk.model;

/**
 * Request parameter for list.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class ListRequest {

  private Integer skip;
  private Integer limit;

  public Integer getSkip() {
    return skip;
  }

  public void setSkip(Integer skip) {
    this.skip = skip;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

}
