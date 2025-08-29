# 🚀 DevTracker - Backend API with CI/CD

DevTracker is a simple developer task and health tracking REST API built with Spring Boot and integrated with a  CI/CD pipeline.

This project is designed to help you learn backend development and implement a complete CI/CD workflow using Jenkins/Github-Actions — from code commit to Docker image build and deployment.

---

## 📌 Features

- 🔍 Health check endpoint
- 📋 CRUD operations for tasks
- 📊 (Optional) Developer productivity metrics
- 🧪 Unit testing integrated
- 🐳 Dockerized for portability
- 🔁 CI/CD pipeline for:
  - Code checkout
  - Dependency install
  - Testing
  - Docker image build
  - Push to Docker Hub

---

## 🛠️ Tech Stack

| Layer       | Technology             |
|-------------|------------------------|
| Backend     | Spring Boot            |
| Database    | MongoDB                |
| Testing     | Junit                  |
| CI/CD       | Jenkins/Github Actions |
| Container   | Docker                 |
| VCS         | Git + GitHub           |

---

## 📂 Project Structure

```bash
devtracker/
├── src/
│   ├── main/
│   │   ├── java/                       # Backend source code
│   │   └── resources/
│   │       └── application.yaml  # Spring Boot configuration
│   └── test/                           # Test source code
├── .github/
│   └── workflows/                      # GitHub Actions workflows
├── Dockerfile                          # Docker image setup
├── Jenkinsfile                         # Jenkins pipeline configuration
├── pom.xml                             # Maven build file
└── README.md                           # Project documentation
```

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/RahulMikaelson/DevTracker.git
cd DevTracker
```
### 2. Start Application

```bash
./mvnw spring-boot:run
```
### 3.Run Test

```bash
./mvnw test
```

## 🐳 Docker Build & Run
```bash
docker build -t devtracker-backend .
docker run -p 8080:8080 devtracker-backend
```
## 🔄  CI/CD Pipeline Goals
The primary purpose of this project is to create and explore a complete CI/CD pipeline with the following stages:

1. **Build:** Compile the Java code and package it into a JAR file.
2. **Test:** Run automated unit and integration tests.
3. **Code Quality Check:** Analyze the code for bugs, vulnerabilities, and code smells.
4. **Containerization:** Build a Docker image of the application.
5. **Deployment:** Deploy the Docker image to a target environment (local, cloud, or Kubernetes).

---

## Typical CI/CD Workflow

Regardless of the tool used (Jenkins, GitHub Actions, GitLab CI/CD, etc.), a basic pipeline generally follows these steps:

1. Connect your CI/CD platform to the project repository.
2. Configure the pipeline definition (e.g., `Jenkinsfile`, `.github/workflows`).
3. On every code push or pull request:
  - ✅ Code is checked out from the repository
  - 🔧 Dependencies are installed
  - 🧪 Tests are executed
  - 🐳 Docker image is built
  - 🚢 Image is pushed and/or deployed
---

## API Endpoints

| Method | Endpoint                 | Description                     |
| ------ |--------------------------|---------------------------------|
| GET    | `/health`                | Health check                    |
| GET    | `/api/v1/tasks`          | List all tasks                  |
| POST   | `/api/v1/tasks`          | Create a new task               |
| PUT    | `/api/v1/tasks/{taskId}` | Update a task                   |
| DELETE | `/api/v1/tasks/{taskId}` | Delete a task                   |
| GET    | `/api/v1/tasks/{taskId}` | Get task with Id                |
| GET    | `/metrics`               | (Optional) Productivity metrics |

---
## 💡 Future Enhancements

PRs and suggestions welcome! This project is aimed at helping backend developers and DevOps learners build and deploy apps confidently.


