# Rain Endpoint test
##GOAL
The goal of this assessment is to gage the assessment takers knowledge and understanding of endpoints and 
how to integrate these functions into a database
## Prerequisites
1. java 8
1. Postman
1. Maven

### Technologies
* [Maven](https://maven.apache.org/) - Dependency management
* [Spring Boot](https://projects.spring.io/spring-boot/) - Quickly create stand-alone, production-grade Spring based Applications
* [H2](http://www.h2database.com/html/main.html) - In-memory database of choice
* Swagger

#Running the Application

Run the following commands from the base project directory
* To build the project:
```
mvn clean install
```
* To run the project:
```
mvn spring-boot:run
```

### Listing port
This application is configured to run on http://localhost:8080, however, you can change it in the application.properties file.

## To open in the browser: 
###For Swagger
```
http://localhost:8080/Rain86/UserLogin2/1.0.0/swagger-ui.html#
```
###For the H2 Database
```
http://localhost:8080/Rain86/UserLogin2/1.0.0/h2-console
```
##About the assessment
Not much clarity was provided regarding how the database should look, I settled for one database table (INDIVIDUAL)
within a schema (INDIVIDUAL_SCHEMA). There was also no clarity on how the database is to be constructed from the endpoint call, thus 
I decided to create a POST call with no data to be provided, the call will then trigger a SQL create statement.


