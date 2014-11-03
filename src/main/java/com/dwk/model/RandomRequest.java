package com.dwk.model;

import com.dwk.util.ImageCodeUtils;

/**
 * Image code random request parameter.
 * 
 * @author: xp
 * @data : 2014-9-3
 * @since : 1.5
 */
public class RandomRequest {

  private int width = ImageCodeUtils.WIDTH;
  private int height = ImageCodeUtils.HEIGHT;
  private int size = ImageCodeUtils.SIZE;
  private int font = ImageCodeUtils.FONT_SIZE;
  
  private String sessionid;

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }
  
  public int getFont() {
    return font;
  }
  
  public void setFont(int font) {
    this.font = font;
  }

  public String getSessionid() {
    return sessionid;
  }

  public void setSessionid(String sessionid) {
    this.sessionid = sessionid;
  }
  
}
