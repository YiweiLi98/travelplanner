package com.example.trip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.trip.entity.Accommodation;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationMapper extends BaseMapper<Accommodation> {

}
