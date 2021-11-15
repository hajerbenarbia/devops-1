package tn.esprit.spring.services;

import tn.esprit.spring.entities.Mission;

public interface IMissionService {
	
	public Mission ajouterMission(Mission mission);
	public String deleteMissionById(int missionId);
	public String getMissionNameById(int missionId);
	public Boolean updateMissionByName( String name, int missionId);
	}
