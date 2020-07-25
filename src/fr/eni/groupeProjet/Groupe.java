package fr.eni.groupeProjet;

import java.util.ArrayList;

public class Groupe {
	/**
	 * Obtenir la liste des stagiaires à distance
	 * 
	 * @param stagiaires : Liste de l'ensemble des stagiaires
	 * 
	 * @return liste des stagiaires à distance
	 */
	public static ArrayList<Stagiaire> obtenirGroupeADistance(ArrayList<Stagiaire> stagiaires) {
		ArrayList<Stagiaire> resultatStagiaires = new ArrayList<>();
		for(Stagiaire e : stagiaires) {
			if(e.isaDistance() == true) {
				resultatStagiaires.add(e);
			}
		}
		return resultatStagiaires;
	}
	
	/**
	 * Obtenir la liste des stagiaires sur place
	 * 
	 * @param stagiaires : Liste de l'ensemble des stagiaires
	 * 
	 * @return liste des stagiaires sur place
	 */
	public static ArrayList<Stagiaire> obtenirGroupeSurPlace(ArrayList<Stagiaire> stagiaires) {
		ArrayList<Stagiaire> resultatStagiaires = new ArrayList<>();
		for(Stagiaire e : stagiaires) {
			if(e.isaDistance() == false) {
				resultatStagiaires.add(e);
			}
		}
		return resultatStagiaires;
	}
}