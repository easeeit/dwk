package com.dwk.model.product.commend;

import java.util.List;

import com.dwk.model.BasicResponse;

public class CommendListResponse extends BasicResponse {
  private List<CommendProduct> product;

  public List<CommendProduct> getProduct() {
    return product;
  }

  public void setProduct(List<CommendProduct> product) {
    this.product = product;
  }
  
}
