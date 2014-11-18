package com.dwk.model.product;

import java.util.List;

import com.dwk.model.BasicResponse;

public class ScheduleTopResponse extends BasicResponse {
  private ProductOverview week_best;
  private List<ProductOverview> hot;
  private List<ScheduleInfo> will;
  private List<ScheduleInfo> done;
  public List<ScheduleInfo> getWill() {
    return will;
  }
  public void setWill(List<ScheduleInfo> will) {
    this.will = will;
  }
  public List<ScheduleInfo> getDone() {
    return done;
  }
  public void setDone(List<ScheduleInfo> done) {
    this.done = done;
  }
  public ProductOverview getWeek_best() {
    return week_best;
  }
  public void setWeek_best(ProductOverview week_best) {
    this.week_best = week_best;
  }
  public List<ProductOverview> getHot() {
    return hot;
  }
  public void setHot(List<ProductOverview> hot) {
    this.hot = hot;
  }

}
