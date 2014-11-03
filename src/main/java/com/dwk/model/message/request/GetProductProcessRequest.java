package com.dwk.model.message.request;

import com.dwk.model.ListRequest;

public class GetProductProcessRequest extends ListRequest {

  private Integer signal;

  public Integer getSignal() {
    return signal;
  }

  public void setSignal(Integer signal) {
    this.signal = signal;
  }
  
}
