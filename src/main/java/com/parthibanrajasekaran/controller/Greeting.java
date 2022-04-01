package com.parthibanrajasekaran.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class Greeting {

  @Getter
  @Setter
  private long id;

  @Getter
  @Setter
  private String content;

}
