⚠️ Note: This project was originally uploaded from another GitHub account which has now been deactivated. Re-uploaded here for portfolio continuity.
# IXIGO.com Flight Booking Automation Framework ✈️

This project is a robust, scalable automation framework built using **Selenium WebDriver**, **Java**, **Maven**, **TestNG**, and **Cucumber (BDD)**. It automates the **flight booking module** of IXIGO.com, covering various real-world scenarios with data-driven testing and detailed reporting


## 🚀 Features

- ✅ One-way flight booking  
- ✅ Round-trip flight booking  
- ✅ Multi-passenger scenarios  
- ✅ No flights found validation  
- ✅ Data-driven testing using Excel  
- ✅ BDD with Cucumber feature files  
- ✅ Extent Reports with screenshots on failure  
- ✅ Modular Page Object Model (POM) structure


## 🧰 Tech Stack

| Tool/Library     | Purpose                          |
|------------------|----------------------------------|
| Java             | Programming language             |
| Selenium WebDriver | UI automation                  |
| Maven            | Build and dependency management  |
| TestNG           | Test execution and assertions    |
| Cucumber         | BDD and feature file support     |
| Apache POI       | Excel data handling              |
| ExtentReports    | Test reporting with screenshots  |
| Eclipse          | IDE used for development         |

## 📊 Reporting
- ExtentReports are generated in the test-output/ folder.
- Screenshots are automatically captured for failed steps.
## 📌 Future Enhancements
- Add CI/CD integration (GitHub Actions, Jenkins)
- Add mobile browser support
- Expand coverage to hotel and train modules

## 🧪 How to Run Tests

### Prerequisites

- Java JDK 8+
- Maven installed
- ChromeDriver in system PATH
- Eclipse IDE (or any Java IDE)

### Run via Maven

```bash
mvn clean test
Run Specific Feature
Use tags in your TestRunner.java or from command line:

mvn test -D cucumber.filter.tags="@OneWayFlight"



