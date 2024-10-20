package org.example;

import org.example.entity.Weather;
import org.example.entity.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherService {

    @Value("${openweather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final WeatherRepository weatherRepository; // Add this line

    @Autowired
    public WeatherService(RestTemplate restTemplate, WeatherRepository weatherRepository) {
        this.restTemplate = restTemplate;
        this.weatherRepository = weatherRepository; // Initialize the repository
    }

    public WeatherResponse getWeather(String city) {
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", city, apiKey);
        try {
            return restTemplate.getForObject(url, WeatherResponse.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }

    // New method to save weather data
//    public void saveWeatherData(String city, double temperature, double feelsLike, String mainCondition) {
//        Weather weather = new Weather(city, temperature, feelsLike, mainCondition);
//        weatherRepository.save(weather);
//    }
}
