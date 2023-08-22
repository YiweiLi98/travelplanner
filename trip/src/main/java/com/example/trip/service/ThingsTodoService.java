package com.example.trip.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.trip.entity.ThingsTodo;
import com.example.trip.mapper.ThingsTodoMapper;
import org.springframework.stereotype.Service;

@Service
public class ThingsTodoService extends ServiceImpl<ThingsTodoMapper,ThingsTodo> {
}
