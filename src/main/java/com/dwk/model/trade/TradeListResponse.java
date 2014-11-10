package com.dwk.model.trade;

import java.util.List;

import com.dwk.model.BasicResponse;

public class TradeListResponse extends BasicResponse {
  private List<TradeInfo> trade;

  public List<TradeInfo> getTrade() {
    return trade;
  }

  public void setTrade(List<TradeInfo> trade) {
    this.trade = trade;
  }

}
