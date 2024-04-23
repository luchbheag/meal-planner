# Simple Meal Planner
## Project Description
The simple Meal Planner project based on [Hyperskill project](https://hyperskill.org/projects/318?track=12) (first 4 stages). It allows you to add meals to database, store them and show them by chosen category. 

Used technologies: Java, Hibernate, MySQL8, Docker
## How to Install and Run the Project
The `maven` and `docker` are required. First, run database container in console
```
docker-compose -f docker-compose-test.yml up
```
Then, in folder `meal-planner` open console and run
```
mvn clean package
```
and then
```
java -jar target/meal-planner-1.0-SNAPSHOT.jar
```

## How to Use the Project

## TODO
* Add automatic tests
* Add example of how to use it (from Hyperskill description of stages)
* Make deploy work with docker-file and docker-compose instead of running .jar manually 
