package com.dwk.model.product;

import java.util.List;

import com.dwk.model.BasicResponse;

public class ScheduleDoneResponse extends BasicResponse {
  private List<ScheduleInfo> done;
  public List<ScheduleInfo> getDone() {
    return done;
  }
  public void setDone(List<ScheduleInfo> done) {
    this.done = done;
  }

}
