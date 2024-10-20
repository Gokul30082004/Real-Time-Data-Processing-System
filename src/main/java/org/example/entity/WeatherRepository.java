package org.example.entity;


import org.example.entity.Weather;
import org.example.entity.WeatherId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, WeatherId> {

    List<Weather> findAll();
}
