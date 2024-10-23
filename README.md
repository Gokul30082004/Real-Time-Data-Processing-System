Overview
This application is a real-time data processing system designed to monitor weather conditions and provide summarized insights. It retrieves weather data from the OpenWeatherMap API, performs rollups and aggregates, and alerts users based on configurable thresholds.


Technologies Used

Spring Boot: For building the application.

Hibernate: For ORM (Object-Relational Mapping) with the H2 database.

H2 Database: In-memory database for storing daily weather summaries.

OpenWeatherMap API: To retrieve real-time weather data.


Features
Continuous retrieval of weather data every 5 minutes for specified metros in India (Delhi, Mumbai, Chennai, Bangalore, Kolkata, Hyderabad).
Conversion of temperature values from Kelvin to Celsius (or Fahrenheit based on user preference).


Daily weather summaries that include:
Average temperature
Maximum temperature
Minimum temperature
Dominant weather condition


User-configurable alerting thresholds for temperature and weather conditions.
Trigger alerts when thresholds are breached.
Visualizations for daily weather summaries and historical trends.


Setup Instructions
Prerequisites
Java 11 or higher
Maven
intellij(Prefered)
A valid OpenWeatherMap API key


Steps to Run the Application
Clone the Repository:
git clone https://github.com/yourusername/weather-monitoring-system.git
cd weather-monitoring-system


Configure API Key:

Open src/main/resources/application.properties.
Add your OpenWeatherMap API key:

properties
openweathermap.api.key=YOUR_API_KEY


Run the Application:
mvn spring-boot:run


Access the Application: The application runs on http://localhost:8080.


Test Cases
1. System Setup
Verify that the system starts successfully and connects to the OpenWeatherMap API.

3. Data Retrieval
Simulate API calls and ensure correct weather data retrieval and parsing.

5. Temperature Conversion
Test temperature conversion from Kelvin to Celsius or Fahrenheit.

7. Daily Weather Summary
Simulate multiple weather updates and verify daily summary calculations.

9. Alerting Thresholds
Configure user thresholds and simulate weather data to test alerting functionality.
