package com.dwk.model.trade;

import java.util.List;

import com.dwk.model.BasicResponse;

public class TradeListResponse extends BasicResponse {
  private List<TradeOverview> trade;

  public List<TradeOverview> getTrade() {
    return trade;
  }

  public void setTrade(List<TradeOverview> trade) {
    this.trade = trade;
  }

}
