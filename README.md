# Task Control API

## About

This API was developed to test my new knowledge in Java using the Spring framework. It aims to manage department tasks along with the users in those departments, featuring authentication and authorization via tokens.

## Tools and Technologies
The following tools and technologies were used to create this application:

- [Spring-Web](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring-Boot-Starter-Data-Jpa](https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#reference)
- [SpringDoc-OpenApi-Starter-WebMVC-Ui](https://springdoc.org/)
- [Spring-Boot-Starter-Validation](https://reflectoring.io/bean-validation-with-spring-boot/)
- [Spring-Boot-Starter-Web](https://docs.spring.io/spring-boot/tutorial/first-application/index.html)
- [Spring-Boot-DevTools](https://docs.spring.io/spring-boot/tutorial/first-application/index.html)
- [H2-Database](https://www.baeldung.com/spring-boot-h2-database)
- [Lombok](https://projectlombok.org/features/)
- [Spring-Boot-Starter-Test](https://docs.spring.io/spring-boot/reference/testing/index.html)
- [Json-Web-Token-Api](https://central.sonatype.com/artifact/io.jsonwebtoken/jjwt-api)
- [Json-Web-Token-Impl](https://central.sonatype.com/artifact/io.jsonwebtoken/jjwt-impl/0.12.6)
- [Json-Web-Token-Jackson](https://central.sonatype.com/artifact/io.jsonwebtoken/jjwt-jackson/0.12.6)
- [Commons-Lang3](https://commons.apache.org/proper/commons-lang/javadocs/api-release/index.html)
- [Spring-Boot-Starter-Security](https://docs.spring.io/spring-security/reference/getting-spring-security.html)

## User Roles

#### Full Access Persona
- email: "Email01"
- password: "1234"
#### Regular User Persona
- Username: "Email02"
- Password: "1234"

## EndPoints
- **/login**
  - **POST:** Used to acquire the authorization token via login with a email and password.
- **/department**
  - **POST:** Create a new Department.
  - **/all**
    - **GET:** Retrieve all departments.
  - **/enableds**
    - **GET:** Retrieve enableds departments.
  - **/name**
    - **/{name}**
      - **GET:** Retrieve a Department by name.
      - **DELETE:** Delete a Department by name.
      - **PUT:** Update a Department by name.
- **/task**
  - **POST:** Create a new Task.
  - **/all**
    - **GET:** Retrieve all tasks.
  - **/department**
    - **{name}**
      - **GET:** Retrieve tasks by department name.
  - **/name**
    - **{name}**
      - **GET:** Retrieve Task by name.
      - **DELETE:** Delete a Task by name.
      - **PUT:** Update a Task by name.
- **/userInformations**
  - **/all**
    - **GET:** Retrive all UserInformations.
  - **/current**
    - **GET:** Retrieve information of the logged-in user.
- **/user**
  - **GET:** Retrieve all Users.
  - **POST:** Create a new User.
  - **/fullName**
    - **{fullName}**
      - **GET:** Retrieve a User by FullName.
      - **DELETE:** Delete a User by FullName.
  - **/current**
    - **PUT:** Update the information of the logged-in user.


## Authentication

Authentication is performed via a POST request to the /login route, using the body {email: "", password: ""}. This action returns an access token in the "Authorization" header of the response.

## Swagger Documentation

This project includes documentation created using Swagger-UI, accessible through the following links:
- HTML: http://localhost:8080/swagger-ui.html
- HTML: http://localhost:8080/swagger-ui/index.html
- Json: http://localhost:8080/v3/api-docs