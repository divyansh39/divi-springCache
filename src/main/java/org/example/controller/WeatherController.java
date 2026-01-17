package org.example.controller;

import org.example.entity.Weather;
import org.example.repository.WeatherRepositiory;
import org.example.service.CacheInspectionService;
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

    @Autowired
    private CacheInspectionService cacheInspectionService;

    @GetMapping
    public String getWeather(@RequestParam String city){
        String weatherCity=weatherService.getWeatherByCity(city);
        return weatherCity;
    }


    @PostMapping
    public Weather addWeather(@RequestBody Weather weather){

        return weatherRepositiory.save(weather);
    }

    @GetMapping("/all")
    public List<Weather> getAllWeather(){
        return weatherRepositiory.findAll();
    }


    @GetMapping("/cacheData")
    public void getCacheData(){
        cacheInspectionService.printCacheContents("weather");
    }

}