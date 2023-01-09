package com.example.demo.controller;

import com.example.demo.model.Ride;
import com.example.demo.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RideController {

    @Autowired
    private RideService rideService;

    @RequestMapping(value = {"", "/", "home"})
    public String displayHomePage(Model model) {
        model.addAttribute("username", "John Doe");
        return "home.html";
    }

    @RequestMapping(value = "/rides", method = RequestMethod.GET)
    public @ResponseBody List<Ride> getRides() {
        return rideService.getRides();
    }

    @RequestMapping(value = "/ride", method = RequestMethod.POST)
    public @ResponseBody Ride createRide(@RequestBody Ride ride) {
        return rideService.createRide(ride);
    }


    @RequestMapping(value = "/ride/{id}", method = RequestMethod.GET)
    public @ResponseBody Ride getRide(@PathVariable(value = "id") Integer id) {
        return rideService.getRide(id);
    }

}
