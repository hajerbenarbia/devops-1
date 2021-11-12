package tn.esprit.spring.services;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.MissionRepository;

@Service
public class MissionServiceImpl implements IMissionService {

	@Autowired
	MissionRepository missionRepository;

 Logger logger = LoggerFactory.getLogger(MissionServiceImpl.class); 

	

	@Override
	public int ajouterMission(Mission mission) {
		logger.info("Ajouter Mission");
		missionRepository.save(mission);
		logger.info("Mission Added !");
	
	return mission.getId();
}




	public String deleteMissionById(int missionId) {

		if (missionId == 0) {
			logger.error("No Mission exist !");
		
	
		}else {
			Mission mission = missionRepository.findById(missionId).get();
		
		missionRepository.delete(mission);	
		logger.info(" Mission deleted");
		}
		return "Mission deleted";
	}


	public String getMissionNameById(int missionId) {
		
	       Mission mission =null;

			if (missionId == 0) {
				logger.error("No Mission exist !");
			
		
			}else {
			
				mission = missionRepository.findById(missionId).get();
			logger.info(" Mission exist  :" +mission.getName());

			}
			return mission.getName();

		}

}


