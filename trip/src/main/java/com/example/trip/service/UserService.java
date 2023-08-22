package com.example.trip.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.trip.entity.User;
import com.example.trip.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {
}
