package tn.esprit.spring.tests;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.EntrepriseServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {
private static final Logger l = (Logger) LogManager.getLogger(EntrepriseServiceImplTest.class);
	
	@Autowired
	EntrepriseServiceImpl es;
	
	@Autowired
	EntrepriseRepository er;
	@Autowired
	DepartementRepository drep;


	@Test
	public void testajout() {
		try {
			l.info("In testajout():");
			Entreprise E = new Entreprise("MICROSOFT","MICROSOFT FRANCE");
			l.info("Creation Entreprise.");
			int id  = es.ajouterEntreprise(E);
			assertNotNull(id);
			l.info("Adding Entreprise.");
			l.info("Id of the added Entreprise : "+id);
			l.info("Deleting Entreprise.");
			es.deleteEntrepriseById(id);
			l.info("Out testajout() without errors.");

		}

		catch (Exception e){ l.error("Erreur dans testAjouterDepartement() : " + e);}

	}

	
	@Test
	public void testAddEntreprise() {
		try {
			l.info("In testAddEntreprise():");
			Entreprise E = new Entreprise("Samsung","EURL");
			l.info("Adding a new  Entreprise.");
			int Id = es.ajouterEntreprise(E);
			l.info("Id of the added Entreprise : "+Id);
			assertNotNull(Id);
			l.info("Deleting Entreprise.");
			es.deleteEntrepriseById(Id);
			l.info("Add Entreprise works");
			l.info("Out testAddEntreprise() without errors .");

		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	@Test
	public void testUpdateEntreprise() {
		try {
			l.info("In testUpdateEntreprise():");
			Entreprise E = new Entreprise("Samsung","EURL");
			l.info("Entreprise Creation .");
			int Id = es.ajouterEntreprise(E);
			l.info("Id of the added Entreprise : "+Id);
			l.info("I want to modify the name of the Entreprise.");
			E.setName("Iphone");
			es.ajouterEntreprise(E);
			Entreprise Ese = es.getEntrepriseById(Id);
			l.info("Testing if the name is updated.");
			assertEquals("Iphone",Ese.getName());
			es.deleteEntrepriseById(Id);
			l.info("Update Entreprise works");
		} 	catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	//test % size of list 
	@Test
	public void testDeleteEntrepriseById_METHOD1() {
		try {
		Entreprise E = new Entreprise("Samsung","EURL");
			l.info("Adding Entreprise  .");
			int Id = es.ajouterEntreprise(E);
			l.info("Id of the added Entreprise : "+Id);
			int lengthBeforeDelete = es.getAllEntreprises().size();
			l.info("Length Before Delete : "+lengthBeforeDelete);
			es.deleteEntrepriseById(Id);
			int lengthAfterDelete = es.getAllEntreprises().size();
			l.info("Length Before Delete : "+lengthAfterDelete);
			assertEquals(lengthBeforeDelete-1 , lengthAfterDelete);
		l.info("Delete Entreprise (%size) works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	//test % existence in DB
	@Test
	public void testDeleteEntrepriseById_METHOD2() {
		try {
			Entreprise E = new Entreprise("Samsung","EURL");
			l.info("Adding Entreprise  .");

			int Id = es.ajouterEntreprise(E);
			l.info("Id of the added Entreprise : "+Id);
			boolean existsBeforeDelete = er.findById(Id).isPresent();
			l.info("Is the enterprise with id "+Id+" exist ? "+existsBeforeDelete);
			l.info("Deleting Entreprise.");
			es.deleteEntrepriseById(Id);
			boolean existsAfterDelete = er.findById(Id).isPresent();
			l.info("Is the enterprise with id "+Id+" exist ? "+ existsAfterDelete);
			assertTrue(existsBeforeDelete);
			assertFalse(existsAfterDelete);
			l.info("Delete Entreprise (%existence) works");
			} catch (NullPointerException e) {
				l.error(e.getMessage());
			}
	}
	
	@Test
	public void testGetEntrepriseById(){
		try {
		Entreprise E = new Entreprise("Samsung","EURL");
			l.info("Adding Entreprise  .");

			int Id = es.ajouterEntreprise(E);
			l.info("Id of the added Entreprise : "+Id);

			assertNotNull(Id);
			l.info("Deleting Entreprise.");

			es.deleteEntrepriseById(Id);
		l.info("Get Entreprise by id works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	@Test
	public void testAffectDepartmentToEntreprise(){
		try {
		l.info("In testAffectDepartmentToEnterprise():");
		l.info("Enterprise Creation");
		Entreprise E = new Entreprise("Oracle","Nothing");
		l.info("Adding New Enterprise.");
		int IdE = es.ajouterEntreprise(E);
		l.info("Department Creation");
		Departement D = new Departement("depTest");
		l.info("Adding New Department.");
		int IdD = es.ajouterDepartement(D);
		l.info("Affecting a Department to Enterprise");
		es.affecterDepartementAEntreprise(IdD, IdE);
		l.info("Getting the department with the id "+IdD);
		Departement D1=drep.findById(IdD).get();
		l.info("D1 "+D1.getEntreprise().getId());
		l.info("Testing if  enterprise_Id of the department is equal to the Id of the affected enterprise");
		assertEquals(D1.getEntreprise().getId(),IdE);
			l.info("Deleting department.");
			es.deleteDepartementById(IdD);
			l.info("Deleting Enterprise.");
			es.deleteEntrepriseById(IdE);
		l.info("Affect Department to Enterprise works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	@Test
	public void testgetAllDepartementsNamesByEntreprise(){
		try {
		Entreprise E = new Entreprise("Samsung","EURL");
		l.info("Adding Entreprise ");
		int IdE = es.ajouterEntreprise(E);
		Departement D = new Departement("Info");
		l.info("Adding New Departement 1 ");
		int IdD = es.ajouterDepartement(D);
		Departement D2 = new Departement("RH");
		l.info("Adding New Departement 2 ");
		int IdD2 = es.ajouterDepartement(D2);
		l.info("Affecting Department 2 to the enterprise 1");
		es.affecterDepartementAEntreprise(IdD2, IdE);
		l.info("Affecting Department 1 to the enterprise 1");
		es.affecterDepartementAEntreprise(IdD, IdE);
		List<String> ExpectedNames = new ArrayList<>();
		l.info("Intialisation of the Expected List");
		ExpectedNames.add(D.getName());
		ExpectedNames.add(D2.getName());
		l.info("the names in the list are "+ExpectedNames);
		l.info("Getting the List of the department names ");
		List<String> names = es.getAllDepartementsNamesByEntreprise(IdE);
		l.info("the names the List of the department names  are "+names);
		l.info("Testing the names are the same");
		assertEquals(ExpectedNames,names);
		l.info("Deleting departement 1.");
		es.deleteDepartementById(IdD);
		l.info("Deleting departement 2.");
		es.deleteDepartementById(IdD2);
			l.info("Deleting enterprise .");
			es.deleteEntrepriseById(IdE);
		l.info("Get Names of Departments by Entreprise works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	
}
