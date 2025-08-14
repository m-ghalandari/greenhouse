# User Service

This service is responsible for managing user data within the **Greenhouse** ecosystem.  
It provides a **RESTful API** for user creation, retrieval, updating, and deletion (CRUD).

Upon user creation, it performs two key integrations:  

1. **Synchronous gRPC call**: Communicates with the billing-service to create a corresponding billing account for the new user.  
2. **Asynchronous Kafka event**: Publishes a `USER_CREATED` event to a Kafka topic, allowing other microservices to react to the new user registration.

---

## Features
- REST API for User CRUD operations.
- Validation for incoming user data.
- Integration with PostgreSQL using Spring Data JPA.
- gRPC client for synchronous communication with the Billing Service.
- Kafka producer for publishing user-related events.
- API documentation with Swagger/OpenAPI.
- Containerized with a multi-stage Dockerfile.

---

## API Endpoints

Swagger UI: [http://localhost:4000/swagger-ui.html](http://localhost:4000/swagger-ui.html)

| Method | Endpoint      | Description       |
|--------|---------------|-------------------|
| POST   | `/users`      | Create a new user |
| GET    | `/users`      | Get all users     |
| PUT    | `/users/{id}` | Update a user     |
| DELETE | `/users/{id}` | Delete a user     |

---

## Integrations

### Kafka Events

* **Topic:** `user`
* **Event Schema:** `UserEvent` (in `src/main/proto/user_event.proto`)
* **Trigger:** `USER_CREATED` event is sent when a user is created.

### gRPC Communication

* **Service:** `BillingService` (in `src/main/proto/billing_service.proto`)
* **Method:** `CreateBillingAccount`
* **Trigger:** Called immediately after a new user is saved to the database.



