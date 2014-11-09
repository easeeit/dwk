package com.dwk.service.product;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.dwk.constant.OrderBy;
import com.dwk.dao.MongodbDao;
import com.dwk.model.product.ScheduleDoneResponse;
import com.dwk.model.product.ScheduleInfo;
import com.dwk.model.product.ScheduleWillResponse;

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
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }

  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

}
