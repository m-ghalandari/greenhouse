# Authentication Service (auth-service)

The **Authentication Service** is a core component of the **SmartGrow** microservices ecosystem.  
It is a standalone **Spring Boot** application responsible for:
- **User authentication**
- **JSON Web Token (JWT) generation and validation**

Its primary functions:
1. Handle user logins and validate credentials.
2. Generate secure, signed JWTs for authenticated users.
3. Provide an endpoint for other microservices (e.g., api-gateway) to validate JWTs.

---

## Core Features
- **User Authentication**: Validates credentials (email & password) against the database.
- **JWT Generation**: Creates signed JWTs with user roles as claims.
- **JWT Validation**: `/validate` endpoint for verifying authenticity and integrity of JWTs.
- **Database Integration**: PostgreSQL via Spring Data JPA; H2 in-memory DB for development/testing.
- **API Documentation**: OpenAPI 3.0 spec at `/v3/api-docs` and Swagger UI at `/swagger-ui.html`.
- **Containerized**: Multi-stage Dockerfile for lightweight production builds.

---

## Configuration

The most critical configuration is the **JWT secret key**, provided as an **environment variable**.

### Environment Variables
- `JWT_SECRET` — Base64-encoded secret key for signing and validating JWTs (must be strong & confidential).


---

## How to Run

### 1. Running Locally (Maven)

For development & testing with H2 or local PostgreSQL:

```bash
# Set secret
export JWT_SECRET="your-super-secret-base64-encoded-key"

# Start application
./mvnw spring-boot:run
```

* Service: [http://localhost:4005](http://localhost:4005)
* Swagger UI: [http://localhost:4005/swagger-ui.html](http://localhost:4005/swagger-ui.html)

---

## API Endpoints

| Method | Path               | Description                                                  | Request Body      | Authentication |
|--------|--------------------|--------------------------------------------------------------|-------------------|----------------|
| POST   | `/login`           | Authenticates user; returns JWT if successful.               | `LoginRequestDTO` | None           |
| GET    | `/validate`        | Validates a JWT from `Authorization: Bearer <token>` header. | None              | Bearer Token   |
| GET    | `/swagger-ui.html` | Interactive API documentation & testing.                     | None              | None           |
| GET    | `/v3/api-docs`     | Raw OpenAPI 3.0 spec in JSON (used by API Gateway).          | None              | None           |

---

## Initial Data (Development Only)

A default admin user is inserted via `src/main/resources/data.sql` if absent:

* **Email:** `testuser@test.com`
* **Password:** `password`
* **Role:** `ADMIN`


