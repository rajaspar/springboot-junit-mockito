package com.parthibanrajasekaran;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parthibanrajasekaran.controller.Value;
import com.parthibanrajasekaran.controller.ValueController;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
@SpringBootTest
public class ValueControllerTest {

  @Autowired
  ValueController valueController;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void checkSortValuesInAscending(){

    Value value = createIntegerList();
    Collections.sort(value.getValue());

    ResponseEntity<Value> response = valueController.sortValuesInAscending(value);
    assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());

    final Value valueResponse = response.getBody();
    assertEquals(valueResponse.getValue(), value.getValue());
  }

  @Test
  @Description("Using Serverless")
  public void checkSortValuesInAscendingServerless(){
    Value value = createIntegerList();
    Collections.sort(value.getValue());

    ObjectMapper objectMapper = new ObjectMapper();
    String requestBody = null;
    try {
      requestBody = objectMapper.writeValueAsString(createIntegerList());
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    try {
      assert requestBody != null;
      this.mockMvc.perform(post("/sortValuesInAscending")
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestBody))
          .andDo(print())
          .andExpect(status().isAccepted())
          .andExpect(jsonPath("$.value").value(value.getValue()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void checkSortValuesInDescending(){

    Value value = createIntegerList();
    value.getValue().sort(Collections.reverseOrder());

    ResponseEntity<Value> response = valueController.sortValuesInDescending(value);
    assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());

    final Value valueResponse = response.getBody();
    assertEquals(valueResponse.getValue(), value.getValue());
  }

  @Test
  @Description("Using Serverless")
  public void checkSortValuesInDescendingServerless(){
    Value value = createIntegerList();
    value.getValue().sort(Collections.reverseOrder());

    ObjectMapper objectMapper = new ObjectMapper();
    String requestBody = null;
    try {
      requestBody = objectMapper.writeValueAsString(createIntegerList());
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    try {
      assert requestBody != null;
      this.mockMvc.perform(post("/sortValuesInDescending")
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestBody))
          .andDo(print())
          .andExpect(status().isAccepted())
          .andExpect(jsonPath("$.value").value(value.getValue()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Value createIntegerList(){
    ArrayList<Integer> toBeSorted = new ArrayList<>();
    Value value = new Value();
    toBeSorted.add(33);
    toBeSorted.add(11);
    toBeSorted.add(22);
    toBeSorted.add(99);
    toBeSorted.add(55);
    value.setValue(toBeSorted);

    return value;
  }

}
