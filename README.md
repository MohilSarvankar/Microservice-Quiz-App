
# Microservice Quiz App

A full-stack Quiz Management System built using **React** (frontend) and **Spring Boot** (backend). 
Admins can manage quizzes and questions, while users can attempt quizzes and view their scores.


## Features

- User can attempt quizzes and see scores  
- Admin can create and delete quizzes  
- Admin can manage questions (CRUD operations)  


## Tech Stack

**Client:** React, TailwindCSS, Axios (for API calls), React Router

**Server:** Spring Boot, Spring Data JPA (Hibernate), Spring Cloud Gateway (API gateway and load balancing), PostgreSql (Database)


## Installation and Setup

### Prerequisites

- Node.js (for frontend)
- Java 17+ (for backend)
- PostgreSQL (for database)

### Backend Setup
- Clone the repo:
   ```bash
   git clone https://github.com/MohilSarvankar/Microservice-Quiz-App.git

- Create databses named quizdb and questiondb in postgreSQL using pgAdmin.

- Open service-registry, question-service, quiz-service and gateway-service in eclipse and make sure all the mvn dependencies are installed.

- Configure PostgreSQL database in application.properties in quiz-service and question-service
    ```properties
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password

- Run service-registry, question-service, quiz-service and gateway-service.

### Frontend Setup
- Install node dependnecies.
    ```bash
    cd frontend
    npm install

- Run the react app
    ```bash
    npm start

- App wil run on localhost:3000
    