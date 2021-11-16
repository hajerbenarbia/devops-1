package dto;

import lombok.Data;

@Data
public class EmployeDto {
	private int id;
	private String prenom;
	private String nom;
	private String email;
	private String password;
	private boolean actif;
}
