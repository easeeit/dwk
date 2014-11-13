package com.dwk.model.trade;

import com.dwk.constant.Exchange;
import com.dwk.constant.Haggle;
import com.dwk.constant.Integrity;
import com.dwk.constant.Platform;
import com.dwk.constant.Version;


public class TradeInfo extends Trade {
  private String nickname;
  
  public void beautifyData() {
    setIntegrity(Integrity.valueToName(getIntegrity()));
    setExchange(Exchange.valueToName(getExchange()));
    setHaggle(Haggle.valueToName(getHaggle()));
    setPlatform(Platform.valueToName(getPlatform()));
    setVersion(Version.valueToName(getVersion()));
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  
}
