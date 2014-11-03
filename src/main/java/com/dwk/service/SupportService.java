package com.dwk.service;

import java.io.ByteArrayInputStream;

import com.dwk.constant.SystemConstant;
import com.dwk.model.RandomRequest;
import com.dwk.util.ImageCodeUtils;

/**
 * Client support service.
 * @author: xp
 * @data : 2014-9-3
 * @since : 1.5
 */
public class SupportService {

//  private Cache cache;
  
  public ByteArrayInputStream random(RandomRequest request) {
    int width = request.getWidth();
    int height = request.getHeight();
    int size = request.getSize();
    int font = request.getFont();
    
    ImageCodeUtils instance = ImageCodeUtils.Instance(width, height, size, font);
    ByteArrayInputStream image = instance.getImage();
    String code = instance.getString();
    
    // code set session
    setRandomCode(request.getSessionid(), code);
    return image;
  }
  
  public String getRandomCode(String sessionid) {
    String key = SystemConstant.RANDOM_CODE_CACHE_KEY + sessionid;
//    return (String)cache.get(key);
    return null;
  }
  
  private void setRandomCode(String sessionid, String code) {
    String key = SystemConstant.RANDOM_CODE_CACHE_KEY + sessionid;
//    cache.set(key, code);
  }

//  public void setCache(Cache cache) {
//    this.cache = cache;
//  }
  
}
