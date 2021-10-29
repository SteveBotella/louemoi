package com.rent.rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController //The answer will be Json
public class CarController {

    private RestTemplate restTemplate = new RestTemplate();

    //Show all Cars
    @GetMapping(value = "/cars")
    public String listcars(){
        return this.restTemplate.getForObject("http://localhost:8081/cars", String.class);
    }

    ////Show one car
    @GetMapping(value = "/car/{id}") //Call this methode only for a get request
    public String displayCar(@PathVariable int id){
        return this.restTemplate.getForObject("http://localhost:8081/car/" + id, String.class);
    }

    ////Show add car
    @PostMapping(value = "/car/add") //Call this methode only for a get request
    public Object displayCar(@RequestBody Object object){
        return this.restTemplate.postForObject("http://localhost:8081/car/add/", object, Object.class);
    }

    @PostMapping(value = {"/car/update"})
    public Object updateCar(@RequestBody Object object) {
        return this.restTemplate.postForObject("http://localhost:8081/car/update/", object, Object.class);
    }

    //Delete one product
    @DeleteMapping(value = "/car/delete/{id}")
    public Boolean deleteCar(@PathVariable int id) {
        if (this.displayCar(id) != null) {
            this.restTemplate.delete("http://localhost:8081/car/delete/" + id);
            return true;
        }
       return false;
    }
}