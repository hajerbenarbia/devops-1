package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Mission implements Serializable {

	private static final long serialVersionUID = -5369734855993305723L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@ManyToOne
	private Departement departement;
	
	@OneToMany(mappedBy="mission")
	private  List<Timesheet> timesheets;
	
	public Mission() {
		super();
	}

	public Mission(String name, String description){
		this.name = name;
		this.description = description;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String setName(String name) {
	return	this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public List<Timesheet> getTimesheets() {
		return timesheets;
	}

	public void setTimesheets(List<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}

	@Override
	public String toString() {
		return "Mission [id=" + id + ", name=" + name + ", description=" + description + ", departement=" + departement
				+ ", timesheets=" + timesheets + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getDescription()=" + getDescription() + ", getDepartement()=" + getDepartement()
				+ ", getTimesheets()=" + getTimesheets() + "]";
	}

	

	
	
	

}
