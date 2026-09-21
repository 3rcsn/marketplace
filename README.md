# Marketplace

Marketplace is a Java/Spring-based modular monolith application for managing event catalog data, event metadata, customer registration, and ticketing.

The project demonstrates how to organize a backend application into business-oriented modules while integrating multiple persistence technologies and modern Java features.

## Features

- **Catalog Management**: Handles event listings, sectors, and seats with MySQL.
- **Event Metadata**: Stores rich event information in MongoDB.
- **Customer Registration**: Manages customer profiles using a dedicated MySQL database.
- **Ticketing**: Handles event and customer data for ticketing operations using PostgreSQL.
- **Multi-Database Support**: Integrates MySQL, PostgreSQL, and MongoDB within a single application.
- **Asynchronous Enrichment**: Enriches event data with metadata asynchronously.
- **Redis Caching**: Optimized performance for the showcase using Redis.
- **Virtual Threads**: High-concurrency support using Java's virtual threads.
- **RESTful APIs**: Exposes endpoints for browsing the showcase and managing customers via Spring Data REST.

## Technologies Used

- **Java 25** (with Virtual Threads enabled)
- **Spring Boot 4.1.1**
- **Spring Data JPA** (MySQL & PostgreSQL)
- **Spring Data MongoDB**
- **Spring Data Redis** (Jedis client)
- **Spring Data REST** & **HAL Explorer**
- **Spring Boot Docker Compose**
- **Lombok**
- **Gradle**
- **Databases**: MySQL 9.6, PostgreSQL 18.3, MongoDB 8.2
- **Cache**: Redis 8.6

## Architecture

The project follows a modular monolith pattern with three main modules:

- **Catalog Module**: 
    - **Domain**: Events, Sectors, Seats, EventMetadata.
    - **Persistence**: MySQL for core event data, MongoDB for rich metadata.
    - **Performance**: Redis caching for the `/showcase` endpoint.
    - **API**: `/showcase` for browsing enriched events.
- **Registration Module**:
    - **Domain**: Customers, Addresses.
    - **Persistence**: MySQL.
    - **API**: Automated REST API via Spring Data REST at `/customers`.
- **Ticketing Module**:
    - **Domain**: Events, Customers, Sectors, Seats.
    - **Persistence**: PostgreSQL.

## Prerequisites

- **JDK 25**
- **Docker and Docker Compose**

## Getting Started

1. **Clone the repository**
2. **Start the environment**
   The project uses `spring-boot-docker-compose` to manage database and cache containers automatically when the application starts. 
   
   To start them manually:
   ```bash
   docker-compose up -d
   ```
3. **Run the application**
   ```bash
   ./gradlew bootRun
   ```

The application will be available at `http://localhost:8080`.

## API Endpoints

- **Browse Showcase**: `GET /showcase` - Returns a list of enriched events (Cached via Redis).
- **Customer Management**: `GET /customers` - Spring Data REST endpoint for registration profiles.
- **Actuator Health**: `GET /actuator/health` - Check application health and data source status.
- **HAL Explorer**: `GET /browser/index.html` - Interactive browser for the REST API.

## Project Structure

- `src/main/java/org/ercsn/marketplace/catalog`: Catalog module logic (MySQL + MongoDB + Redis).
- `src/main/java/org/ercsn/marketplace/registration`: Registration module logic (MySQL).
- `src/main/java/org/ercsn/marketplace/ticketing`: Ticketing module logic (PostgreSQL).
- `src/main/resources/application.properties`: Configuration for multiple data sources, MongoDB, Redis, and Virtual Threads.
- `compose.yml`: Docker Compose configuration for MySQL, PostgreSQL, MongoDB, and Redis.
