# 🛒 Venta Service

![Venta](https://source.unsplash.com/800x400/?shopping,cart,ecommerce)

Microservicio encargado de gestionar las ventas realizadas por los usuarios dentro del sistema PERFULANDIA.

## 🚀 Funcionalidades

- Registrar ventas
- Consultar historial de ventas
- Validar usuario (integración con Usuario Service)
- Integración con Orden y Pago

## 🧱 Arquitectura

- Controller
- Service
- Repository
- DTO / Mapper

## 🛠️ Tecnologías

- Java 17
- Spring Boot
- Spring Data JPA
- Oracle SQL
- Maven
- JWT

## 🔗 Endpoints principales

- `POST /ventas`
- `GET /ventas`
- `GET /ventas/{id}`
- `DELETE /ventas/{id}`

## 🧪 Testing

- JUnit 5
- Mockito