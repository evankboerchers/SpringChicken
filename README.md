# Spring Framework Demo Service 2021
**Authors:**<br/>
Evan Boerchers<br/>
Kenneth Loughery<br/>
Myles Borthwick<br/>

## About

The purpose of this repository is to demostrate the Spring framework and its use in creating a basic webservice that serves API requests. This build includes the following features:

- Gradle as a build tool
- Spring dependency management
- Use of Spring boot for autoconfiguring needed beans and building the application context.
- Configured Tomcat webserver with resources for serving API calls
- Bearer token authorization using filters that leverage Spring roles
- Use of springs testing tools for unit tests and test configurations
- Spring Data JPA as our data access tool
- Caching configuration using spring caching tool and Redis as the storage server
- Custom shutdown hook with shutdown delay and graceful shutdown protocol
- Checkstyle and Spotbugs for static analysis
- Basic logging configuration with logback
- Docker compose file for building mySQL and Redis containers

Our project architecture constists of 4 modules/layers:

1. Infrastruture: Retrieves data from databases and cache into redis
2. Business_Logic: Defines the interfaces of services and data access objects
3. Presentation: Contains controllers that defines resources and defines security and protocols to these resources
4. Main: Entry point to application. Configures spring application context and binds all layers together.

## Setup

Setup for this service is simple. The requirements for this running this application are as follows:

- Sdkman for java version control (application uses java 11) 
- Docker for running Redis/mySQL containers

### First time setup

In order to install relevant pre-requsitees run `firstTimeSetup.bash`, this will install sdkman, docker aswell as jetbrains(for IntelliJ) to your system

## Running the Service

### Docker

The service relies on the presence of 2 docker containers. The first is an mySQL server that is built with a demo database. The second is a Redis server used for the services caching needs.

To run the docker containers there is a `docker-compose` file. Execute `docker-compose -f docker-compose-clean.yml up` to start up the needed containers.

### Web Service

The build tool used is `gradle`, therefore a few tasks can be executed via `./gradlew`. A few key tasks exist:

- `./gradlew tasks`: Gives a list of tasks
- `./gradlew test`: Builds project and runs unit tests
- `./gradlew check`: Builds project and runs static analysis + unit tets
- `./gradlew cleans`: Clears all build files and gradle generated files
- `./gradlew run` Executes the application

The web service can be run using the `./gradlew run` command or directly from an IDE using `Main`.


## Using the Service

The web service will by default run on port 8080. A variety of calls can be made, i.e:

1. `curl -H "Authorization: Bearer 42" "http://localhost:8080/customer/v1"` for a list all customer info
2. `curl -H "Authorization: Bearer 42" "http://localhost:8080/customer/v1/456"` for the customer info of given customer number
3. `curl -H "Authorization: Bearer 42" "http://localhost:8080/customer/v1/orders/456"` for a list of orders/customer info of given customer number
4. `curl -H "Authorization: Bearer 42" "http://localhost:8080/health"` for a server health report

**Note:** The bearer tokens are for authorization purposes, resources will require this authorization or an access forbidden response will be recieved.

## Branches

This repository contains a few branches. Each branch demonstrates some differing features.

### Main

As the name infers this is the core of the project. 

### Data Sharding

This branch demonstrates a different infrastructure layer. In order to demonstrate how our JPA persistence layer can implement dynamic datasource routing, the mySQL customers databased has been horizontally partitioned into multiple databases. Additionally a new database exists that contains the customer number as the primary key, with the respective host, port and database name of that customers information contained in its columns.

Whenever a customers info is needed the host, port and database of this customer is found. Then using the JPA entitymanager either a new connection to the host+port is created or an existing connection is reused. The database were this info exists is then selected and the information retrieved.

This branch takes advantage of connection pooling, and as a result connections to a specific host+port are shared.

**Note:** The orders service is not implemented in this build, therefore api calls related to orders will not function.

### Spring_JDBC_Legacy

This branch demonstrates an infrastructure layer that uses Spring JDBC as the data access tool. This tool uses more straightforward raw SQL queries while removing some of the boilerplate JDBC error handling/query creation.

**Note:** This code is legacy and the branch is functional but missing alot of features that were added later.

### JOOQ_Legacy

This branch demonstrates the JOOQ data access tool and how it can be used in a spring solution. Despite this tool not being a 'Spring' tool, it is still a popular choice and is supported within the Spring ecosystem.

**Note:** This code is legacy and the branch is functional but missing alot of features that were added later.
