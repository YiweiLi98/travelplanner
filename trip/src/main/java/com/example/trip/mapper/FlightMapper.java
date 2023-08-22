package com.example.trip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.trip.entity.Flight;
import com.example.trip.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FlightMapper extends BaseMapper<Flight> {
    @Select("select * from flights where fly_from = #{flyFrom} and destination = #{destination} order by price limit 10")
    List<Flight> selectByLocation(String flyFrom, String destination);
}
