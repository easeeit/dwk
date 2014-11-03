package com.dwk.model.friend.request;

import java.util.List;

/**
 * Request parameter for invite friend.
 * 
 * @author: xp
 * @data : 2014-9-5
 * @since : 1.5
 */
public class InviteFriendRequest {

  private String requestMessage;
  
  private List<InviteInfo> addUserList;

  public String getRequestMessage() {
    return requestMessage;
  }

  public void setRequestMessage(String requestMessage) {
    this.requestMessage = requestMessage;
  }

  public List<InviteInfo> getAddUserList() {
    return addUserList;
  }

  public void setAddUserList(List<InviteInfo> addUserList) {
    this.addUserList = addUserList;
  }

}
