# 🚀 DevTracker — Full-Stack Learning Project (Spring Boot + React)

**DevTracker** is a full-stack web application built using **Spring Boot (Java)** for the backend and **React (TypeScript)** for the frontend.  
The main purpose of this project is to **learn real-world DevOps workflows** — covering CI/CD pipelines, containerization, and orchestration using **Docker** and **Kubernetes**.

---

## 🧩 Project Overview

| Layer | Tech Stack | Folder |
|--------|-------------|--------|
| Frontend | React (Vite) + TypeScript | `/client` |
| Backend | Spring Boot (Java 21, Maven) | `/api` |
| Database | PostgreSQL (local) | — |
| CI/CD | GitHub Actions + SonarQube (Code Quality) | `.github/workflows` |

The application demonstrates how to structure, build, test, and deploy a **multi-service project** under a single repository — similar to a lightweight **monorepo** setup.

---

## 📂 Directory Structure

```
root/
 ├── api/                 # Spring Boot backend
 │   ├── src/
 │   ├── pom.xml
 │   └── target/
 │
 ├── client/               # React frontend (Vite)
 │   ├── src/
 │   ├── package.json
 │   └── dist/
 │
 ├── .github/
 │   └── workflows/
 │       └── ci.yml       # CI pipeline for both frontend and backend
 │
 ├── docker-compose.yml   # Planned for multi-container setup
 ├── README.md
 └── LICENSE
```

---

## ⚙️ Backend (Spring Boot)

**Tech Stack:**
- Java 21
- Spring Boot 3.5.7
- Maven
- PostgreSQL (local)
- JUnit for testing

**To run locally:**
```bash
cd api
mvn spring-boot:run
```

**To test:**
```bash
mvn test
```

**To build a JAR:**
```bash
mvn clean package -DskipTests
```

The generated JAR will be available under:
```
api/target/dev-tracker.jar
```

---

## 💻 Frontend (React + TypeScript)

**Tech Stack:**
- React (Vite)
- TypeScript
- Axios for API requests
- Jest + React Testing Library (for unit tests)

**To run locally:**
```bash
cd client
npm install
npm run dev
```

**To build for production:**
```bash
npm run build
```

The production build will be generated under:
```
react/dist/
```

---

## 🧪 Continuous Integration (GitHub Actions)

The repository uses a **modular CI pipeline** defined in `.github/workflows/ci.yml`.  
It includes separate jobs for frontend and backend, both triggered manually (or by PRs in the future).

### 🔧 Frontend-CI Steps
- Checkout code
- Setup Node.js environment
- Install dependencies (`npm ci`)
- Build application
- Run SonarQube scan
- Upload build artifact (`react/dist`)

### 🔧 Backend-CI Steps
- Checkout code
- Setup Java environment (Temurin 21)
- Build Maven project
- Run JUnit tests
- Perform SonarQube analysis
- Upload build artifact (`dev-tracker.jar`)

---

## ☁️ SonarQube Integration

Static code analysis and quality gates are powered by **SonarCloud / SonarQube**.  
- Frontend and backend scans are configured independently.
- Results show code coverage, code smells, and maintainability metrics.
- Tokens are stored securely in GitHub Secrets.

---

## 🐳 Docker & Kubernetes (Planned)

### Phase 1 — Dockerization
- Build Docker images for both backend and frontend.
- Use a `docker-compose.yml` file for local multi-container setup.
- Example:
  ```bash
  docker compose up --build
  ```

### Phase 2 — Kubernetes Deployment
- Write Kubernetes manifests for both services (`Deployment`, `Service`, `Ingress`).
- Deploy using `kubectl` or Helm charts.
- Add CI/CD automation for container builds and push to DockerHub or GHCR.

---

## 📈 Future Enhancements

- [ ] Add database migration support (Flyway)
- [ ] Implement centralized logging with ELK stack
- [ ] Integrate monitoring (Prometheus + Grafana)
- [ ] Create full CD pipeline (auto-deploy to Kubernetes)
- [ ] Add environment-based configuration (Dev / CI / Prod)

---

## 🧰 Tools & Services Used

| Category | Tool |
|-----------|------|
| CI/CD | GitHub Actions |
| Code Quality | SonarQube / SonarCloud |
| SCM | Git & GitHub |
| Build Tools | Maven, npm |
| Containers | Docker (planned) |
| Orchestration | Kubernetes (planned) |

---

## 🧑‍💻 Author

**Varun Matangi**  
Learning full-stack DevOps by building end-to-end projects.  
> *"Building things the real-world way — one commit at a time."*

