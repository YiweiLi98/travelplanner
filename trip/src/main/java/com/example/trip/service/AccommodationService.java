package com.example.trip.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.trip.entity.Accommodation;
import com.example.trip.mapper.AccommodationMapper;
import org.springframework.stereotype.Service;

@Service
public class AccommodationService extends ServiceImpl<AccommodationMapper,Accommodation> {
}
