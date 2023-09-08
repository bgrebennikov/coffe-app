# Sample of spring security using JWT | kotlin-jwt-spring-security-6
### DB: PostgreSQL | Flyway migrations

## POST
    localhost:9090/api/auth/register
    {
        "email": "some.user@gmail.com",
        "username": "anonym",
        "password": "123456"
    }

## POST
    localhost:9090/auth/authenticate
    {
        "username": "anonym",
        "password": "123456"
    }

### RESPONSE
    {
        "token": "eyJhbG..."
    }


After registration/authentication we get a <b>JWT Token</b> in response and have to put it in header to access secured endpoints.

### <b>Authorization | Bearer <token> </b>
    
![image](https://user-images.githubusercontent.com/90960365/215284250-8f7ec7d4-a28d-4eb9-af6c-9bc577310a27.png)

## GET

    localhost:9090/api/home //Header is present

#### RESPONSE

    Hello, welcome to secured endpoint.
    
# Aditional

### V2__CreateUsersTable.sql

    CREATE TABLE IF NOT EXISTS users
    (
        id       int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
        email    TEXT,
        username TEXT,
        password TEXT,
        role     VARCHAR(20)
    );

### Application.yml

    server:
      port: 9090
      servlet:
        context-path: /api

    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/${DB_NAME}
        username: ${DB_USERNAME}
        password: ${DB_PASSWORD}
        driver-class-name: org.postgresql.Driver
      jpa:
        hibernate:
          ddl-auto: validate
        show-sql: true
        properties:
          hibernate:
            format_sql: true
      flyway:
        enabled: true
        url: ${spring.datasource.url}
        user: ${spring.datasource.username}
        password: ${spring.datasource.password}
        baseline-on-migrate: true

    custom:
      jwt:
        secret: 77397A24432646294A404E635266556A586E3272357538782F4125442A472D4B
        expiration: 720000

