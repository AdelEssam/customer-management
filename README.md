# Account Management APIs  #

This repository APIs to be used for opening a new “current account” of already existing customers.

### What is this repository for? ###

* Version: 0.0.1-SNAPSHOT

* Technologies: Spring boot, Spring AOP and H2 in memory DB 

### How do I get set up? ###

## Clone Repo

    git clone https://github.com/AdelEssam/customer-management.git
    
## Run

    mvn spring-boot:run
    
    
## REST APIs

    The rest APIs of Account management app is described below.

### Request to retrieve all Customers

`GET /service/customer`  

### Request to open new account

`POST /service/openAccount` 
        
        {
            "customerID":"1",
            "initialCredit":10
        }
    
       

### Request to customer by ID

`GET /service/customer/{id}`  

## NOTE: YOU CAN FIND POSTMAN COLLECTION ATTACHED TO REPO









