# Accounting Service

## Overview
This project is an Accounting Service API that manages user accounts and transactions. It provides endpoints for listing accounts and transactions, with pagination and sorting capabilities.

## Project Structure
```
.
├── README.md
├── accountingService
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── mq
│       │   │           ├── AccountingServiceApplication.java
│       │   │           ├── controller
│       │   │           ├── dto
│       │   │           ├── exception
│       │   │           ├── persistence
│       │   │           ├── service
│       │   │           └── util
│       │   └── resources
│       └── test
├── accountingSpecification
│   ├── pom.xml
│   └── v1
│       ├── doc
│       ├── sample
│       └── schema
└── pom.xml
```

## How to Run the Project

1. Ensure you have Java 17+ and Maven installed on your system.
2. Clone the repository to your local machine.
3. Navigate to the project root directory.
5. Set up a database and update connection parameters in Dev/Prod property files 
6. Run the following command to build the project:
   ```
   mvn clean install
   ```
7. To run the application, use in accountingService:
   ```
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   ```
   This will start the application with the `dev` profile.


## Sample GET Request
Dev credentials
- username = user
- password = user

Here's an example GET request to list accounts for a user:

```
GET http://localhost:8080/v1/accounting/accounts?userId=123
```

Here's an example GET request to list of transaction on given account

```
http://localhost:8080/v1/accounting/account/3/transactions?page=0&size=10&sort=transactionDateTime,desc
```

## Database Scripts

The SQL DDL scripts for creating the necessary database tables can be found in the `database` folder in the project root. This folder contains the following files:

- `schema.sql`: Contains the DDL statements for creating the tables.

To set up your database, run these scripts in your SQL client in the order mentioned above.

## Configuration

The application uses different property files for different environments:

- `application.properties`: Default configuration
- `application-dev.properties`: Development environment configuration
- `application-qa.properties`: QA environment configuration
- `application-prod.properties`: Production environment configuration

To use a specific profile, set the `spring.profiles.active` environment variable or use the `-Dspring.profiles.active` VM option when running the application.

## Security

The API uses Bearer Token Authentication (JWT). Ensure you include a valid JWT token in the Authorization header when making requests to protected endpoints.

## Running Tests

To run the unit tests, use the following command:

```
mvn test
```
