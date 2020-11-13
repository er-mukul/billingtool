# Billing Tool
 
This Billing tool, calculates the amount of bill to be paid by a customer of various types. It calculates the discount and gives the final amount to be paid.

### Major Technologies Used:
* Java 8 (Optional, Lambda expressions, Functional Programming, Stream API, Time API)
* Spring WebFlux, Spring Boot, Spring Data JPA
* JUNIT 5, Step Verfier
* Netty Server for Reactive Programming
* H2 Embedded database
* REST Webservices
* Maven

### Pre-requisite
   * Java 8
   * Maven 3.6 and above
   * Sonarcube for Code Quality

### Other Libraries/Tools
* Lombok 1.18 to remove boiler plate code
* Flyway to handle database changes and migrations.
* Slf4j for logging
* Jacoco plugin for Code coverage
* Sonarcube for Code Quality

### REST APIs

* To generate the final bill amount after discounts
  * URI: http://localhost:8080/v1/generatebill
  * REQUEST
    * Http Method: POST
    * Headers:
      * Accept : application/stream+json
      * Content-Type : application/stream+json
    * Request Sample :
     ````
    { 
      "customerName":"snow",
      "items": [
      	{
      		"id":1001,
      		"quantity":4
      	},
      	{
      		"id":1002,
      		"quantity":10
      	}
      	]
    }
    ````
    * Description: In the request to get the final bill amount, customer name and item list needs to be passed. Item details like item id and quantity should be given.
   
  * RESPONSE
    * Status : 200 OK
    * Sample: 441.5
    
  *![image](/images/Req_res.png)
  
* To add the Customer
  * URI: http://localhost:8080/v1/addcustomer
  * REQUEST
    * Http Method: POST
    * Headers:
      * Accept : application/stream+json
      * Content-Type : application/stream+json
    * Request Sample :
     ````
     { 
       "id":12,
       "customerName":"Mukul",
       "customerType":"AFFILIATE",
       "joiningDate":"2019-01-10"
     }
    ````
    * Description: Customer Type should match the customer types in the enum..
   
  * RESPONSE
    * Status : 200 OK
    * Sample:
    
            ````
            {
              "id": 6,
              "customerType": "AFFILIATE",
              "joiningDate": [
                          2019,
                          1,
                          10
                           ],
              "customerName": "kkkkk"
            }
           ````
### How to Build and Run           
  * Clone the repository from  https://github.com/er-mukul/billingtool.git in some directory on your local system.
  * Go to the root of your cloned project directoty.
  * Run commands :
    * mvn package
    * java -jar target/billing_tool-0.0.1.jar

### Code Coverage Report
  * Run command : mvn clean install
  * Code coverage can be found at /target/site/jacoco/index.html
  
### SonarCube
  * Run your local Sonarcube server.
  * Run command : mvn sonar:sonar     
  * Check Sonarcube analysis at http://localhost:9000/dashboard?id=com.mukul%3Abilling_tool 

### Screenshots
 * All screenshots and images can be found in images folder of the project directory.
 
### H2 Database console
 * Console can be accessed at http://localhost:8081/
 * Username: sa and password: password
 
### Improvements Areas
   * Dockerization
   * More Validations
   
   
  




