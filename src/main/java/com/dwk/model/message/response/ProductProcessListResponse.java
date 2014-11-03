package com.dwk.model.message.response;

import java.util.List;

import com.dwk.model.message.ProductProcessInfo;

public class ProductProcessListResponse {

  private List<ProductProcessInfo> processList;

  public List<ProductProcessInfo> getProcessList() {
    return processList;
  }

  public void setProcessList(List<ProductProcessInfo> processList) {
    this.processList = processList;
  }

}
