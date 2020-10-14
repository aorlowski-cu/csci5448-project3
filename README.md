# csci5448-project3
## Project Team Name: BeastKeeper
## Team Members: Alex Orlowski, Andy Fan, Lincoln Schafer
## Overview
1. Developed on jdk version 11
2. IDE: IntelliJ

## Output
Output all the process to file "30RollsMaxOutput.txt"
Output also recoreded for 45 and 60 rolls, in the appropriately name text files.

## Project Structure
Our project can be split into several components (within different package)
1. /rolls:
    1. Roll - Abstract class for all rolls
    2. RollStore - store that handles all of the data tracking, as well as managing customers and their orders
    3. RollFactory - creates a roll based on input parameters using factory pattern
    4. Condiment Decorator - adds condiments to the rolls using decorator pattern
    5. Topping - A condiment that can be added
    6. Sauce - A condiment that can be added
    7. Filling - A condiment that can be added
    8. EggRoll - A type of roll
    9. JellyRoll - A type of roll
    10. PastryRoll - A type of roll
    11. SausageRoll - A type of roll
    12. SpringRoll - A type of roll
    
    Roll classes are used to calculate cost, and how many extras.  
    RollStore used to handle the simulation as well as listen to the customers for when they order
    RollFactory used to create rolls for orders.

2. /customers:
    1. Customer - abstract class for all customers
    2. Customer factory - class used to create customers using factory pattern
    3. Business Customer - A type of customer
    4. Catering Customer - A type of customer
    5. Casual Customer - A type of customer

    Customer classes used to determine how many rolls they will buy
    Customer factory used to generate different customers

3. /UnitTests:
    1. JUnit tests

## Patterns
1. Decorator Pattern: Condiment decorator <br/>
    We delegate the addition of extras to the condiment decorator.  
    Rolls get extras during run time, instead of overriding the super class.
    Each roll has the ability to have many or none extras for each type, decided randomly.
    
2. Observer Pattern: Customer, BusinessCustomer, CasualCustomer, CateringCustomer, RollStore <br/>
     The customer classes are listened to by the RollStore, whenever an order is placed.  
     Once the order is placed, the customer alerts the store and the store records the record of it to output daily.

3. Factory Pattern: CustomerFactory, RollFactory <br/>
     The customer factory and roll factory classes do just that, generate either a list of customers for the day or the rolls for each customers order respectively

      

## To run the application:
1. Use IntelliJ to import the project.
2. Run `Main` and then navigate to the output text file to see the results

## Issues:
1. We tried to use built in UML diagram to automatically build our UML diagrams at first. However, it gets
pretty messy and complex, does not do what we expected. In the end we choose to use LucidChart UML templates
to build our UML diagram.
