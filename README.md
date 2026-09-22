# Marketplace

Marketplace is a Java/Spring-based modular monolith application designed to manage event catalogs, event metadata, customer registrations, and ticketing operations. It serves as a comprehensive demonstration of organizing a backend application into business-oriented modules while integrating multiple persistence technologies and modern Java features.

## Features

- **Catalog Management**: Efficiently handles event listings, sectors, and seats using MySQL for core data.
- **Rich Event Metadata**: Utilizes MongoDB to store flexible and detailed event information.
- **Customer Registration**: Manages user profiles and addresses through a dedicated MySQL instance.
- **Ticketing System**: Handles high-concurrency seat selection and reservations using PostgreSQL and Redis for distributed locking.
- **Multi-Database Integration**: Demonstrates simultaneous integration of MySQL (multiple instances), PostgreSQL, and MongoDB.
- **Distributed Caching & Locking**: Leverages Redis for optimizing read-heavy endpoints and managing concurrent resource access.
- **Asynchronous Processing**: Implements asynchronous enrichment of event data.
- **Virtual Threads**: Optimized for high-concurrency performance using Java's virtual threads.
- **RESTful Ecosystem**: Provides clean APIs for the showcase and automated management via Spring Data REST.

## Technologies Used

- **Java 25** (Virtual Threads enabled)
- **Spring Boot 4.1.1**
- **Spring Data JPA** (MySQL & PostgreSQL)
- **Spring Data MongoDB**
- **Spring Data Redis** (Jedis client)
- **Spring Data REST** & **HAL Explorer**
- **Spring Boot Docker Compose**
- **Lombok**
- **Gradle**
- **Infrastructure**:
    - **MySQL 9.6**: Two separate instances for Catalog and Registration.
    - **PostgreSQL 18.3**: Used for Ticketing transactions.
    - **MongoDB 8.2**: Stores event metadata.
    - **Redis 8.6**: Two instances (Catalog caching and Ticketing locking).

## Architecture

The application follows a **Modular Monolith** pattern, ensuring clear separation of concerns between business domains:

### 1. Catalog Module
- **Responsibility**: Event discovery and showcase.
- **Domain**: Events, Sectors, Seats, EventMetadata.
- **Persistence**: MySQL (core data) + MongoDB (metadata).
- **Optimization**: Redis caching for the showcase API.
- **API**: `/showcase` for enriched event listings.

### 2. Registration Module
- **Responsibility**: Customer onboarding and profile management.
- **Domain**: Customers, Addresses.
- **Persistence**: MySQL.
- **API**: `/customers` (Automated via Spring Data REST).

### 3. Ticketing Module
- **Responsibility**: Seat selection and reservation logic.
- **Domain**: Events, Customers, Sectors, Seats.
- **Persistence**: PostgreSQL.
- **Locking**: Redis-based distributed locks for seat selection.
- **API**: `/ticketing/events/{eventId}/seats/select`.

## Prerequisites

- **JDK 25**
- **Docker and Docker Compose**

## Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/yourusername/marketplace.git
cd marketplace
```

### 2. Start the Environment
The project uses `spring-boot-docker-compose`. Database and cache containers will start automatically when you run the application.

If you prefer to start them manually:
```bash
docker-compose up -d
```

### 3. Run the Application
```bash
./gradlew bootRun
```
The application will be available at `http://localhost:8080`.

## API Reference

| Endpoint | Method | Description |
| :--- | :--- | :--- |
| `/showcase` | `GET` | Browse enriched events (Cached). |
| `/customers` | `GET` | Customer registration profiles (HAL/JSON). |
| `/ticketing/events/{id}/seats/select` | `POST` | Select a seat for a customer. |
| `/actuator/health` | `GET` | Monitor application health. |
| `/browser/index.html` | `GET` | Interactive HAL Explorer. |

## Project Structure

- `src/main/java/org/ercsn/marketplace/catalog`: Event catalog logic (MySQL, Mongo, Redis).
- `src/main/java/org/ercsn/marketplace/registration`: Customer registration logic (MySQL).
- `src/main/java/org/ercsn/marketplace/ticketing`: Ticketing and reservation logic (PostgreSQL, Redis).
- `src/main/resources/application.properties`: Centralized configuration for multiple data sources and virtual threads.
- `compose.yml`: Infrastructure orchestration (6 containers).
