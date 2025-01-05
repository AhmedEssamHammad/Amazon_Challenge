# Automation Project README

## Overview
This project is a Selenium-based automation testing framework built using Java. It follows the **Page Object Model (POM)** design pattern and incorporates data-driven testing using **Data Providers** and **JSON files**. The framework generates detailed test reports with **Allure Reports**.

---

## Features
- **Page Object Model (POM):** Modular and maintainable test structure.
- **Data-Driven Testing:** Parameterized tests using TestNG Data Providers and JSON files.
- **Allure Reports:** Comprehensive and visually appealing reports for test execution.
- **Cross-Browser Testing:** Easily configurable for multiple browsers.
- **Reusable Components:** Utility methods for common test actions.

---

## Prerequisites
### Tools & Technologies
- **Java**: JDK 8 or higher
- **Selenium WebDriver**
- **TestNG**
- **Allure Reports**
- **JSON Library** (e.g., `org.json` or `Gson`)

### Installation
1. Install [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html).
2. Install [Maven](https://maven.apache.org/).
3. Add Java and Maven to your system PATH.
4. Install an IDE like [IntelliJ IDEA](https://www.jetbrains.com/idea/) or [Eclipse](https://www.eclipse.org/).

---

## Project Structure
```
project-root
|-- src
|   |-- main
|   |   |-- java
|   |   |   |-- com.projectname.pages      # Page classes
|   |   |   |-- com.projectname.utils      # Utility classes (e.g., JSON reader)
|   |-- test
|       |-- java
|           |-- com.projectname.tests      # Test classes
|           |-- com.projectname.data       # Data provider classes
|-- test-output                           # TestNG reports
|-- allure-results                        # Allure report data
|-- pom.xml                               # Maven dependencies
```

---

## Setup and Configuration

### Clone the Repository
```bash
git clone https://github.com/AhmedEssamHammad/Amazon_Challenge.git
cd Amazon_Challenge
```

### Install Dependencies
Run the following command to install required dependencies:
```bash
mvn clean install
```

### Configuration Files
- **`config.properties`**: Contains environment-specific settings.
- **`testdata.json`**: Stores test data for data-driven testing.

---

## Running the Tests

### Command Line Execution
1. **Run All Tests:**
   ```bash
   mvn test
   ```
2. **Run Specific Tests:**
   ```bash
   mvn -Dtest=TestClassName test
   ```

### Generate Allure Reports
1. Execute tests to generate Allure results.
2. Run the following commands to serve the report:
   ```bash
   allure serve allure-results
   ```

---

## Key Components

### Page Object Model (POM)
Each page of the application under test has a corresponding Java class that contains:
- Web element locators
- Methods to perform actions on those elements

**Example:**
```java
public class LoginPage {
     private By loginIcon = By.id("nav-link-accountList");
    private By phoneField = By.id("ap_email");
    private By continueButton = By.id("continue");
    private By passwordField = By.id("ap_password");
    private By signInButton = By.id("signInSubmit");


    @Step("-click login button")
    public LoginPage openAmazonURL(String url) {
        openURL(url);
        return this;
    }

    @Step("-click login Icon")
    public LoginPage clickLoginIcon() {
        clickElement(loginIcon);
        return this;
    }

    @Step("-send text to phone number")
    public LoginPage sendTextToPhoneNumberField(String phone) {
        sendText(phoneField, phone);
        return this;
    }
}
```

### Data Providers
TestNG Data Providers are used to pass data to test methods
```

### JSON Data
JSON files store test data and configurations.
**Example: Reading JSON Data**
```java
public JsonDataProviderMapper(String filePath) {
        try {
            // Load the JSON file
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(filePath); // Path to JSON file
            jsonNode = mapper.readTree(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load constants.json");
        }
    }
```

---

## Dependencies
Include the following dependencies in your `pom.xml`:
```xml
<dependencies>
    <dependency>
      <groupId>org.testng</groupId>
      <artifactId>testng</artifactId>
      <version>7.7.0</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-java</artifactId>
      <version>4.19.0</version>
    </dependency>

    <dependency>
      <groupId>io.qameta.allure</groupId>
      <artifactId>allure-testng</artifactId>
      <version>2.27.0</version>
    </dependency>
</dependencies>
```

---

## Troubleshooting
- **Test Failures:** Check the logs.
- **Allure Report Issues:** Ensure the `allure-results` folder is populated.
- **Dependency Errors:** Run `mvn dependency:resolve` to check for missing dependencies.

---

## Contributions
Contributions are welcome! Fork the repository and create a pull request with your changes.

---

