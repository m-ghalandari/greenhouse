# Greenhouse Microservices

![Architecture Diagram](docs/architecture-diagram.png)

## Overview

This repository contains a set of Spring Boot microservices for experimenting with a greenhouse-themed system.  Services communicate over REST, gRPC and Kafka and can be run locally via Docker Compose.

## Services

### API Gateway
Spring Cloud Gateway that exposes a single entry point on port **4004** and routes requests to backend services.

### Auth Service
Provides JWT based authentication with `/login` and `/validate` endpoints, running on port **4005** inside the network.

### User Service
Exposes REST endpoints for basic user CRUD on port **4000**.  When a user is created it publishes a `USER_CREATED` event to Kafka and invokes the Billing service via gRPC to create a billing account.

### Billing Service
A gRPC server listening on **9001** that accepts `CreateBillingAccount` requests and currently returns a stubbed account response.

### Analytics Service
Placeholder service configured as a Kafka consumer; intended to process user events in future iterations.

## Local Development

### Requirements
- Docker & Docker Compose
- JDK 17 (for building services)

### Run everything
```bash
docker-compose up --build
```

The stack includes Postgres databases, Kafka, and all microservices.  The API gateway will be available at http://localhost:4004.

### Example requests
- HTTP examples reside in `api-request/`
- gRPC examples are under `grpc-request/`

## Testing
Run tests for individual modules using Maven:

```bash
cd user-service && ./mvnw test
cd ../billing-service && ./mvnw test
cd ../integration-tests && ./mvnw test   # requires services running on port 4004
```

## Repository layout
- `user-service/` – User CRUD, Kafka producer, gRPC client
- `auth-service/` – JWT authentication
- `billing-service/` – gRPC billing service
- `analytics-service/` – Kafka consumer (stub)
- `api-gateway/` – Spring Cloud Gateway configuration
- `docs/` – diagrams and documentation
- `integration-tests/` – RestAssured tests verifying auth and user flows
- `docker-compose.yml` – local development stack
- `api-request/`, `grpc-request/` – sample requests