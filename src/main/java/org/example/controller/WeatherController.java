package org.example.controller;

import org.example.entity.Weather;
import org.example.repository.WeatherRepositiory;
import org.example.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController{

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherRepositiory weatherRepositiory;

    @GetMapping
    public String getWeather(@RequestParam String city){

        return weatherService.getWeatherByCity(city);
    }

    @PostMapping
    public Weather addWeather(@RequestBody Weather weather){

        return weatherRepositiory.save(weather);
    }

    @GetMapping("/all")
    public List<Weather> getAllWeather(){
        return weatherRepositiory.findAll();
    }
}