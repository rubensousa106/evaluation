# Teacher Evaluation (Backend)

The **Teacher Evaluation Backend** is a REST API built with **Spring Boot** to manage teacher and student evaluations.  
This backend provides all the data and business logic used by the **Evaluation Frontend**, located here:

🔗 **Frontend Repository:** https://github.com/rubensousa106/frontend

Together, both projects form a complete full-stack evaluation system.

---

## Features

- **Teacher Management:** Create, update, list, and delete teachers.
- **Student Management:** Manage students associated with evaluations.
- **Evaluations:** Create and manage teacher and student evaluations.
- **Questions & Answers:** Handle evaluation questions and submitted answers.
- **REST API:** Fully documented and structured for frontend consumption.
- **Persistence Layer:** JPA/Hibernate with a relational database.
- **Cross-Origin Support:** Configured to allow communication with the Angular frontend.

---

## Technologies Used

- **Java** (Spring Boot)
- **Spring Web** (REST API)
- **Spring Data JPA**
- **Maven**
- **Relational Database**  
  (PostgreSQL / MySQL / H2 depending on configuration)

---

## Requirements

- **JDK 11 or later** (recommended: 17+)
- **Maven**  
- A configured database in `application.properties` or `application.yml`.

---

## Installation and Setup

### 1. Clone the Repository

```sh
git clone https://github.com/rubensousa106/evaluation.git
cd evaluation
