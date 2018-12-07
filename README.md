# NewsAPI

[![Build Status](https://travis-ci.org/nicolasmanic/NewsAPI.svg?branch=master)](https://travis-ci.org/nicolasmanic/TriangleChallenge)
[![codecov](https://codecov.io/gh/nicolasmanic/NewsAPI/branch/master/graph/badge.svg)](https://codecov.io/gh/nicolasmanic/TriangleChallenge)

## Prerequisite
- JDK 8+
- Maven 3+

## Build & Run

- Unit testing
```
mvn clean test
```

- Integration testing
```
mvn clean verify -Pfailsafe -DskipTests
```

- Package to fat jar and run
```
//Creates a NewsAPI.jar
mvn clean package

//Run jar
java -jar NewsAPI.jar
```

- Unit testing & Integration testing
```
mvn clean verify -Pfailsafe
```

- Run with maven
```
mvn spring-boot:run
```

**NOTE:** You can also use the wrapped maven version provided to run any maven command, simply ise ```mvnw``` instead
of ```mvn```

## API

**Article Controller**
Article retrieval operations

Retrieve by author name
``` 
GET localhost:8080/articles/byAuthor?firstName={First name}&lastName={Last name}
```

Retrieve by id
``` 
GET localhost:8080/articles/byId/{id}
```

Retrieve by keyword
``` 
GET localhost:8080/articles/byKeyword/{keyword}
```

Retrieve by Period. If no start/end period is specified, the date is set to start of time/until now respectively.
``` 
GET localhost:8080/articles/byPeriod?startDate={Start Date}&endDate={endDate}
```
**Editor Controller**
CUD Operations on articles, accessible only by editors.

Create article.
``` 
POST localhost:8080/post
body: {
        "authors": ["string"],
        "description": "string",
        "header": "string",
        "keywords": [ "string" ],
        "links": [
          {
            "href": "string",
            "templated": true
          }
        ],
        "publication_date": "string",
        "text": "string"
      }
```

Update article.
``` 
PUT localhost:8080/post
body: {
        "authors": ["string"],
        "description": "string",
        "header": "string",
        "id": "string",
        "keywords": [ "string" ],
        "links": [
          {
            "href": "string",
            "templated": true
          }
        ],
        "publication_date": "string",
        "text": "string"
      }
```

Delete article.
``` 
PUT localhost:8080/post/{id}
```

This project uses swagger to auto-generate a documentation please visit the following [LINK](http://localhost:8080/swagger-ui.html) 
on your local running server for more information.

## Design Decisions

The application is based on the Spring Boot framework and can be separated in 3 main parts:

- Rest controller.
- Service Layer.
- Repository.

### Rest controller

It is responsible for exposing the endpoints described in section API[#api] section and displays any exception
it may arise by returning a json message informing the user about the error. Finally handles the mapping between the dto
and the entity that is used in the rest of the application. The article DTO is separated into Article & Author since in the
future we may need to add more data into the Author object.

**Note**: The editor API should be protected and allow only registered users with "editor" privileges to access it. Spring
Security could be used for this use case.

### Service Layer
Since the application logic is fairly simple service layer mostly connects controller with repository. In a few cases it checks
for missing data avoiding the need for unnecessary requests to the db.

### Repository

A nosql (mongodb) repository was selected for this use cases, the reasons behind it was that an article is not a strictly
structured data and using a sql scheme would result in multiple probably expensive joins.

For more information please see the provided javadocs. Also for specific implementation decisions please see the 
corresponding ```@ImplNote``` annotation


## TODOs

1. Add roles & credentials to authors and secure the Editor controller.
2. Add a complete CI/CD pipeline.
3. Add an external nosql instance instead of a embedded one. This will also healp make the application reactive since now the 
embedded mongo does not contain reactive drivers.
4. Add cache in application level, popular articles can be served by the cache and avoid calls to the dn. As articles are 
unlike to change cache invalidation is not a big problem.