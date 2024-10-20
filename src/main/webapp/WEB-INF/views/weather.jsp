<%@ page import="java.util.List" %>
<%@ page import="org.example.entity.Weather" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weather Monitoring System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        header {
            background: #007BFF;
            color: white;
            padding: 20px;
            text-align: center;
        }

        main {
            padding: 20px;
        }

        h2 {
            border-bottom: 2px solid #007BFF;
            padding-bottom: 10px;
        }

        .weather-summary, .daily-summary, .alert-settings, .city-input {
            background: white;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .weather-summary{
            display : flex;
         }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #007BFF;
            color: white;
        }

        footer {
            text-align: center;
            margin-top: 20px;
        }

        #myChart {
            max-width: 600px;
            margin: auto;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<header>
    <h1>Real-Time Weather Monitoring</h1>
</header>

<main>
    <h2>Current Weather</h2>
    <section class="weather-summary">

        <div id="currentWeather">
            <p>City: <span id="city"> Delhi</span></p>
            <p>Temperature: <span id="temperature"> ${Delhitemperature}</span> °C</p>
            <p>Feels Like: <span id="feelsLike">  ${DelhifeelsLike}</span> °C</p>
            <p>Last Updated: <span id="lastUpdated"></span></p>
            <button id="convertToKelvin" onCLick="convertToKelvin(1, ${Delhitemperature})">Convert to Kelvin</button>
                        <button id="convertToCelsius" onClick="convertToCelsius(1, ${Delhitemperature})">Convert to Celsius</button>
                        <p>Converted Temperature: <span id="convertedTemperature1">  ${Delhitemperature} °C</span></p>
        </div>
        <div id="currentWeather">
            <p>City: <span id="city"> Mumbai</span></p>
            <p>Temperature: <span id="temperature"> ${Mumbaitemperature}</span> °C</p>
            <p>Feels Like: <span id="feelsLike">  ${MumbaifeelsLike}</span> °C</p>
            <p>Last Updated: <span id="lastUpdated"></span></p>
            <button id="convertToKelvin" onCLick="convertToKelvin(2, ${Mumbaitemperature})">Convert to Kelvin</button>
            <button id="convertToCelsius" onClick="convertToCelsius(2, ${Mumbaitemperature})">Convert to Celsius</button>
            <p>Converted Temperature: <span id="convertedTemperature2">  ${Mumbaitemperature} °C</span></p>
        </div>
        <div id="currentWeather">
            <p>City: <span id="city"> Chennai</span></p>
            <p>Temperature: <span id="temperature"> ${Chennaitemperature}</span> °C</p>
            <p>Feels Like: <span id="feelsLike">  ${ChennaifeelsLike}</span> °C</p>
            <p>Last Updated: <span id="lastUpdated"></span></p>
            <button id="convertToKelvin" onCLick="convertToKelvin(3, ${Chennaitemperature})">Convert to Kelvin</button>
            <button id="convertToCelsius" onClick="convertToCelsius(3, ${Chennaitemperature})">Convert to Celsius</button>
            <p>Converted Temperature: <span id="convertedTemperature3">  ${Chennaitemperature}  °C</span></p>
        </div>
        <div id="currentWeather">
            <p>City: <span id="city"> Bangalore</span></p>
            <p>Temperature: <span id="temperature"> ${Bangaloretemperature}</span> °C</p>
            <p>Feels Like: <span id="feelsLike">  ${BangalorefeelsLike}</span> °C</p>
            <p>Last Updated: <span id="lastUpdated"></span></p>
            <button id="convertToKelvin" onCLick="convertToKelvin(4, ${Bangaloretemperature})">Convert to Kelvin</button>
            <button id="convertToCelsius" onClick="convertToCelsius(4, ${Bangaloretemperature})">Convert to Celsius</button>
            <p>Converted Temperature: <span id="convertedTemperature4">  ${Bangaloretemperature} °C</span></p>
        </div>
        <div id="currentWeather">
            <p>City: <span id="city"> Kolkata</span></p>
            <p>Temperature: <span id="temperature"> ${Kolkatatemperature}</span> °C</p>
            <p>Feels Like: <span id="feelsLike">  ${KolkatafeelsLike}</span> °C</p>
            <p>Last Updated: <span id="lastUpdated"></span></p>
            <button id="convertToKelvin" onCLick="convertToKelvin(5, ${Kolkatatemperature})">Convert to Kelvin</button>
            <button id="convertToCelsius" onClick="convertToCelsius(5, ${Kolkatatemperature})">Convert to Celsius</button>
            <p>Converted Temperature: <span id="convertedTemperature5">  ${Kolkatatemperature} °C</span></p>
        </div>
        <div id="currentWeather">
            <p>City: <span id="city"> Hyderabad</span></p>
            <p>Temperature: <span id="temperature"> ${Hyderabadtemperature}</span> °C</p>
            <p>Feels Like: <span id="feelsLike">  ${HyderabadfeelsLike}</span> °C</p>
            <p>Last Updated: <span id="lastUpdated"></span></p>
            <button id="convertToKelvin" onCLick="convertToKelvin(6, ${Hyderabadtemperature})">Convert to Kelvin</button>
            <button id="convertToCelsius" onClick="convertToCelsius(6, ${Hyderabadtemperature})">Convert to Celsius</button>
            <p>Converted Temperature: <span id="convertedTemperature6">  ${Hyderabadtemperature} °C</span></p>
        </div>
    </section>

    <section class="daily-summary">
        <h2>Daily Weather Summary</h2>

        <%
            List<Weather> weatherList = (List<Weather>) request.getAttribute("weatherList");
            if (weatherList != null) {
        %>


        <table id="dailySummaryTable">
            <thead>
                <tr>
                    <th>City </th>
                    <th>Date</th>
                    <th>Average Temp (°C)</th>
                    <th>Max Temp (°C)</th>
                    <th>Min Temp (°C)</th>
                    <th>Dominant Condition</th>
                </tr>
            </thead>

            <tbody id="dailySummaryBody">
            <!-- Dynamic rows will be inserted here --!>

             <%
                        for (Weather weather : weatherList) {
                    %>
                        <tr>
                            <td><%= weather.getId().getCity() %></td>
                            <td><%= weather.getId().getDate() %></td>
                            <td><%= weather.getAverageTemperature() %></td>
                            <td><%= weather.getMaximumTemperature() %></td>
                            <td><%= weather.getMinimumTemperature() %></td>
                            <td><%= weather.getDominantWeatherCondition() %></td>
                        </tr>
                    <%
                        }
                    %>
            </tbody>
        </table>
        <%
            }
        %>
    </section>

    <section class="alert-settings">
        <h2>Alert Settings</h2>
        <form id="alertForm">
            <label for="tempThreshold">Temperature Threshold (°C):</label>
            <input type="text" id="tempThreshold" required>

            <label for="condition">Weather City:</label>
            <input type="text" id="condition" required>

            <button type="submit">Set Alert</button>
        </form>
        <div id="alerts"></div>
    </section>
</main>

<footer>
    <p>&copy; 2024 Weather Monitoring System</p>
</footer>

<script>

    // Handle alert form submission
    document.getElementById("alertForm").addEventListener("submit", function(event) {
        event.preventDefault();
        const tempThreshold = document.getElementById("tempThreshold").value;
        const condition = document.getElementById("condition").value;

        const alertData = {
            temperatureThreshold: parseFloat(tempThreshold),
            weatherCondition: condition
        };

        fetch('/api/alerts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(alertData)
        })
        .then(response => response.json())
        .then(data => {
            document.getElementById("alerts").innerText = `Alert set for ${data.temperatureThreshold}°C and city: ${data.weatherCondition}`;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById("alerts").innerText = 'Failed to set alert.';
        });
    });


    function convertToCelsius(num,data){
        const toChange=document.getElementById("convertedTemperature"+num);
        toChange.innerHTML=data +"°C";
    }

    function convertToKelvin(num,data){
        const toChange=document.getElementById("convertedTemperature"+num);
        toChange.innerHTML=data-273.15 + "Â";
    }



</script>
</body>
</html>
