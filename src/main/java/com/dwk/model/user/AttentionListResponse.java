package com.dwk.model.user;

import java.util.List;

import com.dwk.model.BasicResponse;
import com.dwk.model.schedule.ScheduleInfo;

public class AttentionListResponse extends BasicResponse {
  private List<ScheduleInfo> game;

  public List<ScheduleInfo> getGame() {
    return game;
  }

  public void setGame(List<ScheduleInfo> game) {
    this.game = game;
  }

}
