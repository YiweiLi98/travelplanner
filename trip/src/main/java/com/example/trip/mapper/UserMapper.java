package com.example.trip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.trip.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    // find a user
    @Select("select * from user where username = #{username}")
    User selectByName(String username);



    @Select("SELECT * from tripplanner.user")
    List<User> findAll();

    @Insert("INSERT INTO tripplanner.user VALUES (#{id},#{username},#{firstname},#{lastname},#{password},#{avatar},#{age},#{email})")
    int insert(User user);


}
