package com.example.trip.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * things_todo
 * @author
 */
@Data
@TableName(value = "things_todo")
public class ThingsTodo  {
    @TableId(type=IdType.AUTO)
    private Integer id;
    @TableField(value = "name_")
    private String name;
    @TableField(value = "img_")
    private String img;
    @TableField(value = "desc_")
    private String desc;
    @TableField(value = "address_")
    private String address;
    @TableField(value = "city_")
    private String city;
    @TableField(value = "type_")
    private String type;
    @TableField(value = "price_")
    private String price;
}
