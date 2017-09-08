package com.library.data;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Cache
@ApiModel("Book object")
public class Book {

  private String title;
  private String author;
//  private String createdBy;
//  private String createdById;
  private String publishedDate;
  private String description;
  @Id
  private Long id;
  private String imageUrl;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

//  public String getCreatedBy() {
//    return createdBy;
//  }
//
//  public void setCreatedBy(String createdBy) {
//    this.createdBy = createdBy;
//  }
//
//  public String getCreatedById() {
//    return createdById;
//  }
//
//  public void setCreatedById(String createdById) {
//    this.createdById = createdById;
//  }

  public String getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(String publishedDate) {
    this.publishedDate = publishedDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

//  @Override
//  public String toString() {
//    return
//        "Title: " + title + ", Author: " + author + ", Published date: " + publishedDate
//        + ", Added by: " + createdBy;
//  }
}

