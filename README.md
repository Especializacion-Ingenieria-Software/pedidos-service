# Pedidos-Service
Responsable de la gestión del ciclo de vida de los pedidos: creación, actualización, y seguimiento


## Overview

The **pedidos-Service** is a microservice responsible for managing the lifecycle of restaurant pedidos. It handles pedido creation, pedido updates, and pedido tracking. The service also communicates with the **InventoryService** to validate ingredient availability before confirming the pedido.

This service is part of a larger **Restaurant pedido Management System**, designed to facilitate online pedidoing, inventory management, and kitchen communication in a restaurant chain.

## Features

- **pedido Creation**: Allows customers to place new pedidos.
- **pedido Update**: Updates the status and details of existing pedidos.
- **pedido Tracking**: Provides the ability to track the status of pedidos.
- **Integration with inventario-service**: Ensures availability of ingredients before confirming the pedido.
- **Event-driven Architecture**: Publishes events like `pedidoCreated` to communicate with other services (e.g., **InventoryService**).

## Architecture

The service follows **Domain-Driven Design (DDD)** principles, with clear boundaries and responsibilities:

- **Entities**: pedido, Cliente, Item de pedido
- **Value Objects**: dirección de entrega, total del pedido
- **Aggregates**: pedido (root)
- **Repositories**: pedidoRepository
- **Domain Services**: Servicio de Creación de Pedidos, Servicio de Cálculo de Precios


### Communication with Other Services

- The **pedido-Service** communicates with the **inventario-service** to validate ingredient availability.
- Events are published for other services to react to, such as `pedidoCreated`, `AvailabilityValidated`, and `InventoryUpdated`.

## Endpoints

### Create pedido
- **POST** `/pedidos`
- **Description**: Creates a new pedido in the system.
- **Request Body**: JSON containing pedido details (items, customer information, delivery address).
- **Response**: pedido confirmation details (pedido ID, status).

### Update pedido
- **PUT** `/pedidos/{pedidoId}`
- **Description**: Updates an existing pedido.
- **Request Body**: JSON containing updated pedido details.
- **Response**: Updated pedido details.

### Get pedido Status
- **GET** `/pedidos/{pedidoId}`
- **Description**: Retrieves the current status of an pedido.
- **Response**: pedido details with status (e.g., "Pending", "Confirmed", "In Progress", "Delivered").

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
   git clone https://github.com/yourusername/pedido-service.git
   cd pedido-service

### Folder Structure

```bash

pedidos-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/restaurante/pedidos/
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
│   │   │       └── PedidosApplication.java   # Microservice entry point (main)
│   │   └── resources/                      # Configuration files (e.g., application.properties)
│   └── test/                              # Unit and integration tests
└── pom.xml                                 # Project configuration file (if using Maven)

```
