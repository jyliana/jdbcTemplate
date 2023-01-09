package com.example.demo;

import com.example.demo.model.Ride;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RestControllerTest {

    @Test
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void testGetRides() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange("http://localhost:8080/rides", HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                });
        List<Ride> rides = ridesResponse.getBody();

        for (Ride ride : rides) {
            System.out.println("Ride name: " + ride.getName());
        }
    }

    @Test
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void testCreateRide() {
        RestTemplate restTemplate = new RestTemplate();
        Ride ride = new Ride();
        ride.setName("Black Forest Trail");
        ride.setDuration(27);

        ride = restTemplate.postForObject("http://localhost:8080/ride", ride, Ride.class);
        System.out.println(ride);
    }

    @Test
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void testGetRide() {
        RestTemplate restTemplate = new RestTemplate();
        Ride ride = restTemplate.getForObject("http://localhost:8080/ride/1", Ride.class);
        System.out.println("Ride name:" + ride.getName());

    }
}
