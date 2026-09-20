# Marketplace

Marketplace is a Java/Spring-based modular monolith application for managing event catalog data, event metadata, and customer registration.

The project demonstrates how to organize a backend application into business-oriented modules while integrating multiple persistence technologies, including relational databases and MongoDB.

## Features

- **Catalog Management**: Handles event listings, sectors, and seats.
- **Registration**: Manages customer information.
- **Multi-Database Support**: Uses separate MySQL databases for Catalog and Registration, plus MongoDB for event metadata.
- **Event Enrichment**: Asynchronous enrichment of event data with metadata.
- **RESTful APIs**: Exposes endpoints for browsing the showcase and managing customers.

## Technologies Used

- **Java 25**
- **Spring Boot 4.1.1**
- **Spring Data JPA** (MySQL)
- **Spring Data MongoDB**
- **Spring Data REST**
- **Spring Boot Docker Compose**
- **Lombok**
- **Gradle**
- **MySQL 9.6**
- **MongoDB 8.2**

## Architecture

The project follows a modular monolith pattern:

- **Catalog Module**: 
    - Domain: Events, Sectors, Seats, EventMetadata.
    - Persistence: MySQL for core event data, MongoDB for metadata.
    - API: `/showcase` for browsing events.
- **Registration Module**:
    - Domain: Customers, Addresses.
    - Persistence: MySQL.
    - API: Automated REST API via Spring Data REST at `/customers`.

## Prerequisites

- JDK 25
- Docker and Docker Compose

## Getting Started

1. **Clone the repository**
2. **Start the databases**
   The project uses `spring-boot-docker-compose` to manage database containers automatically. You can also start them manually:
   ```bash
   docker-compose up -d
   ```
3. **Run the application**
   ```bash
   ./gradlew bootRun
   ```

The application will be available at `http://localhost:8080`.

## API Endpoints

- **Browse Showcase**: `GET /showcase` - Returns a list of enriched events.
- **Customer Management**: `GET /customers` - Spring Data REST endpoint for customer resources.
- **Actuator Health**: `GET /actuator/health` - Check application health.
- **HAL Explorer**: `GET /browser/index.html` - Explore the REST API.

## Project Structure

- `src/main/java/org/ercsn/marketplace/catalog`: Catalog module logic.
- `src/main/java/org/ercsn/marketplace/registration`: Registration module logic.
- `src/main/resources/application.properties`: Configuration for multiple data sources and MongoDB.
- `compose.yml`: Docker Compose configuration for MySQL and MongoDB.
