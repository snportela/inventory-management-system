[JAVA__BADGE]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING__BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[MAVEN__BADGE]: https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white
[POSTGRES__BADGE]: https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white


# Inventory Management System API 🧾

Spring Boot REST API for an inventory management system.

## 💻Technologies
![JAVA__BADGE] ![SPRING__BADGE] ![MAVEN__BADGE] ![POSTGRES__BADGE]

## Requirements

- PostgreSQL 17
- JDK 23

## ⚙️ Features

- Allows the management of:
  - Products
  - Suppliers
  - Customers
  - Orders
  - Transfers
  - Users

- User authentication with Spring Security
  - Password encryption with Bcrypt
  - Role-Based Access Control
  - JWT authorization

- Password reset through generated token sent in email

- Pagination and sorting

- Soft delete with Spring JPA

- MVC (Model-View-Controller) pattern
    

##  🚀Getting Started

-  Clone the repository
```
git clone https://github.com/snportela/inventory-management-system
```
- Install Maven dependencies

- Create a PostgreSQL database

- Configure environment variables needed at the `application.properties` file

- Go to the `InventorySystemApplication` class and run the project



