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
