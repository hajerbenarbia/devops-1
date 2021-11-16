package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	 Logger logger = LoggerFactory.getLogger(EntrepriseServiceImpl.class);

	
	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
		Optional<Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);
		Optional<Departement> depManagedEntity = deptRepoistory.findById(depId);
		if ((entrepriseManagedEntity.isPresent()) && (depManagedEntity.isPresent())) {
		Entreprise entreprise = entrepriseManagedEntity.get();
		Departement departement = depManagedEntity.get();
				
				departement.setEntreprise(entreprise);
				deptRepoistory.save(departement);
		
	}}
	
	/**public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Optional<Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);
		if(!entrepriseManagedEntity.isPresent()) {
			logger.error("No Entreprise exist !");
			
		}else {
		
		Entreprise entreprise =entrepriseManagedEntity.get();
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entreprise.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames;
	}**/
	
	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		Optional <Entreprise> entrprise= entrepriseRepoistory.findById(entrepriseId);
		if(entrprise.isPresent()) {
		Entreprise entrep =entrprise.get();
		entrepriseRepoistory.delete(entrep);	
	}}

	@Transactional
	public void deleteDepartementById(int depId) {
		Optional <Departement> departement= deptRepoistory.findById(depId);
		if(departement.isPresent()) {
			Departement deprt =departement.get();
			deptRepoistory.delete(deprt);
		}	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		Optional<Entreprise> entr =entrepriseRepoistory.findById(entrepriseId);	
		if (!entr.isPresent()) {
			logger.error("No Entreprise exist !");
			
		}else {
		Entreprise entreprise = entr.get();
		logger.info(" entreprise exist ");
		
		return entreprise;
	}
		return null;
		}
}
