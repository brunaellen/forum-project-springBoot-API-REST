# Forum-Alura-Application

Forum project was created to work as a forum of an online courses plataform where the users are the students of that plataform.
Users will clear up their doubts about the exercises, exchanging experiences with other students through the forum application.

### Atm application was made using:

* Spring Boot
* Java 11
* maven
* H2 database
* Spring data JPA

## How to build

`mvn clean install`

## Running

`mvn spring-boot:run`

## Test coverage

`open target/site/jacoco/index.html`


### Endpoints

The application has six endpoints:
* /topics/listAll;
* /topics/listByCourseName;
* /topics/register;
* /topics/details/{id};
* /topics/update/{id};
* /topics/delete/{id};