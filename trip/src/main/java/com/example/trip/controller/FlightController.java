package com.example.trip.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.trip.entity.Flight;
import com.example.trip.entity.ThingsTodo;
import com.example.trip.entity.User;
import com.example.trip.mapper.FlightMapper;
import com.example.trip.mapper.UserMapper;
import com.example.trip.service.FlightsService;
import com.example.trip.utils.Result;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightMapper flightMapper;
    @Autowired
    private FlightsService flightsService;

    @RequestMapping("/list")
    public List flightList(){
        return  flightsService.list();
    }

    @RequestMapping("/search")
    public List<Flight> conditionSearch(String startDay, String endDay, String home, String des, String budget ) {
        // match flights
        List<Flight> ret = new ArrayList<>();
        String dest = "";
        Date start = Date.valueOf(startDay); //converting string into sql date
        Date end = Date.valueOf(endDay);
        switch(des) {
            case "melbourne":
                dest = "MEL";
                break;
            case "sydney":
                dest = "SYD";
                break;
            case "brisbane":
                dest = "BNE";
                break;
        }
        QueryWrapper<Flight> flightQuery1 = new QueryWrapper<>(); // go
        QueryWrapper<Flight> flightQuery2 = new QueryWrapper<>(); // return
        flightQuery1.eq("fly_from",home).eq("destination", dest).eq("date", start);
        flightQuery2.eq("fly_from",dest).eq("destination", home).eq("date", end);
        switch(budget) {
            case "lowBudget":
                flightQuery1.orderByAsc("price");
                flightQuery2.orderByAsc("price");
                break;
            case "mediumBudget":
                flightQuery1.ge("departure_time", Time.valueOf("08:41:00")).le("departure_time", Time.valueOf("20:00:00"));
                flightQuery2.ge("departure_time", Time.valueOf("08:41:00")).le("departure_time", Time.valueOf("20:00:00"));
                break;
            case "highBudget":
                flightQuery1.orderByDesc("price");
                flightQuery2.orderByDesc("price");
                break;
        }
        flightQuery1.last("limit 1");
        flightQuery2.last("limit 1");
        ret.addAll(flightsService.list(flightQuery1));
        ret.addAll(flightsService.list(flightQuery2));
        return ret;
    }

//    @PostMapping("/flight")
//    public List searchFlight(@RequestBody Flight flight) { // json map to java object
//        // System.out.println(flight);
//        List<Flight> flights = flightMapper.selectByLocation(flight.getFlyFrom(), flight.getDestination());
//        System.out.println(flights);
//        return flights;
//    }

}
