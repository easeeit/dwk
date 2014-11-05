package com.dwk.service.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.dwk.constant.APIConstant;
import com.dwk.constant.Platform;
import com.dwk.dao.MongodbDao;
import com.dwk.model.product.Product;
import com.dwk.model.product.Schedule;
import com.dwk.model.product.ScheduleListResponse;

/**
 * Product  service.
 * 
 * @author: xp
 */
public class ProductService {

  private MongodbDao dao;
  
  public ScheduleListResponse scheduleList(String platform, int pageNum, int rowNum) {
    ScheduleListResponse res = new ScheduleListResponse();
    if (!StringUtils.isBlank(platform) && !Platform.valid(platform)) {
      res.setCode(APIConstant.RETURN_CODE_PARAMETER_INVAILD);
      return res;
    }
    List<Schedule> scheduleList = null;
    Map<String, Object> map = new HashMap<String, Object>(1);
    if (StringUtils.isBlank(platform)) {
      scheduleList = dao.selectList("getScheduleList", map, rowNum, (pageNum - 1) * rowNum);
    } else {
      map.put("platform", platform);
      scheduleList = dao.selectList("getScheduleListForPlatform", map, rowNum, (pageNum - 1) * rowNum);
    }
    // 检索产品信息
    if (!CollectionUtils.isEmpty(scheduleList)) {
      List<String> productIDList = new ArrayList<String>(scheduleList.size());
      for (Schedule schedule : scheduleList) {
        if (schedule != null) {
          productIDList.add(schedule.getProduct_id());
        }
      }
      if (!CollectionUtils.isEmpty(productIDList)) {
        map.put("productIDList", productIDList);
        List<Product> productList = dao.selectList("getProductList", map);
        Map<String,Product> pMap = new HashMap<String, Product>(productList.size());
        for (Product p : productList) {
          pMap.put(p.getId(), p);
        }
        for (Schedule schedule : scheduleList) {
          if (schedule != null) {
            Product p = pMap.get(schedule.getProduct_id());
            schedule.setName(String.format("%s(%s)", p.getName_cn(), p.getName_en()));
            schedule.setPlatform(p.getPlatform());
            schedule.setPlatformName(Platform.valueToName(p.getPlatform()));
          }
        }
      }
    }
    res.setSchedule(scheduleList);
    return res;
  }
  
  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }

}
