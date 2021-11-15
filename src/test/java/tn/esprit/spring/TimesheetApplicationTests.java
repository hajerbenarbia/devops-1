package tn.esprit.spring;


import static org.junit.Assert.assertTrue;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.MissionServiceImpl;


@SpringBootTest
class TimesheetApplicationTests {

	@Test
	void contextLoads() {
	}
	
	Mission M;
		
	@Autowired
	MissionServiceImpl missionServiceImpl;
	
	

	

}
