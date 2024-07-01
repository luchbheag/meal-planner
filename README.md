# Simple Meal Planner
## Project Description
The simple Meal Planner console project based on [Hyperskill project](https://hyperskill.org/projects/318?track=12) (first 4 stages). It allows you to add meals to database, store them and show them by chosen category. 

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
Example of standard execution:
```
What would you like to do (add, show, exit)?
> add
Which meal do you want to add (breakfast, lunch, dinner)?
> lunch
Input the meal's name:
> salad
Input the ingredients:
> lettuce, tomato, onion, cheese, olives
The meal has been added!
What would you like to do (add, show, exit)?
> add
Which meal do you want to add (breakfast, lunch, dinner)?
> lunch
Input the meal's name:
> omelette
Input the ingredients:
> eggs, milk, cheese
The meal has been added!
What would you like to do (add, show, exit)?
> add
Which meal do you want to add (breakfast, lunch, dinner)?
> breakfast
Input the meal's name:
> oatmeal
Input the ingredients:
> oats, milk, banana, peanut butter
The meal has been added!
What would you like to do (add, show, exit)?
> show
Which category do you want to print (breakfast, lunch, dinner)?
> breakfast
Category: breakfast
Name: oatmeal
Ingredients:
oats
milk
banana
peanut butter
What would you like to do (add, show, exit)?
> show
Which category do you want to print (breakfast, lunch, dinner)?
> lunch
Category: lunch

Name: salad
Ingredients:
lettuce
tomato
onion
cheese
olives

Name: omelette
Ingredients:
eggs
milk
cheese

What would you like to do (add, show, exit)?
> exit
Bye!
```
Example of execution with warnings:
```
What would you like to do (add, show, exit)?
> show
Which category do you want to print (breakfast, lunch, dinner)?
> dinner
No meals found.
What would you like to do (add, show, exit)?
> show
Which category do you want to print (breakfast, lunch, dinner)?
> brunch
Wrong meal category! Choose from: breakfast, lunch, dinner.
> dinner
No meals found.
What would you like to do (add, show, exit)?
> exit
Bye!
```
## TODO
* Add automatic tests
* Make deploy work with docker-file and docker-compose instead of running .jar manually 
