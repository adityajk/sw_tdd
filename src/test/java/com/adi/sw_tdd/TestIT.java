package com.adi.sw_tdd;

import com.adi.sw_tdd.domain.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class TestIT extends BaseIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void test() {
        ResponseEntity<Car> responseEntity = this.testRestTemplate.getForEntity("/cars/{name}", Car.class, "tesla");
        Car car = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(car.getName()).isEqualToIgnoringCase("tesla");
        assertThat(car.getType()).isEqualToIgnoringCase("electric");
    }
}
