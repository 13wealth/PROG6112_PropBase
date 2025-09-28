# 🏠 Property Management System (PropBase)

PropBase is a Java Swing application for managing property records.  
It allows you to **register, search, update, and delete properties** stored in a JSON file.  
The system also includes input validation and unit tests to ensure correctness.

---

## ✨ Features
- 📋 **Register Property** – Add new property records with validation.  
- 🔎 **Search Property** – Search for properties by account number.  
- 📝 **Update Property** – Update property details (address, rent, type, etc.).  
- ❌ **Delete Property** – Remove property records by account number.  
- ✅ **Validation** – Ensures rent amounts, numeric fields, and formats are correct.  
- 🧪 **JUnit Tests** – Covers key functionality:
  - Property search (found/not found)  
  - Updating properties  
  - Deleting properties  
  - Validation rules  

---

## 🛠️ Tech Stack
- **Java** (Swing for UI)  
- **JSON** (org.json library for persistence)  
- **JUnit 5** (unit testing)  

---

## 📂 Project Structure
com.prop_base/
├── AddProperty.java
├── SearchProperty.java
├── UpdateProperty.java
├── DeleteProperty.java
├── ValidationsHelper.java
├── test/ (JUnit test cases)
└── AllProperties.json (data file)

yaml  
This workflow will build a Java project with Maven, and cache/restore any dependencies to improve the workflow execution time
For more information see: https://docs.github.com/en/actions/automating-builds-and-tests/building-and-testing-java-with-maven

This workflow uses actions that are not certified by GitHub.
They are provided by a third-party and are governed by
separate terms of service, privacy policy, and support
documentation.

name: QuickChat CI

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v4

      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'

      - name: Install Xvfb
        run: |
          sudo apt-get update
          sudo apt-get install -y xvfb
      - name: Build and test with Maven
        run: xvfb-run --auto-servernum mvn test -Djava.awt.headless=false

      - name: Enable debug logging
        run: mvn -X clean install

---

## 🚀 Getting Started

### Prerequisites
- Java 17+  
- Maven or Gradle (optional, for dependency management)  

### Running the App
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/propbase.git
   cd propbase
Make sure AllProperties.json is present in the root directory.

Run the application:

bash
Copy code
javac com/prop_base/*.java
java com.prop_base.MainApp
🧪 Running Tests
JUnit tests are located under src/test/java/com/prop_base.
To run all tests:

bash
Copy code
mvn test
or, if not using Maven, run directly in your IDE (IntelliJ, Eclipse, VSCode).

📊 Data Format (AllProperties.json)
Example property entry:

json
Copy code
[
  {
    "Account Number": "P1001",
    "Property Address": "123 Main St",
    "Monthly Rent": 1500.00,
    "Property Type": "Apartment",
    "Bedrooms": 2,
    "Bathrooms": 1,
    "Status": "Available",
    "Agent": "John Smith"
  }
]
📖 References
OpenAI. (2025, September). ChatGPT (Version GPT-5) [Large language model]. https://chat.openai.com

👨‍💻 Author
Smith Mbele – Student Developer
