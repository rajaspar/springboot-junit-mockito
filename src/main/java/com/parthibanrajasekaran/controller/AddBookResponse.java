package com.parthibanrajasekaran.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class AddBookResponse {

  @Getter
  @Setter
  private String msg;

  @Getter
  @Setter
  private String id;


}
