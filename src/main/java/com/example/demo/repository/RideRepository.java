package com.example.demo.repository;

import com.example.demo.model.Ride;

import java.util.List;

public interface RideRepository {
    List<Ride> getRides();

    Ride createRide(Ride ride);

    Ride getRide(Integer id);
}
