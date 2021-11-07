package com.adi.sw_tdd.controller;

import com.adi.sw_tdd.domain.Car;
import com.adi.sw_tdd.exception.CarNotFoundException;
import com.adi.sw_tdd.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarService carService;

    @Test
    public void getCar_shouldReturnCar() throws Exception{
        given(carService.getCarDetails(anyString())).willReturn(getDetails());

        mockMvc.perform(get("/cars/prius"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("prius"))
                .andExpect(jsonPath("type").value("hybrid"));
    }

    @Test
    public void getCar_notFound() throws Exception {
        given(carService.getCarDetails(anyString())).willThrow(CarNotFoundException.class);

        mockMvc.perform(get("/cars/prius"))
                .andExpect(status().isNotFound());

    }

    private Car getDetails() {
        return new Car("prius", "hybrid");
    }
}
