package com.parthibanrajasekaran.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
public class Value {

  @Setter
  @Getter
  private ArrayList<Integer> value = null;

}
