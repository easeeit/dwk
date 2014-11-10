package com.dwk.model.product;

import java.util.List;

import com.dwk.model.BasicResponse;

public class ScheduleTopResponse extends BasicResponse {
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

}
