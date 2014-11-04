package com.dwk.model.topic;

import java.util.List;

import com.dwk.model.BasicResponse;

public class TopicListResponse extends BasicResponse {
  private List<Topic> topic;

  public List<Topic> getTopic() {
    return topic;
  }

  public void setTopic(List<Topic> topic) {
    this.topic = topic;
  }
  
}
