package com.dwk.service.tag;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.dwk.dao.MongodbDao;
import com.dwk.model.tag.Tag;
import com.dwk.model.tag.TagListResponse;
import com.dwk.model.tag.TagRelation;

/**
 * Tag service.
 * 
 * @author: xp
 */
public class TagService {

  private MongodbDao dao;
  
  public TagListResponse list(String subjectID, int pageNum, int rowNum) {
    TagListResponse res = new TagListResponse();
    List<Tag> allTagList = dao.selectList("getAllTagList", null, rowNum, (pageNum - 1) * rowNum);
    if (StringUtils.isBlank(subjectID) || CollectionUtils.isEmpty(allTagList)) {
      res.setTag(allTagList);
      return res;
    }
    List<String> subjectTagIDList = dao.selectList("getSubjectTagIDList", subjectID);
    if (!CollectionUtils.isEmpty(subjectTagIDList)) {
      Iterator<Tag> it = allTagList.iterator();
      while(it.hasNext()) {
        if (!subjectTagIDList.contains(it.next().getId())) {
          it.remove();;
        }
      }
      res.setTag(allTagList);
    }
    return res;
  }
  
  /**
   * 更新主题的标签关系
   * @param subjectID
   * @param tags 以逗号","分隔的标签ID 串
   */
  public void updateSubjectTag(String subjectID, String tags) {
    if (StringUtils.isBlank(subjectID)) {
      return ;
    }
    Map<String, Object> map = new HashMap<String, Object>(2);
    map.put("subjectID", subjectID);
    // delete all relation
    dao.delete("deleteTagRelation", map);
    // insert relation
    List<TagRelation> trList = TagRelation.create(tags, subjectID);
    if (trList != null) {
      dao.insertBatch("createTagRelation", trList);
    }
  }
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }

}
