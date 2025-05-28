
# ArogyaSetuPlus – Insurance Applying Application

**Author:** Naga Mouli Anil G

ArogyaSetuPlus is a Spring Boot-based web application that allows Indian citizens to apply for government-backed health insurance schemes. The system streamlines data collection, eligibility analysis, and submission using a modular, API-driven architecture.

---

## 🚀 Features

- **📝 Citizen Registration**
  - Multi-step dynamic form capturing full details: name, age, address, income, education, children, and more.
  - Input bindings are validated and mapped into logical groupings (`IncomeInputs`, `EducationInputs`, `ChildInputs`, etc.).

- **📑 Insurance Plan Eligibility**
  - Based on collected inputs, the backend evaluates user eligibility against multiple plans.
  - Outputs structured results like:
    - `EligibilityDetailsOutput`
    - `DcSummaryReport`
    - `CoTriggerSummary`

- **📦 Modular Architecture**
  - Cleanly separated code with binding models, REST controllers, services, and repositories.

- **📬 Email Support**
  - Configured with Spring Boot Mail for notifications and confirmations.

- **🗂 PDF Generation**
  - Generates downloadable PDFs for form summaries or plan documents using `OpenPDF`.

- **🌐 RESTful API Interface**
  - Comprehensive API collection available via Postman:
    👉 [Explore API Collection](https://anilg03.postman.co/workspace/My-Workspace~0f5d9e5a-ca46-42b8-8226-8cee9f30d83f/collection/35275376-7e31e4dc-ec69-4d95-95e0-b5f5c4fe4081?action=share&creator=35275376)

- **🔍 Swagger Documentation**
  - Integrated using `springdoc-openapi` for interactive API exploration.

- **📄 Maven & Spring Boot 3.4.2**
  - Fully Maven-configured with modular dependencies, enabling easy build and deployment.

---

## 🛠 Tech Stack

| Technology       | Description                               |
|------------------|-------------------------------------------|
| Java 17          | Core programming language                 |
| Spring Boot 3.4.2| Backend framework                         |
| Spring Data JPA  | ORM and database interaction              |
| Spring WebFlux   | Reactive API support                      |
| Spring Mail      | Email integration                         |
| OpenPDF          | PDF document generation                   |
| Oracle JDBC      | Runtime database driver                   |
| Swagger/OpenAPI  | Auto-generated API docs                   |
| Lombok           | Annotation-based boilerplate reduction    |
| Maven            | Project build and dependency management   |

---

## 🧰 Project Setup

### Requirements

- Java 17+
- Maven 3.6+
- Oracle Database (or modify to use another DB in `application.properties`)

### Clone and Build

```bash
git clone https://github.com/anilvardhan03/ArogyaSetuPlus-Insurance_Applying_Application.git
cd ArogyaSetuPlus-Insurance_Applying_Application
mvn clean install
