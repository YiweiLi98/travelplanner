package com.example.trip;

import com.example.trip.controller.AccommodationController;
import com.example.trip.controller.FlightController;
import com.example.trip.controller.ThingsTodoController;
import com.example.trip.controller.UserController;
import com.example.trip.service.ThingsTodoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
class TripApplicationTests {
	@Autowired
	UserController userController;
	@Autowired
	ThingsTodoController thingsTodoController;
	@Autowired
	FlightController flightController;
	@Autowired
	AccommodationController accommodationController;

	@Test
	void contextLoads() {
		Assertions.assertThat(userController).isNot(null);
		Assertions.assertThat(thingsTodoController).isNot(null);
		Assertions.assertThat( flightController).isNot(null);
		Assertions.assertThat(accommodationController).isNot(null);
	}

}
