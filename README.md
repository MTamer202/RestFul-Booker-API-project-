<h1 align="center">Restful Booker API – Testing Project</h1>

<p align="center">
  <em>API Test Automation using Java, RestAssured, TestNG &amp; Maven on Linux</em>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Language-Java-blue" />
  <img src="https://img.shields.io/badge/Testing-RestAssured-brightgreen" />
  <img src="https://img.shields.io/badge/Framework-TestNG-orange" />
  <img src="https://img.shields.io/badge/Build-Maven-red" />
  <img src="https://img.shields.io/badge/OS-Linux-important" />
</p>

---

## 📁 Repository — top level

This README reflects the actual top-level entries in the repo:

```text
RestFul-Booker-API-project-
├── .idea/                 # IDE settings (IntelliJ) — optional
├── manual Api/            # Manual API notes / screenshots / docs (check contents)
├── src/                   # Source code (tests & utils)
├── test-outputs/          # Test reports / outputs (screenshots, logs, etc.)
├── .gitignore
├── README.md
├── TestRunner.xml         # TestNG/Test runner XML (name in your repo)
└── pom.xml                # Maven build & dependency configuration

📂 Suggested detailed src structure

Below is a detailed structure you can drop into this README — update names (packages/classes) to match your real source layout inside src.

src
├── main
│   └── java
│       └── (optional helpers if used)
│           ├── utils/
│           │   ├── ConfigReader.java        # loads config.properties
│           │   ├── PayloadBuilder.java      # builds JSON payloads
│           │   └── RestAssuredConfig.java   # common RestAssured setup
│           └── constants/
│               └── Endpoints.java           # endpoint paths, e.g. /booking
└── test
    ├── java
    │   ├── base/
    │   │   └── BaseTest.java                # RestAssured setup (baseURI, before/after)
    │   ├── tests/
    │   │   ├── AuthTests.java               # token creation / invalid auth tests
    │   │   ├── CreateBookingTests.java
    │   │   ├── GetBookingTests.java
    │   │   ├── UpdateBookingTests.java
    │   │   └── DeleteBookingTests.java
    │   ├── utils/
    │   │   └── TestHelpers.java             # helpers used only by tests
    │   └── models/
    │       ├── Booking.java                 # booking POJO
    │       ├── BookingDates.java
    │       └── Token.java
    └── resources
        ├── config.properties                # baseUrl, username, password
        └── test-data.json                   # optional test data


Notes

If you used different package names (e.g. com.yourname.tests), replace the above with your exact packages.

If you don't use POJOs, remove models/ and note that you use raw JSON instead.

🐧 Linux-first setup & run (exact commands)

Run these in a Linux terminal (Ubuntu / Debian / WSL):

# Clone
git clone https://github.com/MTamer202/RestFul-Booker-API-project-.git
cd RestFul-Booker-API-project-

# Verify Java & Maven
java -version
mvn -version

# Install if missing (Ubuntu/Debian)
sudo apt update
sudo apt install default-jdk maven -y

# Run all tests
mvn clean test

# Run TestNG suite (if you use TestRunner.xml)
mvn clean test -DsuiteXmlFile=TestRunner.xml

🔧 Key files explained

pom.xml — dependencies (RestAssured, TestNG, JSON libs).

TestRunner.xml — TestNG suite file used to run selected groups/tests.

config.properties (under src/test/resources) — base URL, creds (don’t commit secrets).

test-outputs/ — results, screenshots, saved responses — useful for later CI uploads.

✅ End-to-end example test flow

Create auth token (POST /auth)

Create booking (POST /booking) — assert 200 & response body

Read booking by id (GET /booking/{id}) — assert fields

Update booking (PUT/PATCH /booking/{id}) — assert updated fields

Delete booking (DELETE /booking/{id}) — assert deletion

Negative tests: invalid token, malformed body, wrong HTTP method
