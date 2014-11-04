package com.dwk.model.tag;

import java.util.List;

import com.dwk.model.BasicResponse;

public class TagListResponse extends BasicResponse {
  private List<Tag> tag;

  public List<Tag> getTag() {
    return tag;
  }

  public void setTag(List<Tag> tag) {
    this.tag = tag;
  }
  
}
