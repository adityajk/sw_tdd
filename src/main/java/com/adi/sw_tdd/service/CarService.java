package com.adi.sw_tdd.service;

import com.adi.sw_tdd.domain.Car;
import com.adi.sw_tdd.exception.CarNotFoundException;
import com.adi.sw_tdd.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car getCarDetails(String name) {
        Car carname = carRepository.findByName(name);
        if(null == carname)
        {
            throw new CarNotFoundException();
        }
        return carname;
    }
}
