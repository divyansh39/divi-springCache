package org.example.service;

import org.example.entity.Weather;
import org.example.repository.WeatherRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class WeatherService {


    private final  WeatherRepositiory weatherRepositiory;

    public WeatherService(WeatherRepositiory weatherRepositiory) {
        this.weatherRepositiory = weatherRepositiory;
    }

    @Cacheable(value="weather",key="#city")
    public String getWeatherByCity(String city){
        System.out.println("fetching data from from db for city: "+city);
        Optional<Weather> weather=weatherRepositiory.findByCity(city);

        return weather.map(Weather::getForecast).orElse("weather data not available");
    }

    @CachePut(value="weather", key="#city")
    public String updateWeather(String city, String updatedWeather){
        weatherRepositiory.findByCity(city).ifPresent((weather)->{
            weather.setForecast(updatedWeather);
            weatherRepositiory.save(weather);
        });
        return updatedWeather;
    }
    @Transactional
    @CacheEvict(value="weather", key="#city")
    public void deleteWeather(String city){
        System.out.println("removing weather data for city: "+city);
        weatherRepositiory.deleteByCity(city);
    }



}
