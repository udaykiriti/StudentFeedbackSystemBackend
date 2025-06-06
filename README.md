# Student Feedback System Backend

This repository contains the backend implementation for the Student Feedback System. The backend is developed using Java and provides APIs for managing student feedback functionalities.


## Overview

The Student Feedback System is designed to facilitate seamless feedback collection and management from students. This backend service provides robust and scalable RESTful APIs to handle all feedback-related operations.

## Features

- **User Authentication and Authorization:** Secure login and registration for users.
- **CRUD Operations:** Manage student feedback with Create, Read, Update, and Delete operations.
- **Feedback Analytics and Reporting:** Generate insightful reports and analytics from the feedback data.
- **Integration:** Easy integration with the frontend through RESTful APIs.

## Technologies Used

- **Java:** The primary programming language used for backend development.
- **Spring Boot:** Framework for building production-ready applications with ease.
- **Hibernate:** ORM framework for database operations.
- **MySQL/PostgreSQL:** Relational databases for persistent storage.
- **Swagger:** For API documentation.
- **Maven:** Build automation tool used for project management.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java:** JDK 11 or higher
- **Maven:** Version 3.6.0 or higher
- **Database:** MySQL or PostgreSQL

## Installation

To install and set up the project locally, follow these steps:

1. **Clone the repository:**
    ```sh
    git clone https://github.com/Prasanth631/StudentFeedbackSystemBackend.git
    ```

2. **Navigate to the project directory:**
    ```sh
    cd StudentFeedbackSystemBackend
    ```

3. **Configure the database connection:**
   Update the `src/main/resources/application.properties` file with your database configuration.

4. **Build the project using Maven:**
    ```sh
    mvn clean install
    ```

## Usage

To run the application locally, execute the following command:

```sh
mvn spring-boot:run
```

The backend server will start on `http://localhost:8080`.

## API Documentation

The API documentation is available at `http://localhost:8080/swagger-ui.html` once the server is up and running.

"# StudentFeedbackSystemBackend" 
