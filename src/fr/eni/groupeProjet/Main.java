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
		
		int nombreParGroupeMaximum = 3;
		
//		int nbGroupe = obtenirTotalGroupeSurPlace(stagiaires, 3);
		System.out.println("Nombre de stagiaires : " + stagiaires.size());
		int nbGroupe = (int) Math.ceil((double) stagiaires.size() / nombreParGroupeMaximum);
		System.out.println("Nombre de groupe : " + nbGroupe);
		
//		Creer les groupes vide
		ArrayList< ArrayList<Stagiaire> > groupe = new ArrayList<>();
		for(int i = 0; i < nbGroupe; i++) {
			groupe.add(new ArrayList<Stagiaire>());
		}
		
//		Liste de fille
		ArrayList<Stagiaire> stagiairesF = new ArrayList<>();
		ArrayList<Stagiaire> stagiairesM = new ArrayList<>();
		for(Stagiaire s : stagiaires) {
			if(s.getSexe() == 'F') {
				stagiairesF.add(s);
//				System.out.println(s);
			} else {
				stagiairesM.add(s);
			}
		}

		System.out.println("Nombre de fille : " + stagiairesF.size());
		groupe = insererStagiaireDansGroupe((ArrayList<Stagiaire>) stagiairesF.clone(), (ArrayList< ArrayList<Stagiaire> >) groupe.clone(), true);
		
//		Liste de CDA
		ArrayList<Stagiaire> stagiairesCDA = new ArrayList<>();
		ArrayList<Stagiaire> stagiairesD2WM = new ArrayList<>();
		for(Stagiaire s : stagiaires) {
			if(s.isCda() == true) {
				stagiairesCDA.add(s);
			} else {
				stagiairesD2WM.add(s);
			}
		}
//		System.out.println("Nombre de CDA : " + stagiairesCDA.size());
		groupe = insererStagiaireDansGroupe((ArrayList<Stagiaire>) stagiairesCDA.clone(), (ArrayList< ArrayList<Stagiaire> >) groupe.clone(), true);
//		groupe = insererStagiaireDansGroupe(stagiairesM, groupe, false);
		
//		DEV verifie les doubons
//		int i = 0;
//		for(Stagiaire f : stagiairesF) {			
//			for(Stagiaire cda : stagiairesCDA) {
//				if(f.equals(cda)) {
//					i++;
//					System.out.println("Doublons : " + f);
//				}
//			}
//		}
//		System.out.println(i + " doublons");
		
//		Affihcher les groupes
		for(ArrayList<Stagiaire> g : groupe) {
			System.out.println(g.size() + " => " + g);
		}
	}
	
	public static int obtenirTotalGroupe(ArrayList<Stagiaire> stagiaires, int nombreParGroupe) {
		System.out.println("Nombre total : " + stagiaires.size());		
//		Nombre de groupe
		return (int) Math.ceil((double) stagiaires.size() / nombreParGroupe);
	}
	public static int obtenirTotalGroupeADistance(ArrayList<Stagiaire> stagiaires, int nombreParGroupe) {
		int nombreDistance = 0;
		for(Stagiaire e : stagiaires) {
			if(e.isaDistance() == true) {
				nombreDistance++;
			}
		}
		System.out.println("Nombre total : " + stagiaires.size());
		System.out.println("Nombre à distance : " + nombreDistance);
		
//		Nombre de groupe
		return (int) Math.ceil((double) nombreDistance / nombreParGroupe);
	}
	public static int obtenirTotalGroupeSurPlace(ArrayList<Stagiaire> stagiaires, int nombreParGroupe) {
		int nombreSurPlace = 0;
		for(Stagiaire e : stagiaires) {
			if(e.isaDistance() == false) {
				nombreSurPlace++;
			}
		}
		System.out.println("Nombre total : " + stagiaires.size());
		System.out.println("Nombre sur place : " + nombreSurPlace);
		
//		Nombre de groupe
		return (int) Math.ceil((double) stagiaires.size() / nombreParGroupe);
	}
	
	
	/**
	 * Prend une liste et les insèrent chacun dans un groupe parmis la liste de groupe possible
	 * 
	 * @param ArrayList<Stagiaire> liste
	 * @param ArrayList< ArrayList<Stagiaire> > groupe
	 * @param booleant sens
	 * @return ArrayList< ArrayList<Stagiaire> > groupeMisAJour
	 */
	public static ArrayList< ArrayList<Stagiaire> > insererStagiaireDansGroupe(ArrayList<Stagiaire> liste, ArrayList< ArrayList<Stagiaire> > groupe, boolean sens){
		if(sens == true) {
//			Tant qu'il y des élément à insérer
			while(liste.size() > 0) {
				for(int g = 0; g < groupe.size(); g++) {
					inserer(g, liste, groupe);
				}
			}
		} else {
//			Tant qu'il y des élément à insérer
			while(liste.size() > 0) {
				for(int g = groupe.size() - 1; g >= 0; g--) {
					inserer(g, liste, groupe);
				}
			}
		}
		
		return groupe;
	}
//	TODO methode renvoyant trop de resultat dans groupe (doublont)
	
	public static void inserer(int g, ArrayList<Stagiaire> liste, ArrayList< ArrayList<Stagiaire> > groupe){
		int rand = 0;
		if(liste.size() > 0) {
			rand = getRandom(liste.size());
//			System.out.println("Rand : " + rand);
//			System.out.println("Taille liste : " + liste.size());

		
//		for(int s = 0; s < liste.size(); s++) {
			
//		Verifie que liste.get(rand) n'est pas déjà dans groupe[g]
			boolean inGroupe = false;
			for(ArrayList<Stagiaire> gr : groupe) {
				if(liste.size() <= 0 || inGroupe == true || rand >= liste.size()) {
					break;
				}
//				Si il reste des element dans la liste
				for(int st = 0; st < gr.size(); st++) {
					if(liste.size() <= 0 || inGroupe == true || rand >= liste.size()) {
						inGroupe = true;
						break;
					}
//					try {							
////						if(st.getNom().equals(liste.get(s).getNom()) ) {
//						if(gr.get(st).equals(liste.get(s))) {
//							if(liste.size() > 0 && s < liste.size()) {
//						BIS: if(gr.contains(liste.get(s)) ) {
//					if(rand < liste.size()) {
						if(gr.get(st).equals(liste.get(rand)) ) {
//							if(gr.contains(liste.get(rand)) ) {
							inGroupe = true;
							System.out.println("Doublons : " + liste.get(rand) );
							liste.remove(rand);
//								System.out.println("in");
////						Retirer l'élément en doublont
//								liste.remove(liste.get(s));
								break;
//							}
						}
//					}
//					} catch(IndexOutOfBoundsException e) {
//						System.out.println("Restant de liste : " + liste );
//					}
				}
//			}
			if(inGroupe == false ) {						
//			Choisir un élément dans la liste
//				int rand = getRandom(liste.size());
//				if(rand < liste.size()) {					
					groupe.get(g).add(liste.get(rand));
//				}
//			Retirer l'élément insérer de la liste
				liste.remove(rand);
//			Arrêter le parcours de la liste
				break;
			}
		}
	}
		
//		return groupe;
	}
	
	public static int getRandom(int max) {
		Random rand = new Random();
		return rand.nextInt(max);
	}
}

