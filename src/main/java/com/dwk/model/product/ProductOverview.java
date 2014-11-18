package com.dwk.model.product;


/**
 * api:推荐产品
 * @author xp
 *
 */
public class ProductOverview {
  private String id;
  private String name;
  private String surl;
  private Double score ;
  private Integer laud_count;
  private Integer comment_count;
  private Integer hot;
  
  public static ProductOverview parse(Product p) {
    if (p == null) {
      return null;
    }
    ProductOverview cp = new ProductOverview();
    cp.comment_count = p.getComment_count();
    cp.id = p.getId();
    cp.laud_count = p.getLaud_count();
    cp.name = p.getName_cn();
    cp.score = p.getScore();
    cp.surl = p.getSurl();
    cp.hot = p.getHot();
    return cp;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getSurl() {
    return surl;
  }
  public void setSurl(String surl) {
    this.surl = surl;
  }
  public Double getScore() {
    return score;
  }
  public void setScore(Double score) {
    this.score = score;
  }
  public Integer getLaud_count() {
    return laud_count;
  }
  public void setLaud_count(Integer laud_count) {
    this.laud_count = laud_count;
  }
  public Integer getComment_count() {
    return comment_count;
  }
  public void setComment_count(Integer comment_count) {
    this.comment_count = comment_count;
  }
  public Integer getHot() {
    return hot;
  }
  public void setHot(Integer hot) {
    this.hot = hot;
  }
}
