package com.dwk.model.product;

import java.util.List;

import com.dwk.model.BasicResponse;

public class ScheduleListResponse extends BasicResponse {
  private List<Schedule> schedule;

  public List<Schedule> getSchedule() {
    return schedule;
  }

  public void setSchedule(List<Schedule> schedule) {
    this.schedule = schedule;
  }
  
}
