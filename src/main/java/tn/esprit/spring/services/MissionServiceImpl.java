package tn.esprit.spring.services;






import java.util.Optional;

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
	public Mission ajouterMission(Mission mission) {
		logger.info("Ajouter Mission");
		missionRepository.save(mission);
		logger.info("Mission Added !");
	
	return mission;
}

	public Boolean updateMissionByName( String name, int missionId) {
		logger.info("Updating Mission Name");
		
		Optional<Mission> mission = missionRepository.findById(missionId);

            if(mission.isPresent()) {
            	
            	Mission mission1=	mission.get();
            
            if (name != "") {
            	mission1.setName(name);
            	missionRepository.save(mission1);
        		logger.info("Name Updated Succefully !");
        		
        		return true;
        		}else {
        			logger.error("The new Name Must not Be Empty !");
        			return false;

        			
        		}

            }
return true;
	}
	

	public String deleteMissionById(int missionId) {

		if (missionId == 0) {
			logger.error("No Mission exist !");
		
	
		}else {
			Mission mission = missionRepository.findById(missionId).orElse(null);
		
		missionRepository.delete(mission);	
		logger.info(" Mission deleted");
		}
		return "Mission deleted";
	}


	public String getMissionNameById(int missionId) {
		
		Optional<Mission> mission =missionRepository.findById(missionId);
		
			if (!mission.isPresent()) {
				logger.error("No Mission exist !");
			
		return "No mission exist";
			}else {
			
				Mission mission1 = mission.get();
				
				logger.info(" Mission exist : {}", mission1.getName());
				
				return mission1.getName();


			}

		}


	

}


