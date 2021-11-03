package com.rent.rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Service
@ConfigurationProperties(prefix = "endpoint")
@RestController //The answer will be Json
public class CarController {
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${endpoint.url}")
    private String endpoint;

    //Show all Cars
    @GetMapping(value = "/car")
    public String listcars(){
        return this.restTemplate.getForObject(endpoint+"/car", String.class);
    }

    ////Show one car
    @GetMapping(value = "/car/{id}") //Call this methode only for a get request
    public String displayCar(@PathVariable int id){
        return this.restTemplate.getForObject(endpoint+"/car/" + id, String.class);
    }

    ////Show add car
    @PostMapping(value = "/car") //Call this methode only for a get request
    public Object displayCar(@RequestBody Object object){
        return this.restTemplate.postForObject(endpoint+"/car/", object, Object.class);
    }

    @PutMapping(value = {"/car/{id}"})
    public Object updateCar(@RequestBody Object object, @PathVariable int id) {
        this.restTemplate.put(endpoint+"/car/"+id, object);
        return this.displayCar(id);
    }

    //Delete one product
    @DeleteMapping(value = "/car/{id}")
    public Boolean deleteCar(@PathVariable int id) {
        if (this.displayCar(id) != null) {
            this.restTemplate.delete(endpoint+"/car/" + id);
            return true;
        }
       return false;
    }
}