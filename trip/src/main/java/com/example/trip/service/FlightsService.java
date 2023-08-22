package com.example.trip.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.trip.entity.Flight;
import com.example.trip.mapper.FlightMapper;
import org.springframework.stereotype.Service;

@Service
public class FlightsService extends ServiceImpl<FlightMapper, Flight> {
}
