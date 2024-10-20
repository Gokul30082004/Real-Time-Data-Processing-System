package org.example;

import jakarta.annotation.PostConstruct;
import org.example.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
public class WeatherController {

    private final WeatherService weatherService;
    private ScheduledExecutorService scheduler;

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private AlertRepository alertRepository;
    @PostConstruct
    public void init() {

        scheduler = Executors.newScheduledThreadPool(1);

        Runnable fetchWeatherTask = () -> {
            String[] cities = {"Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"};
            for (String city : cities) {
                WeatherResponse weather = weatherService.getWeather(city);

//                System.out.println(weather.getMain().getTemp());
//                System.out.println("Fetched weather for " + city + ": " + weather.getMain().getTemp());


                //checking for the alert
                List<Alert> alerts = alertRepository.findAll();

                for(Alert alert : alerts){

                    System.out.println(alert.getWeatherCondition() + " " + alert.getTemperatureThreshold() + " " + city + " " + weather.getMain().getTemp());

                    if(alert.getWeatherCondition().equals(city) && alert.getTemperatureThreshold() == weather.getMain().getTemp()){
                        System.out.println("Alert : the temperature is " + alert.getWeatherCondition() + " and it is " + alert.getTemperatureThreshold());
                    }

                }


                //updating to the table
                double maxTemp = weather.getMain().getTemp();
                double minTemp = weather.getMain().getTemp();
                double avgTemp = weather.getMain().getTemp();
                String dominantCondition = weather.getWeather()[0].getDescription();

                LocalDate today = LocalDate.now();

                Weather existingWeather =  weatherRepository.findById(new WeatherId(city, today)).orElse(null);

                if (existingWeather != null) {
                    minTemp = Math.min(minTemp, existingWeather.getMinimumTemperature());
                    maxTemp = Math.max(maxTemp, existingWeather.getMaximumTemperature());
                    avgTemp = (maxTemp + existingWeather.getAverageTemperature()) / 2; // Update the average temperature based on the new min/max
                }

                Weather w = new Weather(city, today, avgTemp, maxTemp, minTemp, dominantCondition);
                weatherRepository.save(w);





            }
        };

        // Schedule the task to run every 5 minutes
        scheduler.scheduleAtFixedRate(fetchWeatherTask, 0, 5, TimeUnit.MINUTES);
    }

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String getWeather(Model model) {


        String arr[] = new String[]{"Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"};

        for(String city : arr) {
            WeatherResponse weatherResponse = weatherService.getWeather(city);
            model.addAttribute(city + "temperature", weatherResponse.getMain().getTemp());
            model.addAttribute(city + "feelsLike", weatherResponse.getMain().getFeels_like());
            model.addAttribute(city + "mainCondition", weatherResponse.getWeather()[0].getMain());

//            System.out.println(weatherResponse.getMain().getTemp() + " " + city);
        }

        List<Weather> weatherResponse = weatherRepository.findAll();
        model.addAttribute("weatherList", weatherResponse);
//        model.addAttribute("weatherData", weatherData);
//        System.out.println(weatherResponse.size() + "  aadfcvsdsdff");
        return "weather";
    }
}