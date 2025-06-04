# ArogyaSetuPlus - Insurance Applying Application

## 🧭 Overview
ArogyaSetuPlus is a web-based insurance application designed to simplify and streamline the process of applying for government-backed health insurance. Built with **Spring Boot**, it supports document generation, API-driven integration, and seamless Oracle Database interaction.

---

## ✨ Features
- **📝 User Registration & Authentication**
- **📄 Health Insurance Application Submission**
- **📧 Email Notification System**
- **📑 PDF Report Generation using OpenPDF**
- **🌐 RESTful APIs documented with Swagger/OpenAPI**
- **💾 Oracle Database Integration**

---

## 🛠 Tech Stack

| Component               | Technology                |
|------------------------|---------------------------|
| Programming Language    | Java 17                   |
| Framework               | Spring Boot 3.4.2         |
| Database                | Oracle DB (OJDBC 11)      |
| ORM                     | Spring Data JPA           |
| API                     | Spring Web & WebFlux      |
| Email                   | Spring Mail               |
| PDF Generator           | OpenPDF                   |
| Documentation           | SpringDoc OpenAPI         |
| Build Tool              | Maven                     |
| Dev Tools               | Spring Boot DevTools      |
| Boilerplate Reduction   | Lombok                    |

---

## ⚙️ Setup & Installation

### ✅ Prerequisites
- Java 17+
- Maven
- Oracle Database

### 🚀 Steps to Run Locally

1. **Clone the Repository**
   ```bash
   git clone https://github.com/anilvardhan03/ArogyaSetuPlus-Insurance_Applying_Application.git
   cd ArogyaSetuPlus-Insurance_Applying_Application
   ```

2. **Configure Database**
   - Open `src/main/resources/application.properties` or `application.yml`
   - Set your Oracle DB URL, username, and password.

3. **Build and Start the Application**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access Swagger API Docs**
   - Navigate to: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🔗 Postman Collection

Explore and test the available APIs with the official Postman collection in the Repo with the name ISH.postmancolletion.json
---

## 🤝 Contribution

We welcome contributions!

1. **Fork the repository**
2. **Create a new feature branch**
   ```bash
   git checkout -b feature-name
   ```
3. **Commit your changes**
   ```bash
   git commit -m "Add: Feature name"
   ```
4. **Push your branch**
   ```bash
   git push origin feature-name
   ```
5. **Open a Pull Request**

---

## 👨‍💻 Author
Developed by **Naga Mouli Anil Gubbala**
