# OrderService
Responsable de la gestión del ciclo de vida de los pedidos: creación, actualización, y seguimiento


## Overview

The **OrderService** is a microservice responsible for managing the lifecycle of restaurant orders. It handles order creation, order updates, and order tracking. The service also communicates with the **InventoryService** to validate ingredient availability before confirming the order.

This service is part of a larger **Restaurant Order Management System**, designed to facilitate online ordering, inventory management, and kitchen communication in a restaurant chain.

## Features

- **Order Creation**: Allows customers to place new orders.
- **Order Update**: Updates the status and details of existing orders.
- **Order Tracking**: Provides the ability to track the status of orders.
- **Integration with InventoryService**: Ensures availability of ingredients before confirming the order.
- **Event-driven Architecture**: Publishes events like `OrderCreated` to communicate with other services (e.g., **InventoryService**).

## Architecture

The service follows **Domain-Driven Design (DDD)** principles, with clear boundaries and responsibilities:

- **Entities**: Order, Customer, OrderItem
- **Value Objects**: DeliveryAddress, OrderTotal
- **Aggregates**: Order (root)
- **Repositories**: OrderRepository
- **Domain Services**: OrderCreationService, PriceCalculationService

### Communication with Other Services

- The **OrderService** communicates with the **InventoryService** to validate ingredient availability.
- Events are published for other services to react to, such as `OrderCreated`, `AvailabilityValidated`, and `InventoryUpdated`.

## Endpoints

### Create Order
- **POST** `/orders`
- **Description**: Creates a new order in the system.
- **Request Body**: JSON containing order details (items, customer information, delivery address).
- **Response**: Order confirmation details (order ID, status).

### Update Order
- **PUT** `/orders/{orderId}`
- **Description**: Updates an existing order.
- **Request Body**: JSON containing updated order details.
- **Response**: Updated order details.

### Get Order Status
- **GET** `/orders/{orderId}`
- **Description**: Retrieves the current status of an order.
- **Response**: Order details with status (e.g., "Pending", "Confirmed", "In Progress", "Delivered").

## Technologies

- **Programming Language**: Java (or your preferred language)
- **Framework**: [Spring Boot / Quarkus / Micronaut] (choose the framework based on your preference)
- **Database**: PostgreSQL (or MongoDB)
- **Messaging**: Kafka or RabbitMQ (for event-driven communication)
- **Testing**: JUnit / Mockito (or testing framework of your choice)

## Setup

### Prerequisites

- Java 11 or higher
- PostgreSQL or MongoDB (depending on your choice)
- Kafka or RabbitMQ (for event communication)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/order-service.git
   cd order-service

### Folder Structure

python```
order-service/

├── src/

│   ├── main/

│   │   ├── java/

│   │   │   └── com/restaurante/order/

│   │   │       ├── application/           # Application logic (controllers, services)

│   │   │       │   ├── controllers/       # REST controllers (API Endpoints)

│   │   │       │   └── services/          # Application services (business orchestration)

│   │   │       ├── domain/                # Domain logic (aggregates, entities, services)

│   │   │       │   ├── entities/          # Domain entities (e.g., Ingredient, Recipe)

│   │   │       │   ├── repositories/      # Data access repositories

│   │   │       │   ├── services/          # Domain services (e.g., AvailabilityValidationService)

│   │   │       │   └── valueobjects/      # Value objects (e.g., Quantity, UnitOfMeasure)

│   │   │       ├── infrastructure/        # Infrastructure logic (persistence, messaging)

│   │   │       │   ├── persistence/       # Repository implementations (DB access)

│   │   │       │   └── messaging/         # Communication with other services (Kafka, RabbitMQ)

│   │   │       └── OrderApplication.java   # Microservice entry point (main)

│   │   └── resources/                      # Configuration files (e.g., application.properties)

│   └── test/                              # Unit and integration tests

└── pom.xml                                 # Project configuration file (if using Maven)

```
