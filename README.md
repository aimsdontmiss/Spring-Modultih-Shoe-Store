# ShlopApp

A modular e-commerce backend built with **Spring Boot**, designed using **Domain-Driven Design (DDD)** principles and a **Clean Architecture / Hexagonal Architecture** approach.

ShlopApp is a learning-focused commerce platform exploring how to build a scalable backend by separating business logic from infrastructure concerns.

---

## Overview

ShlopApp models an online shopping system with independent business modules (bounded contexts), including:

* Product catalog management
* Product variants
* Shopping cart management
* Identity and user management
* Order processing (planned)

The project follows a modular monolith approach, allowing each domain area to evolve independently while remaining within a single deployable application.

---

## Architecture

The application is organized around bounded contexts:

```
com.example.ShlopApp

├── Catalog
│   ├── Domain
│   ├── Application
│   ├── Infrastructure
│   └── Presentation
│
├── Cart
│   ├── Domain
│   ├── Application
│   ├── Infrastructure
│   └── Presentation
│
├── Identity
│   ├── Domain
│   ├── Application
│   ├── Infrastructure
│   └── Presentation
│
└── Ordering
    └── (planned)
```

Each module follows a layered design:

```
Presentation
      |
Application
      |
Domain
      |
Infrastructure
```

### Domain Layer

Contains business rules and core models:

* Aggregates
* Entities
* Value Objects
* Domain services
* Repository ports

The domain layer does not depend on frameworks or databases.

---

### Application Layer

Coordinates business operations through use cases.

Examples:

* Create cart
* Add item to cart
* Retrieve products
* Update cart contents

Application logic communicates with infrastructure through ports.

---

### Infrastructure Layer

Contains technical implementations:

* JPA entities
* Repository adapters
* Database mappings
* External integrations

Infrastructure depends on the domain, not the other way around.

---

## Current Features

### Catalog Module

Implemented:

* Product model
* Variant model
* Product retrieval
* Variant retrieval
* Persistence mapping

---

### Cart Module

Implemented:

* Cart aggregate
* Cart items
* Cart ownership model
* Cart repository port
* JPA persistence adapter
* Add item workflow
* Cart creation workflow

Current design supports:

* Guest shoppers
* Session-based ownership
* Future customer account linking

---

## Technology Stack

### Backend

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate ORM
* Spring Modulith
* Maven

### Database

* Oracle Database

### Architecture

* Domain-Driven Design (DDD)
* Clean Architecture
* Hexagonal Architecture
* Modular Monolith

---

## Design Goals

The main goals of this project are:

* Keep business logic independent from frameworks
* Model real commerce concepts using domain objects
* Practice aggregate design and domain boundaries
* Create maintainable modules that can evolve independently
* Explore enterprise-level Spring Boot architecture patterns

---

## Database Design Approach

The database schema for ShlopApp is designed and maintained manually rather than being generated automatically by Hibernate.

Tables, constraints, indexes, and relationships are created explicitly to maintain control over the persistence model and to better understand the relationship between domain concepts and database structures.

This approach allows the project to:

* Keep the database schema intentional and predictable
* Separate domain modeling decisions from persistence concerns
* Understand how aggregates map to relational structures
* Control primary keys, foreign keys, and constraints directly
* Avoid relying on automatic schema generation during development

The application uses JPA/Hibernate only as a persistence mapping layer. Domain models are not designed around database tables; instead, persistence entities act as adapters between the domain and relational database.

Example flow:

```
Domain Model
     |
     |  Mapper
     ↓
Persistence Entity
     |
     |  JPA/Hibernate
     ↓
Relational Database
```

Database objects such as tables, identifiers, and relationships are created intentionally to match the requirements of each bounded context.

Current database modeling includes:

* Catalog tables for products and variants
* Cart tables for carts and cart items
* Explicit primary key definitions
* Controlled relationships between entities
* Manually defined constraints for data integrity

---

## Running Locally

### Requirements

* Java 21+
* Maven
* Oracle Database (or compatible database configuration)

---

### Clone Repository

```bash
git clone https://github.com/yourusername/ShlopApp.git

cd ShlopApp
```

---

### Configure Database

Update:

```
src/main/resources/application.properties
```

with your local database configuration.

Example:

```properties
spring.datasource.url=your_database_url
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

### Run Application

Using Maven:

```bash
./mvnw spring-boot:run
```

or:

```bash
mvn spring-boot:run
```

---

## Future Roadmap

Planned improvements:

* Authentication and authorization
* Session-based guest carts
* Cart expiration
* Customer accounts
* Order management
* Payment integration
* Inventory management
* Event-driven communication between modules
* Automated testing

---

## Project Status

🚧 Active development

ShlopApp is continuously evolving as new domain concepts and architectural improvements are introduced.
