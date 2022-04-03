package com.parthibanrajasekaran.controller;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValueController {

  private static final Logger log = LoggerFactory.getLogger(ValueController.class);

  @PostMapping("/sortValuesInAscending")
  public ResponseEntity<Value> sortValuesInAscending(@RequestBody Value value){
    Collections.sort(value.getValue());
    log.info("Sorting provided valeus in ascending order");
    return new ResponseEntity<>(value, HttpStatus.ACCEPTED);
  }

  @PostMapping("/sortValuesInDescending")
  public ResponseEntity<Value> sortValuesInDescending(@RequestBody Value value){
    value.getValue().sort(Collections.reverseOrder());
    log.info("Sorting provided valeus in descending order");
    return new ResponseEntity<>(value, HttpStatus.ACCEPTED);
  }

}
