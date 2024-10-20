package org.example.entity;


import org.example.entity.Weather;
import org.example.entity.WeatherId;
import org.example.entity.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WeatherInfoService {

    @Autowired
    private WeatherRepository weatherRepository;

    public List<Weather> getAllWeather() {
        return weatherRepository.findAll();
    }

    public Weather getWeatherByCityAndDate(String city, LocalDate date) {
        return weatherRepository.findById(new WeatherId(city, date)).orElse(null);
    }

    public Weather saveWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    public void deleteWeather(String city, LocalDate date) {
        weatherRepository.deleteById(new WeatherId(city, date));
    }
}
