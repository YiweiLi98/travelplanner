package com.example.trip.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.util.Date;

@TableName("flights")
public class Flight {
//    @TableId(value="id")
//    private Integer id;

    private String flyFrom;

    private String destination;

    private String airline;

    private String flight;

    @JsonFormat(timezone = "GMT+11",pattern = "yyyy/MM/dd")
    private Date date;

    @JsonFormat(timezone = "GMT+11", pattern = "HH:mm:ss")
    private Time departureTime;

    @JsonFormat(timezone = "GMT+11", pattern = "HH:mm:ss")
    private Time arrivalTime;

    private Integer price;

    @TableField(exist = false)
    private String img="https://cdn-icons-png.flaticon.com/512/3125/3125713.png";//"src/main/resources/static/departures.png";

    public String getFlyFrom() {
        return flyFrom;
    }

    public void setFlyFrom(String flyFrom) {
        this.flyFrom = flyFrom;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flyFrom='" + flyFrom + '\'' +
                ", destination='" + destination + '\'' +
                ", airline='" + airline + '\'' +
                ", flight='" + flight + '\'' +
                ", date=" + date +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", price=" + price +
                '}';
    }
}
