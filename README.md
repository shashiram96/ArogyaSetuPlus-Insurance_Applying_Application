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

Explore and test the available APIs with the official Postman collection:  
👉 [ArogyaSetuPlus Postman Workspace](https://anilg03.postman.co/workspace/My-Workspace~0f5d9e5a-ca46-42b8-8226-8cee9f30d83f/collection/35275376-7e31e4dc-ec69-4d95-95e0-b5f5c4fe4081?action=share&creator=35275376)

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
