<h1 align="center">Restful Booker API – Testing Project</h1>

<p align="center">
  <em>API Test Automation using Java, RestAssured, TestNG & Maven on Linux</em>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Language-Java-blue" />
  <img src="https://img.shields.io/badge/Testing-RestAssured-brightgreen" />
  <img src="https://img.shields.io/badge/Framework-TestNG-orange" />
  <img src="https://img.shields.io/badge/Build-Maven-red" />
  <img src="https://img.shields.io/badge/OS-Linux-important" />
</p>

---

## 📌 Project Overview

This project is an end-to-end **API testing framework** for the public  
<a href="https://restful-booker.herokuapp.com/">Restful Booker</a> application.

It validates the main booking flows:

- Creating a booking
- Retrieving a booking
- Updating a booking
- Deleting a booking
- Authentication and token handling
- Negative scenarios (invalid data, missing token, etc.)

The project is designed to run smoothly on **Linux environments** (Ubuntu, Debian, etc.) using the terminal.

---

## 🧩 Tech Stack

<ul>
  <li><strong>Language:</strong> Java</li>
  <li><strong>Build Tool:</strong> Maven</li>
  <li><strong>Testing Framework:</strong> TestNG</li>
  <li><strong>API Testing:</strong> RestAssured</li>
  <li><strong>OS:</strong> Linux (tested on Ubuntu-based distros)</li>
</ul>

---

## 📂 Project Structure

> Example structure (update if needed to match your repo)

```text
RestFul-Booker-API-project-
├── src
│   ├── main
│   │   └── java
│   │       └── utils/           # Helpers, config, payload builders
│   └── test
│       └── java
│           ├── base/            # Base test class, setup & teardown
│           ├── tests/           # Test classes (auth, booking, CRUD)
│           └── models/          # POJOs / request & response models (if used)
├── testng.xml                   # TestNG suite configuration
├── pom.xml                      # Maven configuration & dependencies
└── README.md
