package fr.eni.groupeProjet;

import java.util.ArrayList;
import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		ArrayList<Stagiaire> stagiaires = new ArrayList<>();
		stagiaires.add( new Stagiaire("John", 'M', true, false) );
		stagiaires.add( new Stagiaire("Max", 'M', true, false) );
		stagiaires.add( new Stagiaire("Bob", 'M', false, false) );
		stagiaires.add( new Stagiaire("Anne", 'F', true, false) );
		stagiaires.add( new Stagiaire("Elodie", 'F', false, false) );
		stagiaires.add( new Stagiaire("Marie", 'F', false, false) );
		stagiaires.add( new Stagiaire("Jack", 'M', true, false) );
		stagiaires.add( new Stagiaire("Marc", 'M', true, true) );
		stagiaires.add( new Stagiaire("Martin", 'M', true, true) );
		stagiaires.add( new Stagiaire("Boby", 'M', true, true) );
		stagiaires.add( new Stagiaire("Hervé", 'M', true, true) );
		stagiaires.add( new Stagiaire("Benoit", 'M', false, true) );
		stagiaires.add( new Stagiaire("Amandine", 'F', true, true) );
		stagiaires.add( new Stagiaire("Stéphanie", 'F', false, true) );
		stagiaires.add( new Stagiaire("Mélanie", 'F', false, true) );
		stagiaires.add( new Stagiaire("Zoé", 'F', false, true) );
		stagiaires.add( new Stagiaire("Zara", 'F', false, true) );
		
		System.out.println("TIRAGE A DISTANCE");
		new Tirage(Groupe.obtenirGroupeADistance(stagiaires)).tirage();
		System.out.println("=====================");
		System.out.println("\nTIRAGE SUR PLACE");
		new Tirage(Groupe.obtenirGroupeSurPlace(stagiaires)).tirage();
		System.out.println("=====================");
		System.out.println("\nTIRAGE GENERAL");
		new Tirage(stagiaires).tirage();
	}
}

