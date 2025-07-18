# AuthService

A dedicated microservice for **user authentication and authorization** in the CampusFlow platform.  
Built with **Java** and **Spring Boot**, it provides secure JWT-based authentication and role-based access control for multiple user roles including President, Vice President, Admins, Professors, and Students.

---

## 🚀 Features

- User registration and login with secure password handling
- JWT token generation and validation for stateless authentication
- Role-based access control (RBAC) supporting multiple user roles
- Token refresh and logout endpoints
- Password encryption using BCrypt
- Integration-ready with other CampusFlow microservices

---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Maven
- H2 (In-memory database for development/testing)
- PostgreSQL/MySQL (for production; configurable)
- Docker (optional)

---

## 📂 Project Structure

```
├── controller       # REST API endpoints (login, registration, token refresh)
├── service          # Business logic (user management, token handling)
├── model            # User, Role entities and JWT models
├── repository       # JPA repositories for User and Role persistence
├── config           # Security and JWT configuration classes
├── exception        # Custom exceptions and global error handlers
├── dto              # Data Transfer Objects for request/response payloads
├── util             # JWT utilities and helpers
└── application.properties / application.yml
```

---

## 🔧 Running Locally

1. Clone the repository:
```bash
git clone https://github.com/YourUsername/AuthService.git
cd AuthService
```

2. Configure your database in `application.properties` or `application.yml`.

3. Build and run:
```bash
./mvnw spring-boot:run
```

4. The service will be available at `http://localhost:8080`.

---

## 🧪 API Endpoints

| Endpoint           | Method | Description                    |
|--------------------|--------|-------------------------------|
| `/api/auth/register` | POST   | Register a new user           |
| `/api/auth/login`    | POST   | Authenticate and get JWT      |
| `/api/auth/refresh`  | POST   | Refresh JWT token             |
| `/api/auth/logout`   | POST   | Logout and invalidate token   |

---

## ⚙️ Configuration

- JWT secret and expiration times configurable via `application.properties`
- CORS and security settings customizable in security config classes

---

## 📄 License

MIT License

---

## 📞 Contact

Created by Mahitha Pasupuleti  
[GitHub](https://github.com/Mahitha-pasupuleti) | [LinkedIn](https://linkedin.com/in/mahithapasupuleti)
