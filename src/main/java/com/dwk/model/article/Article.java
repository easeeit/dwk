package com.dwk.model.article;

import java.util.List;

import com.dwk.model.comment.CommentInfo;

public class Article {
  private String id;
  private String product_id;
  private String title;
  private String summary;
  private String content;
  private String source;
  private String type;
  private String style;
  private String res;
  private String author;
  private String status;
  private Integer laud_count;
  private Integer comment_count;
  private Integer hot;
  private String url;
  private String original_url;
  private Long create_time;
  
  private List<CommentInfo> comment;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getSource() {
    return source;
  }
  public void setSource(String source) {
    this.source = source;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getAuthor() {
    return author;
  }
  public void setAuthor(String author) {
    this.author = author;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public Integer getLaud_count() {
    return laud_count;
  }
  public void setLaud_count(Integer laud_count) {
    this.laud_count = laud_count;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public String getOriginal_url() {
    return original_url;
  }
  public void setOriginal_url(String original_url) {
    this.original_url = original_url;
  }
  public Long getCreate_time() {
    return create_time;
  }
  public void setCreate_time(Long create_time) {
    this.create_time = create_time;
  }
  public List<CommentInfo> getComment() {
    return comment;
  }
  public void setComment(List<CommentInfo> comment) {
    this.comment = comment;
  }
  public String getProduct_id() {
    return product_id;
  }
  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }
  public String getSummary() {
    return summary;
  }
  public void setSummary(String summary) {
    this.summary = summary;
  }
  public String getStyle() {
    return style;
  }
  public void setStyle(String style) {
    this.style = style;
  }
  public String getRes() {
    return res;
  }
  public void setRes(String res) {
    this.res = res;
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
