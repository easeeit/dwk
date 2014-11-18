package com.dwk.service.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.dwk.constant.OrderBy;
import com.dwk.dao.MongodbDao;
import com.dwk.model.schedule.ScheduleDoneResponse;
import com.dwk.model.schedule.ScheduleInfo;
import com.dwk.model.schedule.ScheduleWillResponse;

/**
 * Schedule service.
 * 
 * @author: xp
 */
public class ScheduleService {

  private MongodbDao dao;
  private ProductService productService;

  // TODO 缓存
  public ScheduleWillResponse willList(int pageNum, int rowNum, OrderBy ob) {
    ScheduleWillResponse res = new ScheduleWillResponse();
    String sql = "getWillScheduleListOrderByTime";
    if (OrderBy.hot.equals(ob)) {
      sql = "getWillScheduleListOrderByHot";
    }
    List<ScheduleInfo> scheduleList = 
        dao.selectList(sql, null, rowNum, (pageNum - 1) * rowNum);
    // 产品信息
    assemblyScheduleInfo(scheduleList);
    res.setWill(scheduleList);
    return res;
  }
  
  // TODO 缓存
  public ScheduleDoneResponse doneList(int pageNum, int rowNum, OrderBy ob) {
    ScheduleDoneResponse res = new ScheduleDoneResponse();
    String sql = "getDoneScheduleListOrderByTime";
    if (OrderBy.hot.equals(ob)) {
      sql = "getDoneScheduleListOrderByHot";
    }
    List<ScheduleInfo> scheduleList = 
        dao.selectList(sql, null, rowNum, (pageNum - 1) * rowNum);
    // 产品信息
    assemblyScheduleInfo(scheduleList);
    res.setDone(scheduleList);
    return res;
  }
  
  public List<ScheduleInfo> getScheduleInfo(List<String> productIDList) {
    if (CollectionUtils.isEmpty(productIDList)) {
      return null;
    }
    // search schedule
    Map<String,Object> map = new HashMap<String, Object>(1);
    map.put("productIDList", productIDList);
    List<ScheduleInfo> scheduleList = dao.selectList("getScheduleByProductIDList", map);
    assemblyScheduleInfo(scheduleList);
    return scheduleList;    
  }
  
  private void assemblyScheduleInfo(List<ScheduleInfo> list) {
    if (CollectionUtils.isEmpty(list)) {
      return;
    }
    for (ScheduleInfo s : list) {
      if (s != null) {
        s.copyInfo(productService.getProduct(s.getProduct_id()));
      }
    }
  }
  
  public void updateScheduleHot(String productID ) {
    if (StringUtils.isBlank(productID)) {
      return;
    }
    int hot = productService.getProduct(productID).getHot();
    Map<String, Object> map = new HashMap<String, Object>(2);
    map.put("productID", productID);
    map.put("hot", hot);
    dao.update("updateScheduleHot", map);
  }
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }

  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

}
