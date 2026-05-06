# Microservices Template

Template for Spring Boot microservices applications. Everything runs in Docker, so each service is an isolated container that can be started and managed independently.

---

## Services

| Service | Role |
|---|---|
| Zipkin | Distributed tracing between microservices |
| MySQL | Database container |
| Eureka | Service registry — lists all running microservices |
| API Gateway | Load balancer and entry point |
| Auth | Handles authentication and user registration |
| Core | Main business logic, talks to the database |
| Clients (shared lib) | Feign clients and shared utilities across services |

---

## Getting started

```bash
# Optional: build Docker images first
mvn clean package

# Start everything
docker-compose up
```
