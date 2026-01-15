package org.example.service;

import org.example.entity.Weather;
import org.example.repository.WeatherRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeatherService {


    private final  WeatherRepositiory weatherRepositiory;

    public WeatherService(WeatherRepositiory weatherRepositiory) {
        this.weatherRepositiory = weatherRepositiory;
    }

    public String getWeatherByCity(String city){
        System.out.println("fetching data from from db for city: "+city);
        Optional<Weather> weather=weatherRepositiory.findByCity(city);

        return weather.map(Weather::getForecast).orElse("weather data not available");
    }



}
