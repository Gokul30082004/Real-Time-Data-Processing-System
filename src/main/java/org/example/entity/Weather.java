package org.example.entity;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "weather-table")
public class Weather {

    @EmbeddedId
    private WeatherId id;

    private double averageTemperature;
    private double maximumTemperature;
    private double minimumTemperature;
    private String dominantWeatherCondition;

    public Weather() {}

    public Weather(String city, LocalDate date, double averageTemperature, double maximumTemperature, double minimumTemperature, String dominantWeatherCondition) {
        this.id = new WeatherId(city, date);
        this.averageTemperature = averageTemperature;
        this.maximumTemperature = maximumTemperature;
        this.minimumTemperature = minimumTemperature;
        this.dominantWeatherCondition = dominantWeatherCondition;
    }

    // Getters and setters
    public WeatherId getId() {
        return id;
    }

    public void setId(WeatherId id) {
        this.id = id;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public double getMaximumTemperature() {
        return maximumTemperature;
    }

    public void setMaximumTemperature(double maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    public double getMinimumTemperature() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(double minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public String getDominantWeatherCondition() {
        return dominantWeatherCondition;
    }

    public void setDominantWeatherCondition(String dominantWeatherCondition) {
        this.dominantWeatherCondition = dominantWeatherCondition;
    }
}
