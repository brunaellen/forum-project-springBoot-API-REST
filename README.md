# Forum-Alura-Application
test

Forum project was created to work as a forum of an online courses plataform where the users are the students of that plataform.
Users will clear up their doubts about the exercises, exchanging experiences with other students through the forum application.

### Atm application was made using:

* Spring Boot 2.6.1
* Java 11
* maven
* H2 database
* Spring data JPA

## How to build

`mvn clean install`

## Running

`mvn spring-boot:run`

### Building docker container

`docker build -t alura-forum .`

### Running with Docker

`docker run -d -it --rm -p 8080:8080 -e SPRING_PROFILES_ACTIVE='production' -e FORUM_DATABASE_URL='jdbc:h2:mem:alura-forum' -e FORUM_DATABASE_USERNAME='sa' -e FORUM_DATABASE_PASSWORD='' -e FORUM_JWT_SECRET='4&uQQMsy7eCD2RTHbbRforumAlura-0.0.1-SNAPSHOT.jari$6uLU&^$3L2@E#&S#EEqxYFLTY%cQqv@ZM*Xx7R$ey#K*n44nVjqASMJ3VeQndDv%zuu9hdf23lss5$nfvQ#e37H4L5#3omtxHn6caRzaU8b&4oqfQAV7#76ujsnjC$Rx4isJMskACz3p$#$t43W8gBHvCS@viPBaJmdmfSSe#XF7NTcpu2Vu@DFgvnmRrjA2A#eKSaQMi$dqR5Lm86EYqHAa3%' --name forum alura-forum:latest`

## Test coverage

`open target/site/jacoco/index.html`

## Checking the API documentation through Swagger

`http://localhost:8080/swagger-ui.html`

## Endpoints

The application has six endpoints:
* /auth
* /topics/listAll
* /topics/listByCourseName
* /topics/register
* /topics/details/{id}
* /topics/update/{id}
* /topics/delete/{id}


#### POST /auth

Endpoints for user authentication (JWT):

Usage:

```
curl --location --request POST 'http://localhost:8080/auth' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "user@email.com",
    "password": "123456"
}'
```

Response:

```
{
  "token": "eyJhbGciOiJIUzI1NiJ9.
    eyJpc3MiOiJBUEkgb2YgQWx1cmEgRm9ydW0iLCJzdWIiOiIxIiwiaWF0IjoxNjQ0NzA1MDg5LCJleHAiOjE2NDQ3MDY4ODl9.
    6cozKecWTlN_LTNJQXkaZewTiU_PqXSRg80JnCR0Yr8",

  "type": "Bearer"
}
```


#### GET /topics/listAll

Endpoints that returns all topics registered:

Usage:

```
curl --location --request GET 'http://localhost:8080/topics/listAll'
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.
  eyJpc3MiOiJBUEkgb2YgQWx1cmEgRm9ydW0iLCJzdWIiOiIxIiwiaWF0IjoxNjQ0NzA1MDg5LCJleHAiOjE2NDQ3MDY4ODl9.
  6cozKecWTlN_LTNJQXkaZewTiU_PqXSRg80JnCR0Yr8'
```

Response:

```
[
  {
    "id": 1,
    "title": "query",
    "message": "Error when creating a project",
    "dateOfCriation": "2021-05-05T18:00:00"
  },
  {
    "id": 2,
    "title": "query 2",
    "message": "Project is not compiling",
    "dateOfCriation": "2021-05-05T19:00:00"
  },
  {
    "id": 3,
    "title": "query 3",
    "message": "Tag HTML",
    "dateOfCriation": "2021-05-05T20:00:00"
  }
]
```

#### GET /topics/listByCourseName;

Endpoints that returns the topics registered, given a course name:

Usage:

```
curl --location --request GET 'http://localhost:8080/topics/listByCourseName?courseName=Spring Boot'
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.
  eyJpc3MiOiJBUEkgb2YgQWx1cmEgRm9ydW0iLCJzdWIiOiIxIiwiaWF0IjoxNjQ0NzA1MDg5LCJleHAiOjE2NDQ3MDY4ODl9.
  6cozKecWTlN_LTNJQXkaZewTiU_PqXSRg80JnCR0Yr8'
```

Response:

```
[
  {
    "id": 1,
    "title": "query",
    "message": "Error when creating a project",
    "dateOfCriation": "2021-05-05T18:00:00"
  },
  {
    "id": 2,
    "title": "query 2",
    "message": "Project is not compiling",
    "dateOfCriation": "2021-05-05T19:00:00"
  }
]
```


#### POST /topics/register

Endpoints that register a topic:

Usage:

```
curl --location --request POST 'http://localhost:8080/topics/register' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.
  eyJpc3MiOiJBUEkgb2YgQWx1cmEgRm9ydW0iLCJzdWIiOiIxIiwiaWF0IjoxNjQ0NzA1MDg5LCJleHAiOjE2NDQ3MDY4ODl9.
  6cozKecWTlN_LTNJQXkaZewTiU_PqXSRg80JnCR0Yr8'
--data-raw '{
    "title": "just a title test 1",
    "message":"just a message test",
    "courseName":"Spring Boot"
}
```

Response:

```
{
  "id": 4,
  "title": "just a title test 1",
  "message": "just a message test",
  "dateOfCriation": null
}
```

#### GET /topics/details/1

Endpoints that returns the details of a topic, given a topic id:

Usage:

```
curl --location --request GET 'http://localhost:8080/topics/details/1'
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.
  eyJpc3MiOiJBUEkgb2YgQWx1cmEgRm9ydW0iLCJzdWIiOiIxIiwiaWF0IjoxNjQ0NzA1MDg5LCJleHAiOjE2NDQ3MDY4ODl9.
  6cozKecWTlN_LTNJQXkaZewTiU_PqXSRg80JnCR0Yr8'
```

Response:

```
[
  {
    "id": 1,
    "title": "query",
    "message": "Error when creating a project",
    "dateOfCriation": "2021-05-05T18:00:00",
    "author": "user",
    "status": "NOT_ANSWERED",
    "answers": []
  }
]
```

#### DELETE /topics/delete/1

Endpoints that delete a topic, given a topic id:

Usage:

```
curl --location --request DELETE 'http://localhost:8080/topics/delete/1'
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.
  eyJpc3MiOiJBUEkgb2YgQWx1cmEgRm9ydW0iLCJzdWIiOiIxIiwiaWF0IjoxNjQ0NzA1MDg5LCJleHAiOjE2NDQ3MDY4ODl9.
  6cozKecWTlN_LTNJQXkaZewTiU_PqXSRg80JnCR0Yr8'
```

## Project is still in development <img src="https://img.icons8.com/emoji/48/000000/woman-construction-worker.png"/>
