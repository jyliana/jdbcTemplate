package com.example.demo.service;

import com.example.demo.model.Ride;

import java.util.List;

public interface RideService {
    List<Ride> getRides();

    Ride createRide(Ride ride);

    Ride getRide(Integer id);
}
