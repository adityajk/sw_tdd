package com.adi.sw_tdd.controller;

import com.adi.sw_tdd.domain.Car;
import com.adi.sw_tdd.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/cars/{name}")
    public Car getCar(@PathVariable String name) {
        return carService.getCarDetails(name);
    }
}
