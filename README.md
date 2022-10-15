# Microservices template

This is a template for a Spring boot Microservices project.
First thing to know is that is based on DOCKER so every microservice is a container that can run by itself.
There are 6 microservice and 1 shared library:
* Zipkin --> Container userd to trace request between microservices.
* MySql --> Container used to host the DB
* Eureka --> In order to list all the microservices up and running
* ApiGW --> Load Balancer
* Auth --> Is responsible of Authentication and Registration of users
* Core --> Is the main microservices that implements the business logic and talk with the DB
* Clients --> Is a shared library that allows to implement FeignClients and share common utility files or classes.

## How to start
* Run the mvn clean and mvb package to create the docker images. (Optional)
* Run docker-compose up
