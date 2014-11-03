package com.dwk.model.friend.response;

import java.util.List;

import com.dwk.model.BasicResponse;
import com.dwk.model.user.BasicUserInfo;

public class FriendListResponse extends BasicResponse {
  
  private List<BasicUserInfo> friendList;

  public List<BasicUserInfo> getFriendList() {
    return friendList;
  }

  public void setFriendList(List<BasicUserInfo> friendList) {
    this.friendList = friendList;
  }
  
}
