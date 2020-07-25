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
		stagiaires.add( new Stagiaire("Herv�", 'M', true, true) );
		stagiaires.add( new Stagiaire("Benoit", 'M', false, true) );
		stagiaires.add( new Stagiaire("Amandine", 'F', true, true) );
		stagiaires.add( new Stagiaire("St�phanie", 'F', false, true) );
		stagiaires.add( new Stagiaire("M�lanie", 'F', false, true) );
		stagiaires.add( new Stagiaire("Zo�", 'F', false, true) );
		stagiaires.add( new Stagiaire("Zara", 'F', false, true) );
		
//		System.out.println(obtenirGroupeADistance(stagiaires).size());
//		System.out.println(obtenirGroupeSurPlace(stagiaires).size());
		
		System.out.println("TIRAGE A DISTANCE");
		tirage(obtenirGroupeADistance(stagiaires));
		System.out.println("=====================");
		System.out.println("\nTIRAGE SUR PLACE");
		tirage(obtenirGroupeSurPlace(stagiaires));
		System.out.println("=====================");
		System.out.println("\nTIRAGE GENERAL");
		tirage(stagiaires);
		
		
	}

	/**
	 * Obtenir la liste des stagiaires � distance
	 * 
	 * @param stagiaires : Liste de l'ensemble des stagiaires
	 * 
	 * @return liste des stagiaires � distance
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
	
	/**
	 * Effectue le tirage sur une liste de stagiaires
	 * 
	 * @param stagiaires : liste des stagiaires � tir�e
	 */
	public static void tirage(ArrayList<Stagiaire> stagiaires) {
//		Initialiser les valeurs
		ArrayList<Stagiaire> listeUtilise = (ArrayList<Stagiaire>) stagiaires.clone();
		int nombreParGroupeMaximum = 3;
		int nbGroupe = (int) Math.ceil((double) listeUtilise.size() / nombreParGroupeMaximum);
		
		System.out.println("Nombre de stagiaires : " + listeUtilise.size());
		System.out.println("Nombre de groupes : " + nbGroupe);
		
		
//		Creer l'ensemble de groupes vide
		ArrayList< ArrayList<Stagiaire> > groupes = new ArrayList<>();
		for(int i = 0; i < nbGroupe; i++) {
			groupes.add(new ArrayList<Stagiaire>());
		}
		
//		Liste de fille
		ArrayList<Stagiaire> stagiairesF = new ArrayList<>();
		ArrayList<Stagiaire> stagiairesM = new ArrayList<>();
		for(Stagiaire s : listeUtilise) {
			if(s.getSexe() == 'F') {
				stagiairesF.add(s);
			} else {
				stagiairesM.add(s);
			}
		}

//		Liste de CDA
		ArrayList<Stagiaire> stagiairesCDA = new ArrayList<>();
		ArrayList<Stagiaire> stagiairesD2WM = new ArrayList<>();
		for(Stagiaire s : listeUtilise) {
			if(s.isCda() == true) {
				stagiairesCDA.add(s);
			} else {
				stagiairesD2WM.add(s);
			}
		}
		
//		Insertion & �quilibrage
		groupes = insererStagiaireDansGroupe(stagiairesCDA, groupes, false);
		groupes = insererStagiaireDansGroupe(stagiairesF, groupes, true);
		groupes = insererStagiaireDansGroupe(stagiairesM, groupes, false);
		groupes = insererStagiaireDansGroupe(stagiairesD2WM, groupes, true);
		
		groupes = equilibrerGroupes(groupes, nombreParGroupeMaximum);
		
		afficherGroupes(groupes, 1);
	}
	
	/**
	 * Afficher les enregistrements pr�sent dans les groupes
	 * 
	 * @param groupes
	 * @param mode : 
	 * 		[0] mode d�taill� avec tailles des groupes et detaille staigiaire
	 * 		[1] mode simple avec nom des stagiaires
	 */
	public static void afficherGroupes(ArrayList< ArrayList<Stagiaire> > groupes, int mode) {
		StringBuilder res = new StringBuilder("");
		int i = 1;
		for(ArrayList<Stagiaire> g : groupes) {
			if(mode == 0) {
				System.out.println(g.size() + " => " + g);
			} else {
				res.append("\nGroupe " + i + " : ");
				for(Stagiaire s : g) {
					res.append(s.getNom() + "  ");
				}
				i++;
			}
			

		}
		System.out.println(res.toString());
		System.out.println("");
	}
	
	/**
	 * Prend une liste et les ins�rent chacun dans un groupes parmis la liste de groupes possible
	 * 
	 * @param liste : liste � ins�rer dans le groupes point�
	 * @param groupes : Ensemble de tout les groupes
	 * @param sens : Commencer les insertion du d�but, ou de la fin
	 * 
	 * @return un ensemble groupes mis � jour
	 */
	public static ArrayList< ArrayList<Stagiaire> > insererStagiaireDansGroupe(ArrayList<Stagiaire> liste, ArrayList< ArrayList<Stagiaire> > groupes, boolean sens){
		ArrayList< ArrayList<Stagiaire> > resultGroupe = (ArrayList<ArrayList<Stagiaire>>) groupes.clone();
		ArrayList<Stagiaire> tempListe = verifierDoublon(liste, groupes);;
		
		if(sens == true) {
//			Tant qu'il y des �l�ment � ins�rer
			while(tempListe.size() > 0) {
				for(int g = 0; g < resultGroupe.size(); g++) {
					inserer(g, tempListe, resultGroupe);
				}
			}
		} else {
//			Tant qu'il y des �l�ment � ins�rer
			while(tempListe.size() > 0) {
				for(int g = resultGroupe.size() - 1; g >= 0; g--) {
					inserer(g, tempListe, resultGroupe);
				}
			}
		}
		
		return resultGroupe;
	}
	
	/**
	 * Verifie les donn�es d�j� pr�sente dans les groupes
	 * 
	 * @param liste : liste � ins�rer � v�rifier
	 * @param  groupes : Ensemble de tout les groupes
	 * @return une liste sans doublons
	 */
	public static ArrayList<Stagiaire> verifierDoublon(ArrayList<Stagiaire> liste, ArrayList< ArrayList<Stagiaire> > groupes) {
		ArrayList<Stagiaire> result = (ArrayList<Stagiaire>) liste.clone();
		for(Stagiaire s : liste) {
			for(ArrayList<Stagiaire> gr : groupes) {
				if(gr.contains(s) ) {
					result.remove(s);
//					System.out.println("Doublons : " + s );
				}
			}
		}
		return result;
	}
	
	/**
	 * Inserer les valeurs d'une liste dans le groupes point� par le curseur
	 * 
	 * /!\ Effet de bord : liste, groupes
	 * @param g : Curseur de distribution pointant sur un groupes
	 * @param liste : liste � ins�rer dans le groupes point�
	 * @param groupes : Ensemble de tout les groupes
	 */
	public static void inserer(int g, ArrayList<Stagiaire> liste, ArrayList< ArrayList<Stagiaire> > groupes){
		int rand = 0;
		if(liste.size() > 0) {
			rand = getRandom(liste.size());
			groupes.get(g).add(liste.get(rand));
			liste.remove(rand);
		}
	}

	/**
	 * Equilibre la tailles des groupes
	 * 
	 * @param groupes : Ensemble de tout les groupes
	 * @param tailleMax : Taille maximal par groupe souhait�
	 * @return un ensemble groupes mis � jour
	 */
	public static ArrayList< ArrayList<Stagiaire> > equilibrerGroupes(ArrayList< ArrayList<Stagiaire> > groupes, int tailleMax){
		ArrayList< ArrayList<Stagiaire> > resultGroupes = (ArrayList<ArrayList<Stagiaire>>) groupes.clone();
		
		ArrayList<Stagiaire> lePlusGrand = new ArrayList<>();
		ArrayList<Stagiaire> lePlusPetit = new ArrayList<>();
		do {
				
	//		Determiner le plus grand et le plus petit groupe
			int indexLePlusGrand = 0;
			int indexLePlusPetit = 0;
			int max = 0;
			int min = 100;
			
			for(ArrayList<Stagiaire> g : resultGroupes) {
				if(g.size() >= max) {				
					lePlusGrand = g;
					max = lePlusGrand.size();
				}
				if(g.size() <= min) {				
					lePlusPetit = g;
					min = lePlusPetit.size();
				}
			}
			indexLePlusGrand = resultGroupes.indexOf(lePlusGrand);
			indexLePlusPetit = resultGroupes.indexOf(lePlusPetit);
			
	//		Equilibrer entre le plus grand et le plus petit
			lePlusPetit.add( lePlusGrand.get(lePlusGrand.size() - 1) );
			lePlusGrand.remove(lePlusGrand.size() - 1);
			
	//		Ajoute les modification aux groupes
			resultGroupes.set(indexLePlusGrand, lePlusGrand);
			resultGroupes.set(indexLePlusPetit, lePlusPetit);
			
		} while(lePlusGrand.size() >= tailleMax);
		
		return resultGroupes;
	}
	
	
	/**
	 * Obtenir un nombre al�atoire entre 0 et max
	 * 
	 * @param max : Valeur maximal
	 * @return un nombre al�atoire
	 */
	public static int getRandom(int max) {
		Random rand = new Random();
		return rand.nextInt(max);
	}
}

