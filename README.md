# algorithm-based-solved-tasks

## Table of contents
* [Introduction](#Introduction)
* [Technologies](#Technologies)
* [Launch](#Launch)
* [Developer](#Developer)

## Introduction
This project is a client-server web-application that 
let you get a solution of one of the tasks based on 
sophisticated algorithm.

For your convenience there is an opportunity to save
current task with corresponding input data to a
file or global database and retrieve it back getting
a proper solution.

Flexible architecture makes adding new tasks easy to
perform.

Database can be changed with small changes in 
TaskInputDAO.java and schema.sql files that only 
needed to adapt to given SQL dialect.

## Technologies
* Java 17
* Spring Boot 2.6.7
* Spring MVC
* Spring Data JDBC
* Thymeleaf
* H2 in-memory database
* Spring Boot Test (including JUnit 5 and Mockito)

## Launch
Project is building into an executable .jar file. So
you can run it either in command prompt or your other
favorite environment that support JVM for Java 17.
To access a main page of the application go to 
http://localhost:8080/ in your internet browser.

## Developer
Application was developed by Leonid Asanin.

Contact info:
* [E-mail] [Leonid Asanin](mailto:l.asanin@mail.ru)
* [GitHub] (https://github.com/LeonidAsanin)
