package com.adi.sw_tdd.service;

import com.adi.sw_tdd.domain.Car;
import com.adi.sw_tdd.exception.CarNotFoundException;
import com.adi.sw_tdd.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    public void getCarDetails_returnsCarInfo() {
        given(carRepository.findByName("prius")).willReturn(getDetails());

        Car car = carService.getCarDetails("prius");

        assertThat(car.getName()).isEqualTo("prius");
        assertThat(car.getType()).isEqualTo("hybrid");
    }

    @Test
    public void getCarDetails_whenCarNotFound() throws Exception {
        given(carRepository.findByName("prius")).willReturn(null);
        assertThrows(CarNotFoundException.class, () -> carService.getCarDetails("prius"));
    }

    private Car getDetails() {
        return new Car("prius", "hybrid");
    }
}