package org.example.repository;

import org.example.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepositiory extends JpaRepository<Weather, Long> {

    Optional<Weather> findByCity(String city);
    void deleteByCity(String city);

}
