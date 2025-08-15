# Billing Service

The **Billing Service** is a microservice within the **SmartGrow** ecosystem responsible for handling all billing-related operations.  
It is built using **Spring Boot** and communicates **exclusively via gRPC**, providing a high-performance, strongly-typed API for other internal services.

**Primary Responsibility:** Manage billing accounts for users.

---

## Core Features
- **gRPC First**: Exposes a gRPC API for creating and managing billing accounts (no REST interface).
- **Strongly-Typed API**: Service contract defined using Protocol Buffers (`.proto`), ensuring type safety.
- **Spring Boot Integration**: Uses `net.devh:grpc-spring-boot-starter` for seamless gRPC integration.
- **Containerized**: Multi-stage Dockerfile for optimized production image.

---


## Building the Service

The build process compiles the `.proto` file into Java source code automatically.

```bash
# Compile and package
./mvnw clean package
````

This will:

* Compile Java and generated sources
* Run tests
* Create an executable JAR in the `target/` directory

---

## Configuration

Configuration is stored in `src/main/resources/application.properties`.

| Property           | Description                                         | Default |
|--------------------|-----------------------------------------------------|---------|
| `server.port`      | Port for embedded HTTP server (health checks, etc.) | 4001    |
| `grpc.server.port` | Main gRPC server port for client connections        | 9001    |

---

## gRPC API Definition

Defined in: `src/main/proto/billing_service.proto`

```proto
syntax = "proto3";

option java_multiple_files = true;
option java_package = "billing";

// The service responsible for billing operations.
service BillingService {
  // Creates a new billing account for a given user.
  rpc CreateBillingAccount (BillingRequest) returns (BillingResponse);
}

message BillingRequest {
  string userId = 1;
  string name = 2;
  string email = 3;
}

message BillingResponse {
  string accountId = 1;
  string status = 2;
}
```

---

## Current Implementation

**Class:** `BillingGrpcService.java`

* **CreateBillingAccount**: Currently returns a dummy response.
* **Next step**: Implement actual business logic for creating and persisting billing accounts.


