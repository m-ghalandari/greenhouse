# API Gateway Service

This service is the **central entry point** for the microservices ecosystem, built with **Spring Cloud Gateway**.  
It is responsible for:
- Routing incoming client requests to the appropriate downstream service.
- Securing endpoints by validating **JSON Web Tokens (JWTs)**.

---

## Configuration

The gateway requires the URL of the authentication service to be set as an **environment variable** before running.  
This is used by the `JwtValidation` filter.

**Required Environment Variable:**
- `AUTH_SERVICE_URL` — The base URL for the auth-service's validation endpoint.  



---

## How to Run

The API Gateway will start and listen on **port 4004**.


## Routing and Port Forwarding

The gateway forwards requests to downstream services based on the **path prefix**.
The default configuration is for **container-to-container communication** using service names.

| Path Prefix       | Target Service | Target Port | Authentication | Notes                                                        |
|-------------------|----------------|-------------|----------------|--------------------------------------------------------------|
| `/auth/**`        | auth-service   | 4005        | None           | Forwards to the auth service for login, registration, etc.   |
| `/api/users/**`   | user-service   | 4000        | JWT Required   | Forwards to the user service. Requires a valid Bearer token. |
| `/api-docs/users` | user-service   | 4000        | None           | Aggregates the OpenAPI specification from the user service.  |
| `/api-docs/auth`  | auth-service   | 4005        | None           | Aggregates the OpenAPI specification from the auth service.  |



