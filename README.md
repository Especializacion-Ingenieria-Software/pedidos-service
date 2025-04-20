# Pedidos-Service
Servicio responsable de la gestión del ciclo de vida de los pedidos: creación, actualización y seguimiento.


## Overview
El **Pedidos-Service** es un microservicio encargado de gestionar el ciclo de vida de los pedidos en un restaurante. Maneja la creación de pedidos, actualizaciones y seguimiento. El servicio está diseñado para formar parte de un sistema de gestión de pedidos más amplio para restaurantes.

## Features


-**Creación de Pedidos**: Permite a los clientes realizar nuevos pedidos.

-**Consulta de Pedidos**: Proporciona información detallada de pedidos existentes.

-**Gestión de Clientes**: Administra la información de los clientes.

-**Arquitectura Hexagonal**: Implementa una arquitectura limpia con separación clara entre dominio, aplicación e infraestructura.

-**Pruebas Unitarias**: Cobertura completa de tests para cada capa de la aplicación.


## Architecture

El servicio sigue los principios de **Domain-Driven Design (DDD)**, con límites y responsabilidades claramente definidos:

-**Entidades**: Order, Customer, OrderItem

-**Value** Objects: Address, Item

-**Repositorios**: OrderRepository, CustomerRepository, OrderItemRepository

-**Servicios** de Dominio: OrderHandleService

-**DTOs**: OrderDTO, OrderItemDTO


## Tecnologías


-**Lenguaje** de Programación: Java 21

-**Framework**: Spring Boot 3.4.3

-**Base** de Datos: MongoDB Atlas

-**Testing**: JUnit 5 y Mockito

-**Gestión** de Dependencias: Maven

-**Contenedorización**: Docker y Docker Compose

## Endpoints 

### Gestión de Pedidos

#### Crear Pedido


-**POST** `/orders/order`

-**Descripción**: Crea un nuevo pedido en el sistema.

-**Cuerpo** de la Solicitud: JSON con detalles del pedido (items, cliente, comentarios).

-**Respuesta**: Detalles del pedido creado con ID asignado

```json
// Ejemplo de solicitud
{
  "comments": "Entregar en la puerta principal",
  "orderTotal": 45.75,
  "customer": 101,
  "items": [
    {
      "name": "Pizza Margherita",
      "amount": 2,
      "unitPrice": 15.50
    },
    {
      "name": "Refresco",
      "amount": 3,
      "unitPrice": 2.25
    }
  ]
}
```

### Obtener pedido por ID


-**GET** `/orders/order/{orderId}`

-**Descripción**: Recupera la información de un pedido existente.

-**Respuesta**: Detalles completos del pedido.

```json
{"id":-395723782,
   "comments":"Nuevo pedido 6.0",
   "orderTotal":1000.0,
   "customer":2,
   "items":1301227327}
```

### Listar todos los pedidos

- **GET** `/orders/order`

- **Descripción**: Recupera todos los pedidos registrados.

- **Respuesta**: Lista de pedidos con sus detalles.
```json
[
    {
        "id": -1584528767,
        "comments": "Nuevo pedido, 3.0",
        "orderTotal": 5000.0,
        "customer": 1,
        "items": -1929477254
    },
    {
        "id": -1106470365,
        "comments": "Nuevo pedido 4.0",
        "orderTotal": 1000.0,
        "customer": 2,
        "items": -1066643627
    },
    {
        "id": 290269076,
        "comments": "Nuevo pedido 5.0",
        "orderTotal": 1000.0,
        "customer": 2,
        "items": -661914096
    },
    {
        "id": -395723782,
        "comments": "Nuevo pedido 6.0",
        "orderTotal": 1000.0,
        "customer": 2,
        "items": 1301227327
    },
    {
        "id": -1858853246,
        "comments": "Nuevo pedido, 3.0",
        "orderTotal": 5000.0,
        "customer": 1,
        "items": 866729760
    },
    {
        "id": 2087839959,
        "comments": "Nuevo pedido, 3.0",
        "orderTotal": 5000.0,
        "customer": 1,
        "items": 1231829855
    },
    {
        "id": -237717377,
        "comments": "Nuevo pedido, 3.0",
        "orderTotal": 5000.0,
        "customer": 1,
        "items": -1470425159
    },
    {
        "id": -1367133316,
        "comments": "Nuevo pedido 7.0",
        "orderTotal": 1000.0,
        "customer": -1371611483,
        "items": 357841762
    }
]
```

## Gestión de Clientes

### Crear cliente


- **POST** `/orders/customer`

- **Descripción**: Registra un nuevo cliente en el sistema.

- **Cuerpo** de la Solicitud: JSON con información del cliente.

- **Respuesta**: Información del cliente creado con ID asignado.

