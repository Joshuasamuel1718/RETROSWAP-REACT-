# RetroSwap Backend

## About
Second-hand marketplace backend built with Spring Boot.

## Technologies
- Java
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL/H2
- Maven

## Features
- User registration & authentication
- JWT-based security
- Product CRUD
- Cart management
- Order management
- Category management
## API Endpoints

### Authentication
- `POST /retroswap/auth/register`
- `POST /retroswap/auth/login`

### Products
- `GET /retroswap/products`
- `GET /retroswap/products/{id}`
- `POST /retroswap/products`
- `PUT /retroswap/products/{id}`
- `DELETE /retroswap/products/{id}`

### Cart
- `GET /retroswap/cart`
- `POST /retroswap/cart`
- `PUT /retroswap/cart/{cartItem}`
- `DELETE /retroswap/cart/{id}`

### Orders
- `GET /retroswap/orders`
- `POST /retroswap/orders`

### Categories
- `GET /retroswap/categories`
- ...
