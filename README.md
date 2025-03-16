# 🚀 Financial Microservices with Spring Boot and RabbitMQ

This project implements a **microservices architecture** for managing customers, accounts, and bank transactions. The services communicate **asynchronously** using **RabbitMQ** and persist data in **MySQL**.

## 📌 Technologies Used
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA** (MySQL)
- **Flyway** (Database Migration)
- **RabbitMQ** (Asynchronous Messaging)
- **Docker & Docker Compose**

---

## 📂 Project Structure

```
financial-services/
├── customer-service/    # Customer Management Microservice
├── account-service/     # Account Management Microservice
├── docker-compose.yml   # Docker Compose for orchestrating services
├── init-db.sql          # Database initialization script
└── postman.json         # Postman collection for testing APIs
```

---

## 🚀 Getting Started

### Prerequisites
Ensure you have the following installed:
- **Docker & Docker Compose**

### Running the Project

1. Clone the repository:
   ```sh
   git clone https://github.com/jonant7/springboot-microservices.git
   cd springboot-microservices
   ```

2. Start all services using Docker Compose:
   ```sh
   docker-compose up -d
   ```

3. Verify the services:
    - **RabbitMQ Management UI**: [http://localhost:15672](http://localhost:15672) (User: `guest`, Password: `guest`)
    - **Customer Service**: [http://localhost:8080](http://localhost:8080)
    - **Account Service**: [http://localhost:8082](http://localhost:8082)

4. (Optional) Check running containers:
   ```sh
   docker ps
   ```

---

## 🛠️ Configuration Details

The project is orchestrated using **Docker Compose**, which automatically sets up:
- **RabbitMQ** for asynchronous messaging
- **MySQL** as the database, initialized with `init-db.sql`
- **Customer Service** and **Account Service**, running on ports `8080` and `8082`

Flyway handles database migrations to ensure consistency.

---

## 🧪 API Testing
A **Postman collection** is included (`microservices.postman_collection.json`) to facilitate API testing. Import it into Postman and execute requests.

---

## 🔄 Stopping the Services
To stop and remove all containers:
```sh
docker-compose down
```

To remove volumes and reset the database:
```sh
docker-compose down -v
```

---

## 📜 License
[MIT License](LICENSE)

---

## 👥 Authors
- **Jonathan Navarro** - [GitHub](https://github.com/jonant7)