package com.dwk.model.message.response;

import java.util.List;

import com.dwk.model.BasicResponse;
import com.dwk.model.message.MessageInfo;

public class MessageListResponse extends BasicResponse {
  
  private List<MessageInfo> messageList;

  public List<MessageInfo> getMessageList() {
    return messageList;
  }

  public void setMessageList(List<MessageInfo> messageList) {
    this.messageList = messageList;
  }
  
}
