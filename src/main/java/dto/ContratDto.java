package dto;

import java.util.Date;

import lombok.Data;

@Data
public class ContratDto {
	private int reference;
	private Date dateDebut;
	private String typeContrat;
	private float telephone;
	private float salaire;

}
