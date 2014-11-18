package com.dwk.model.product.commend;

import java.util.List;

import com.dwk.model.BasicResponse;
import com.dwk.model.product.ProductOverview;

public class CommendListResponse extends BasicResponse {
  private List<ProductOverview> product;

  public List<ProductOverview> getProduct() {
    return product;
  }

  public void setProduct(List<ProductOverview> product) {
    this.product = product;
  }
  
}
