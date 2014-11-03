package com.dwk.model.auth.request;

/**
 * Request parameter for mobil auth.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class MobilAuthRequest {

  private String lenovoST;
  private String realm;

  public String getLenovoST() {
    return lenovoST;
  }

  public void setLenovoST(String lenovoST) {
    this.lenovoST = lenovoST;
  }

  public String getRealm() {
    return realm;
  }

  public void setRealm(String realm) {
    this.realm = realm;
  }

}