```json
{
  "name": "Juan Pérez",
  "email": "juan@example.com",
  "phone": "3001234567",
  "address": {
    "town": "Poblado",
    "city": "Medellín",
    "compositeAddress": "Calle 10 # 43-12",
    "addressDetails": "Edificio Plaza, Apto 502"
  }
}
```


###  Obtener cliente por ID


- **GET** `/orders/customer/{customerId}`

- **Descripción**: Recupera la información de un cliente existente.

- **Respuesta**: Datos completos del cliente.

`http://localhost:8080/orders/customer/-1086309686`

```json
{
    "id": -1086309686,
    "name": "Marlon Peñuela",
    "address": {
        "town": "colombia",
        "city": "Medellín",
        "compositeAddress": "street false 123",
        "addressDetails": "d"
    },
    "email": "marlon.penuela@email.com",
    "phone": "555-1234"
}
```

### Eliminar cliente


- **DELETE** /orders/customer/{customerId}

- **Descripción**: Elimina un cliente del sistema.


### Estado del servicio


- **GET** /orders/health

- **Descripción**: Verifica el estado del servicio.

- **Respuesta**: Mensaje confirmatório "service's ok".


## Configuración y Despliegue


### Prerrequisitos


- Java 21 o superior

- Maven 3.8 o superior

- Docker y Docker Compose (para despliegue en contenedores)


### Instalación y Ejecución Local

1. Clonar el repositorio:
```bash
git clone https://github.com/tuusuario/pedidos-service.git
cd pedidos-service
```
2. Compilar el proyecto:
```bash
mvn clean package

```
3. Ejecutar la aplicación:


### Despliegue con Docker

1. Crear archivo Dockerfile:
```bash
# Etapa de build
FROM maven:3.9-eclipse-temurin-21 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa final
FROM openjdk:21-slim
WORKDIR /app
COPY --from=build /app/target/pedidos-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```
2. Crear archivo docker-compose.yml:
```bash
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      # Usando la misma conexión a MongoDB Atlas que está en tu application.properties
      - SPRING_DATA_MONGODB_URI=mongodb+srv://marlondavid0526:AAEbJExRT0Q8inS4@aplicationitm.05edg.mongodb.net/restaurant
    networks:
      - restaurant-network

networks:
  restaurant-network:
    driver: bridge
```
3. Construir y levantar los contenedores:
```bash
docker-compose up   --Para montar la imagen 
docker-compose down -- para quitar la imagen
```

## Pruebas

El proyecto incluye pruebas unitarias completas para todas las capas:

- **Controllers:** Pruebas para verificar el correcto enrutamiento y respuesta HTTP.
- **Services:** Pruebas de la lógica de negocio y la orquestación entre componentes.
- **Repositories:** Pruebas de acceso a datos y operaciones CRUD.
- **DTOs y Value Objects:** Pruebas de serialización, deserialización y validaciones.
- **Entities:** Pruebas de comportamiento y relaciones entre entidades.

Para ejecutar las pruebas:

```bash
mvn test

```

## Estructura del Proyecto

```bash
pedidos-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── co/edu/itm/restaurant/orders/
│   │   │       ├── application/           # Lógica de aplicación
│   │   │       │   ├── controllers/       # Controladores REST
│   │   │       │   ├── dto/               # Objetos de transferencia de datos
│   │   │       │   └── services/          # Servicios de aplicación
│   │   │       ├── domain/                # Lógica de dominio
│   │   │       │   ├── entities/          # Entidades de dominio
│   │   │       │   ├── repositories/      # Interfaces de repositorios
│   │   │       │   ├── services/          # Servicios de dominio
│   │   │       │   └── value_objects/     # Objetos de valor
│   │   │       └── infraestructure/       # Infraestructura
│   │   │           └── persistence/       # Implementaciones de repositorios
│   │   └── resources/                     # Archivos de configuración
│   └── test/                              # Pruebas unitarias
└── pom.xml                                # Configuración de Maven
```


## Gestión de Múltiples Clientes
El sistema permite manejar información detallada de múltiples clientes, incluyendo sus direcciones de entrega, lo que facilita la creación de pedidos recurrentes sin necesidad de reingresar la información.

## Contribución
Para contribuir al proyecto:


1. Forkea el repositorio

2. Crea una rama para tu feature (git checkout -b feature/nueva-funcionalidad)

3. Realiza tus cambios y escribe pruebas unitarias

4. Haz commit de tus cambios (git commit -m 'Añade nueva funcionalidad')

5. Empuja tu rama (git push origin feature/nueva-funcionalidad)

6. Abre un Pull Request

## Licencia
Este proyecto está licenciado bajo [nombre de la licencia].

### Communication with Other Services

- The **pedido-Service** communicates with the **inventario-service** to validate ingredient availability.

- Events are published for other services to react to, such as `pedidoCreated`, `AvailabilityValidated`, and `InventoryUpdated`.