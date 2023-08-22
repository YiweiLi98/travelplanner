package com.example.trip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users_accommodation")
public class Accommodation {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer price;
    private String cityName;
    private String img;
}
