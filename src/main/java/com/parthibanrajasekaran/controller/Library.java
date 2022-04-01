package com.parthibanrajasekaran.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Storage2")
public class Library {

  @Getter
  @Setter
  @Column(name = "book_name")
  private String book_name;

  @Getter
  @Setter
  @Id
  @Column(name = "id")
  private String id;

  @Getter
  @Setter
  @Column(name = "aisle")
  private int aisle;

  @Getter
  @Setter
  @Column(name = "isbn")
  private String isbn;

  @Getter
  @Setter
  @Column(name = "author")
  private String author;





}
