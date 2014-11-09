package com.dwk.model.product;

import java.util.List;

import com.dwk.model.BasicResponse;

public class ScheduleWillResponse extends BasicResponse {
  private List<ScheduleInfo> will;
  public List<ScheduleInfo> getWill() {
    return will;
  }
  public void setWill(List<ScheduleInfo> will) {
    this.will = will;
  }

}
