package org.example.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class WeatherId implements Serializable {

    private String city;
    private LocalDate date;

    public WeatherId() {}

    public WeatherId(String city, LocalDate date) {
        this.city = city;
        this.date = date;
    }

    // Getters and setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherId weatherId = (WeatherId) o;
        return city.equals(weatherId.city) && date.equals(weatherId.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, date);
    }
}