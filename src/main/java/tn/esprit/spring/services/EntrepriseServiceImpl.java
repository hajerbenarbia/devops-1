package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	public List<Entreprise> getAllEntreprises(){
		return (List<Entreprise>) entrepriseRepoistory.findAll();
	}

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
		Optional<Entreprise> optionalEntreprise=entrepriseRepoistory.findById(entrepriseId);
		if (optionalEntreprise.isPresent()){
				Entreprise entrepriseManagedEntity = optionalEntreprise.get();
				Optional<Departement> optionalDepartement =deptRepoistory.findById(depId);
				if (optionalDepartement.isPresent()){
				Departement depManagedEntity = optionalDepartement.get();
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
				}
		}
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Optional<Entreprise> optionalEntreprise = entrepriseRepoistory.findById(entrepriseId);
		if(optionalEntreprise.isPresent()) {
			Entreprise entrepriseManagedEntity = optionalEntreprise.get();
			List<String> depNames = new ArrayList<>();
			for (Departement dep : entrepriseManagedEntity.getDepartements()) {
				depNames.add(dep.getName());
			}

			return depNames;
		}
		else return   new ArrayList<>();
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		Optional<Entreprise> optionalEntreprise =entrepriseRepoistory.findById(entrepriseId);
		optionalEntreprise.ifPresent(entreprise -> entrepriseRepoistory.delete(entreprise));
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		Optional<Departement> optionalDepartement =deptRepoistory.findById(depId);
		optionalDepartement.ifPresent(departement -> deptRepoistory.delete(departement));
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		Optional<Entreprise> optionalEntreprise =entrepriseRepoistory.findById(entrepriseId);
		return optionalEntreprise.orElse(null);
	}

}
