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
Copy code

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
