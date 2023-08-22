package com.example.trip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@CrossOrigin
@RestController
@MapperScan("com/example/trip/mapper")
//@ComponentScan(basePackages = {"com.example.trip.controller","com.example.trip.entity"})
public class TripApplication {


	public static void main(String[] args) {
		SpringApplication.run(TripApplication.class, args);
	}



}
