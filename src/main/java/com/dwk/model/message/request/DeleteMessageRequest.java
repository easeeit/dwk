package com.dwk.model.message.request;

import java.util.List;

public class DeleteMessageRequest {
  
  private List<String> msgIDs;

  public List<String> getMsgIDs() {
    return msgIDs;
  }

  public void setMsgIDs(List<String> msgIDs) {
    this.msgIDs = msgIDs;
  }
  
}
