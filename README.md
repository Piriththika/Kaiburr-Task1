**Kaiburr Task 1 - Spring Boot MongoDB Task API**

This project is developed as part of Kaiburr Assessment Assignment – Task 1.

The objective of the task is to build a Spring Boot REST API integrated with MongoDB that allows you to:
1. Create tasks
2. Read tasks
3. Update tasks
4. Delete tasks
5. Search tasks 
6. Execute tasks and
7. store their output

This solution demonstrates how to use Java, Spring Boot, and MongoDB to design and implement a complete backend system for task management.


**Technologies Used**

1. Java 17
2. Spring Boot 3.5.6
3. MongoDB 8.2.0
4. Apache Maven 3.9
5. IntelliJ IDEA (can also use Eclipse/NetBeans)
6. Git & GitHub


**Prerequisites**

Before running the project, ensure you have the following installed:
1. Java JDK 17 or 20+
2. Apache Maven 3.9+
3. MongoDB (running on default port 27017)
4. Git


**Setup and Installation**

1. Clone this repository:
    git clone https://github.com/Piriththika/Kaiburr-Task1.git
    cd Kaiburr-Task1

2. Start MongoDB server:
    mongod --dbpath C:\data\db
    Ensure that the folder C:\data\db exists on your system. If it does not exist, locate where MongoDB is installed and set the path correctly.

3. Build and run the Spring Boot application:
    mvn clean install
    mvn spring-boot:run

The application will start on http://localhost:8080


**API Endpoints**

| Method | Endpoint                  | Description          | Example Request Body                                                                                       |
| ------ | ------------------------- | -------------------- | ---------------------------------------------------------------------------------------------------------- |
| POST   | `/api/tasks`              | Create a new task    | `{ "name": "Sample Task", "command": "echo Hello", "owner": "Piriththika", "description": "Hello" }`       |
| GET    | `/api/tasks`              | Get all tasks        | —                                                                                                          |
| GET    | `/api/tasks/{id}`         | Get task by ID       | —                                                                                                          |
| PUT    | `/api/tasks/{id}`         | Update a task        | `{ "name": "Updated Task", "command": "echo Updated", "owner": "Piriththika", "description": "Hello Hi" }` |
| DELETE | `/api/tasks/{id}`         | Delete a task        | —                                                                                                          |
| GET    | `/api/tasks/search?name=` | Search tasks by name | —                                                                                                          |
| PUT    | `/api/tasks/{id}/execute` | Execute task command | —                                                                                                          |


**Example Usage**

1. Create a Task

      POST /api/tasks
      
        {
          "name": "Print Hello",
          "command": "echo Hello World!",
          "owner": "Piriththika",
          "description": "Test"
        }
      
      
      Response:
      
        {
          "id": "68d16b1aac6e1fec50d0331e",
          "name": "Print Hello",
          "owner": "Piriththika",
          "command": "echo Hello World!",
          "description": "Test",
          "taskExecutions": []
        }

2. Execute a Task

    PUT /api/tasks/68d16b1aac6e1fec50d0331e/execute
    Response:

        {
          "id": "68d16b1aac6e1fec50d0331e",
          "name": "Print Hello",
          "owner": "Piriththika",
          "command": "echo Hello World!",
          "description": "Test",
          "taskExecutions": [
            {
              "startTime": "2025-09-22T20:59:52.8396841",
              "endTime": "2025-09-22T20:59:52.9104234",
              "output": "Hello World!",
              "error": null
            }
          ]
        }


**How It Works**

1. A task is stored in MongoDB with fields: id, name, owner, command, description.
2. When a task is executed, the backend runs the shell command (dir, echo, etc.) and saves the execution history.
3. Each execution stores: startTime, endTime, output, and error if any.
4. The history is appended to the task and saved back in MongoDB.

**Summary**

This project implements a Task Management API using Spring Boot and MongoDB.
Developers can create, view, update, search, and delete tasks.
Commands can be executed directly from the API, and outputs are logged.
All execution history is stored in MongoDB for tracking.

In simpler terms, it is like a digital notebook of commands where you can save a command (like “list files”), run it from the API, and always see what happened last time.
This project proves the ability to integrate Java, databases, and REST APIs in a real-world scenario.
