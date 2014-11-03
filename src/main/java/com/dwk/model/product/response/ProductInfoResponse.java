package com.dwk.model.product.response;

import com.dwk.model.BasicResponse;
import com.dwk.model.product.ProductInfo;

public class ProductInfoResponse extends BasicResponse {

  private ProductInfo productInfo;

  public ProductInfo getProductInfo() {
    return productInfo;
  }

  public void setProductInfo(ProductInfo productInfo) {
    this.productInfo = productInfo;
  }


}
