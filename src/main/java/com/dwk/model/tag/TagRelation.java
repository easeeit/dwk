package com.dwk.model.tag;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class TagRelation {
  private String id;
  private String tag_id;
  private String subject_id;
  private Long create_time;
  
  public static List<TagRelation> create(String tagIDList, String subjectID) {
    if (StringUtils.isBlank(tagIDList)) {
      return null;
    }
    String[] array = tagIDList.split(",");
    List<TagRelation> list = new ArrayList<TagRelation>(array.length);
    long now = System.currentTimeMillis();
    for(String tagID : array) {
      TagRelation tr = new TagRelation();
      tr.tag_id = tagID;
      tr.subject_id = subjectID;
      tr.create_time = now;
      list.add(tr);
    }
    return list;
  }
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getTag_id() {
    return tag_id;
  }
  public void setTag_id(String tag_id) {
    this.tag_id = tag_id;
  }
  public String getSubject_id() {
    return subject_id;
  }
  public void setSubject_id(String subject_id) {
    this.subject_id = subject_id;
  }
  public Long getCreate_time() {
    return create_time;
  }
  public void setCreate_time(Long create_time) {
    this.create_time = create_time;
  }
  
}
