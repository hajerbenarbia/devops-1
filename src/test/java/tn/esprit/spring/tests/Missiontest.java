package tn.esprit.spring.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.MissionServiceImpl;

@SpringBootTest
public class Missiontest{
	
	
	@Autowired
	MissionServiceImpl missionServiceImpl;
	
	
	/**@Test
	void deleteMissionById() {
		
		missionServiceImpl.deleteMissionById(6);
		assertNull("Mission deleted");
		
	}**/

	
	@Test
	void getMissionNameById() {
		
		String name = missionServiceImpl.getMissionNameById(7);
		
		org.junit.jupiter.api.Assertions.assertFalse(name=="");
		org.junit.jupiter.api.Assertions.assertTrue(name!="");
	}
	
	
	@Test
	void addMission() {
		
	  Mission m = missionServiceImpl.ajouterMission(new Mission("mannouch","ma7lazinha"));
		
	  assertThat(m.getId()).isGreaterThan(0);
		
	
	}
	
	@Test
	void updateMissionByName() {
		
	
	Boolean Mission  =	 missionServiceImpl.updateMissionByName("salwa", 6);
		
	org.junit.jupiter.api.Assertions.assertFalse(Mission==false);
	org.junit.jupiter.api.Assertions.assertTrue(Mission!=false);
	}
	
	

}
