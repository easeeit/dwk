package com.dwk.model.product.response;

import java.util.List;

import com.dwk.model.BasicResponse;
import com.dwk.model.product.BasicProductInfo;

public class BasicProductListResponse extends BasicResponse {
	
  private List<BasicProductInfo> productList;

  public List<BasicProductInfo> getProductList() {
    return productList;
  }

  public void setProductList(List<BasicProductInfo> productList) {
    this.productList = productList;
  }
	
	
	
}
