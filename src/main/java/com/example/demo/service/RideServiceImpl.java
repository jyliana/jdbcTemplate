package com.example.demo.service;

import com.example.demo.model.Ride;
import com.example.demo.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rideService")
public class RideServiceImpl implements RideService{
    @Autowired
    private RideRepository rideRepository;

    @Override
    public List<Ride> getRides() {
        return rideRepository.getRides();
    }

    @Override
    public Ride getRide(Integer id){
        return rideRepository.getRide(id);
    }

    @Override
    public Ride createRide(Ride ride) {
        return rideRepository.createRide(ride);
    }
}
