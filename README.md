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

#### POST /bankAccount/details

Endpoints that returns the bank account details:

Usage:

```
curl --location --request POST 'http://localhost:8080/bankAccount/details' \
--header 'Content-Type: application/json' \
--data-raw '{
    "accountNumber": 123456789,
    "pin": 1234
}'
```

Response:

```
{
    "accountNumber": 123456789,
    "date": "2022-01-08T11:21:20.955+00:00",
    "balance": 800,
    "availableToSpend": 1000,
    "statements": []
}
```

#### GET /atm/transactions

Endpoints that returns the ATM transactions:

Usage:

```
curl --location --request GET 'http://localhost:8080/atm/transactions'
```

Response:

```
{
    "statements": [
        {
            "type": "WITHDRAW",
            "amount": 500,
            "date": "2022-01-08T11:24:16.715+00:00"
        }
    ],
    "notesAvailable": {
        "50": 0,
        "20": 30,
        "10": 30,
        "5": 20
    },
    "date": "2022-01-08T11:28:48.073+00:00",
    "balance": 1000.0
}
```

## Project is still in development <img src="https://img.icons8.com/emoji/48/000000/woman-construction-worker.png"/>