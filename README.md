# springboot-jwt-auth-service
Secure authentication and authorization service built with Spring Boot, Spring Security, JWT, and MySQL. Supports login, token generation, and protected API endpoints.


A secure authentication and authorization backend built with Spring Boot, Spring Security, and JWT (JSON Web Tokens).

This project demonstrates how modern backend systems handle user authentication, token-based security, and protected API access.

User login with email & password

JWT token generation

Secure password hashing (Spring Security)

Token validation for protected endpoints

Stateless authentication (no sessions)

MySQL database integration with JPA/Hibernate

🛠 Tech Stack
Layer	Technology
Backend	Spring Boot
Security	Spring Security + JWT
Database	MySQL
ORM	JPA / Hibernate
Build Tool	Maven
Java Version	Java 21

Controller → Handles authentication requests

Service → Business logic & token generation

Repository → Database operations

JWT Filter → Intercepts requests and validates tokens

🔑 Authentication Flow

User sends login request

Server validates credentials

JWT token is generated and returned

Client sends token in Authorization header

Server validates token for protected routes
