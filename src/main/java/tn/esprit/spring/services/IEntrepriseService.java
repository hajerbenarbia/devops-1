package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;

public interface IEntrepriseService {
	List<Entreprise> getAllEntreprises();

	int ajouterEntreprise(Entreprise entreprise);
	int ajouterDepartement(Departement dep);
	void affecterDepartementAEntreprise(int depId, int entrepriseId);
	List<String> getAllDepartementsNamesByEntreprise(int entrepriseId);
	void deleteEntrepriseById(int entrepriseId);
	void deleteDepartementById(int depId);
	Entreprise getEntrepriseById(int entrepriseId);
}
