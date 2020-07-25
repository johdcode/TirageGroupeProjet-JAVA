package fr.eni.groupeProjet;

public class Stagiaire {
	
	private String nom;
	private char sexe;
	private boolean cda;
	private boolean aDistance;
	
	public Stagiaire(String nom, char sexe, boolean cda, boolean aDistance) {
		this.nom = nom;
		this.sexe = sexe;
		this.cda = cda;
		this.setaDistance(aDistance);
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public char getSexe() {
		return sexe;
	}

	public void setSexe(char sexe) {
		this.sexe = sexe;
	}

	public boolean isCda() {
		return cda;
	}

	public void setCda(boolean cda) {
		this.cda = cda;
	}
	
	public String toString() {
		return "Stagiaire [nom = " + this.nom + "] [sexe = " + String.valueOf(sexe) + "] [cda = " + String.valueOf(cda) + "]";
	}
	public boolean isaDistance() {
		return aDistance;
	}
	public void setaDistance(boolean aDistance) {
		this.aDistance = aDistance;
	}

}
