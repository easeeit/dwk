package com.dwk.model.friend.response;

import java.util.List;

import com.dwk.model.BasicResponse;

public class FriendRequestList extends BasicResponse {

  private List<RequestInfo> requestList;

  public static class RequestInfo {

    private String requestUserID;
    private String requestUserName;
    private String requestUserAvatar;
    private String requestUserPhone;
    private String requestMessage;
    private Long requestTime;

    public String getRequestUserID() {
      return requestUserID;
    }

    public void setRequestUserID(String requestUserID) {
      this.requestUserID = requestUserID;
    }

    public String getRequestUserName() {
      return requestUserName;
    }

    public void setRequestUserName(String requestUserName) {
      this.requestUserName = requestUserName;
    }

    public String getRequestUserAvatar() {
      return requestUserAvatar;
    }

    public void setRequestUserAvatar(String requestUserAvatar) {
      this.requestUserAvatar = requestUserAvatar;
    }

    public String getRequestUserPhone() {
      return requestUserPhone;
    }

    public void setRequestUserPhone(String requestUserPhone) {
      this.requestUserPhone = requestUserPhone;
    }

    public String getRequestMessage() {
      return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
      this.requestMessage = requestMessage;
    }

    public Long getRequestTime() {
      return requestTime;
    }

    public void setRequestTime(Long requestTime) {
      this.requestTime = requestTime;
    }

  }

  public List<RequestInfo> getRequestList() {
    return requestList;
  }

  public void setRequestList(List<RequestInfo> requestList) {
    this.requestList = requestList;
  }

}
