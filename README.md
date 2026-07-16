# Inventory Management System

## Overview

The Inventory Management System is a microservices-based backend application developed using Java and Spring Boot.

The application is designed to manage complete inventory operations including product management, supplier management, stock tracking, purchase management, sales management, and report generation.

This project follows a distributed microservices architecture using Spring Cloud components such as Eureka Server for service discovery, API Gateway for centralized routing, Config Server for centralized configuration management, and OpenFeign Client for communication between microservices.

The main objective of this project is to build a scalable and maintainable inventory solution where each business module is developed as an independent service.

---

# Architecture

The application follows a Microservices Architecture where each service is responsible for a specific business domain.

Each microservice contains its own business logic and communicates with other services through REST APIs.

The system uses Eureka Server for service registration and discovery. API Gateway acts as a single entry point for client requests and routes them to the appropriate services. Config Server manages centralized configuration across all microservices.

OpenFeign Client is used for communication between services, allowing one microservice to consume another microservice's REST APIs easily.

---

# Technology Stack

## Backend Technologies

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- REST API


## Microservices and Cloud

- Spring Cloud
- Eureka Server
- API Gateway
- Config Server
- OpenFeign Client


## Database

- MySQL


## Development Tools

- Maven
- Git
- GitHub
- Postman
- IntelliJ IDEA


---

# Microservices Description

## Config Server

The Config Server provides centralized configuration management for all microservices.

Responsibilities:

- Maintain application configurations in one place
- Provide configuration properties to different services
- Simplify environment management


---

## Eureka Server

Eureka Server works as a service registry for all microservices.

Responsibilities:

- Register microservices
- Maintain available service instances
- Enable dynamic service discovery


---

## API Gateway

API Gateway provides a centralized entry point for accessing all backend services.

Responsibilities:

- Route client requests to respective services
- Handle communication between client and microservices
- Provide centralized request handling


---

## Product Service

Product Service manages product-related operations.

Responsibilities:

- Create new products
- Update product information
- Delete products
- Retrieve product details
- Manage product categories


---

## Supplier Service

Supplier Service manages supplier information.

Responsibilities:

- Add supplier details
- Update supplier information
- Delete supplier records
- Retrieve supplier details


---

## Inventory Service

Inventory Service manages product stock information.

Responsibilities:

- Maintain available stock quantity
- Track inventory levels
- Update stock during purchase and sales operations
- Check product availability


Inventory Service communicates with Product Service using OpenFeign Client to retrieve product information.


---

## Purchase Service

Purchase Service manages product purchasing operations.

Responsibilities:

- Create purchase transactions
- Maintain purchase records
- Manage purchased items
- Update inventory after successful purchase


Purchase Service communicates with:

- Product Service
- Supplier Service
- Inventory Service

using OpenFeign Client.

---

## Sales Service

Sales Service manages product sales operations.

Responsibilities:

- Create sales transactions
- Maintain sales records
- Reduce inventory stock after sales
- Track sold products


Sales Service communicates with Inventory Service and Product Service using OpenFeign Client.

---

## Report Service

Report Service provides business reports based on data collected from different services.

Responsibilities:

- Generate purchase reports
- Generate sales reports
- Provide inventory information
- Combine data from multiple services


Report Service communicates with other services using OpenFeign Client.

---

# Features

## Product Management

The system provides complete product management functionality including:

- Add products
- Update products
- Delete products
- View product details


## Supplier Management

The application manages supplier-related operations:

- Create suppliers
- Update supplier information
- Retrieve supplier details


## Inventory Management

The inventory module maintains stock information and automatically updates stock during purchase and sales transactions.


## Purchase Management

The purchase module handles:

- Purchase creation
- Supplier association
- Product purchase details
- Inventory stock updates


## Sales Management

The sales module manages:

- Sales transactions
- Product quantity reduction
- Sales history


## Report Generation

The reporting module provides consolidated information from different services for business analysis.


---

# Inter-Service Communication

The application uses OpenFeign Client for communication between microservices.

Example communication:

- Purchase Service retrieves product details from Product Service.
- Purchase Service retrieves supplier information from Supplier Service.
- Purchase Service updates inventory information through Inventory Service.
- Sales Service communicates with Inventory Service to update stock.
- Report Service consumes data from multiple services to generate reports.

OpenFeign simplifies REST API communication by allowing services to call each other using declarative interfaces.

---

# Database Design

Each microservice manages its own database tables based on its business responsibility.

Product Service manages product-related data.

Supplier Service manages supplier information.

Inventory Service maintains stock details.

Purchase Service manages purchase and purchase item details.

Sales Service manages sales and sales item details.

This approach keeps services independent and improves scalability and maintainability.

---

# API Testing

The APIs can be tested using:

- Postman
- Swagger UI


Author

Manjula G

Java Backend Developer

Skills:

Java | Spring Boot | Microservices | Spring Cloud | REST API | MySQL
