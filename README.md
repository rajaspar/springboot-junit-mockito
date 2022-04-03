# sprinpboot-rest-junit-mockito
This is a coding excercise to sort integer values as an arraylist in ascending ( and descending order )

### Connect with me
[<img align="middle" alt="Parthiban Rajasekaran | LinkedIn" width="115px" src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" />][linkedin]
<br />

### Prerequisites
<table> 
<tr>
<td>
<img style="vertical-align:bottom" alt="Java" width="35px" src="https://cdn-icons-png.flaticon.com/512/226/226777.png" /> 
</td>
<td>
Open JDK 11 or higher
</td>
</tr>
<tr>

<tr>
<td>
<img align="left" style="vertical-align:bottom" alt="IntelliJ" width="35px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/IntelliJ_IDEA_Icon.svg/2048px-IntelliJ_IDEA_Icon.svg.png" /></td>
<td>
Community edition
</td>
</tr>
</table>


### Getting Started: ⚡
- Clone the project <br />
  ```git clone https://github.com/ParthibanRajasekaran/springboot-junit-mockito.git```
- Launch and open the project 'springboot-junit-mockito' in IntelliJ editor
- To start the tomcat server, right click **'SprinpBootRestApplication'** and run it as click _'SprinpBootRestApplication main()'_ .This will start the server in your _localhost port: 8080_ by default
- If you wish to test the service using **POSTMAN** then please feel free to download the collection from _springboot-junit-mockito/postman-collection/_
- Trigger the test run right clicking the **'ValueControllerTest'** and run it as a junit test to trigger the unit tests

### Excercise:
- [x]  Provide 1 interface that can take in an array, these inputs must be numbers.
  <span style="color:green;"> Model <Value> is created unde the model package, this will restrict users from passing an incorrect arraylist as request body. If users enter an incorrect input then the endpoint returns a **400 - Bad request** as response </span>
- [x]  When this interface is called, it must return the numbers in ascending order. For example, input [9, 20, 1, 15, 30] would return [1, 9, 15, 20, 30]. <br>
  <span style="color:green;">/sortValuesInAscending & /sortValuesInDescending in the controller package is implemented</span>
- [x]  There must be at least one input check.
- [x]  There must be at least one unit test.
  <span style="color:green;">Value Controller has 100% unit test coverage</span>
- [x]  The response must be returned as JSON.
  
### Walkthrough on implementation:
- The entry point of the project shall be _com/parthibanrajasekaran/SprinpBootRestApplication.java_ which will start the service
- In order to ensure only array of integers are accepted, we declare the type of input via model here : _com/parthibanrajasekaran/model/Value.java_
  -- In order to generate the setters and getters, we use the lombok **@Setter @Getter**.
  -- To ignore any null during  serialization, we make use of Jackson annotation : **@JsonInclude(Include.NON_NULL)**
- The service call is created here: _com/parthibanrajasekaran/controller/ValueController.java_
  -- For spring boot to start the service, we add a binding annotation - **@RestController**
  -- We are creating a POST request in order to perform ascending and descending order sorting. This is acheived through **@PostMapping** with the _resource path_ and _request body_ which complies with the 'Value' model
  -- We make use of the collections from the Java Utils package inorder to perform sorting and reverse sorting
  -- For the purpose of logging we make use of the **slf4j** library

### Walkthrough on tests:
- We primarily use **Junit & MockMvc** for the purpose of unit tests
- Object  creation is performed using **@Autowired**, Invoke MockMvc using **@AutoConfigureMockMvc**
- We cover method implementation from the controller class using server based execution and via _serverless_ options using MockMvc

### Note:
In case you would like to have a look at other details of the implementation, then please switch to **main** branch. I have also implemented a sample **Library** API and cover the unit tests with **Junit, Mokito & MockMvc**

[linkedin]: https://www.linkedin.com/in/parthiban-rajasekaran/