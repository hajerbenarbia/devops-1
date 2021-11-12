package tn.esprit.spring.tests;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.MissionServiceImpl;


@SpringBootTest
public class Missiontest {
	
	Mission M;
	@Autowired
	MissionServiceImpl missionServiceImpl;
	
	
	@Test
	void deleteMissionById() {
		System.out.println("Running testDelete...");
		
		missionServiceImpl.deleteMissionById(0);
		assertNull("Mission deleted");
		
	}

	
	@Test
	void getMissionNameById() {
		
		String name = missionServiceImpl.getMissionNameById(1);
		
		assertTrue("error", name == null);
		assertTrue("succefully", name != null);
	}
	
	@Test
	void addMission() {
		
		int idMiss;
	  idMiss = missionServiceImpl.ajouterMission(new Mission("imen","benabdlaziz"));
		
	  assertTrue("error", idMiss == 0);
		assertTrue("succefully", idMiss != 0);
		
		
		
	}

}
