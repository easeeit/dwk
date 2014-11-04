package com.dwk.model.article;

public class Article {
  private String id;
  private String title;
  private String sub_title;
  private String content;
  private String source;
  private String type;
  private String author;
  private String status;
  private Integer hits_count;
  private Integer laud_count;
  private String url;
  private String original_url;
  private Long create_time;
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
  public String getSub_title() {
    return sub_title;
  }
  public void setSub_title(String sub_title) {
    this.sub_title = sub_title;
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
  public Integer getHits_count() {
    return hits_count;
  }
  public void setHits_count(Integer hits_count) {
    this.hits_count = hits_count;
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
}
