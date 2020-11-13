This Billing tool, I have created is buy usinj Java 8 and Spring Web Flux.

I've done basic api implementation with a Sample Junit5 test.

Few points :

File name should be _**"cityFile.csv"**_

The Api's are:
POST http://localhost:8080/v1/
PUT http://localhost:8080/v1/update?location=Delhi
GET http://localhost:8080/v1/search?location=Delhi

Headers:
Accept: application/stream+json
Content-Type: application/stream+json

Run the Project:
on the project root
mvn spring-boot:run

OR

run 
mvn clean install
on the project and run java -jar covidtracker-1.0 0.0.1-SNAPSHOT.jar
on the project and run java -jar covidtracker-1.0 0.0.1-SNAPSHOT.jar



**** I'm working on Dockerfile.



