package fr.eni.groupeProjet;

import java.util.ArrayList;
import java.util.Random;

public class Tirage {
	ArrayList<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
	
	public Tirage(ArrayList<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}
	
	/**
	 * Effectue le tirage sur une liste de stagiaires
	 * 
	 * @param stagiaires : liste des stagiaires à tirée
	 */
	public void tirage() {
//		Initialiser les valeurs
		ArrayList<Stagiaire> listeUtilise = (ArrayList<Stagiaire>) this.stagiaires.clone();
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
		
		if(listeUtilise.size() > 0) {			
//			Insertion & équilibrage
			groupes = insererStagiaireDansGroupe(stagiairesCDA, groupes, false);
			groupes = insererStagiaireDansGroupe(stagiairesF, groupes, true);
			groupes = insererStagiaireDansGroupe(stagiairesM, groupes, false);
			groupes = insererStagiaireDansGroupe(stagiairesD2WM, groupes, true);
			
			groupes = equilibrerGroupes(groupes, nombreParGroupeMaximum);
			
			afficherGroupes(groupes, 1);
		} else {
			System.out.println("\nAucun élément à tiré.\n");
		}
	}
	
	/**
	 * Afficher les enregistrements présent dans les groupes
	 * 
	 * @param groupes : Ensemble de tout les groupes
	 * @param mode : 
	 * 		[0] mode détaillé avec tailles des groupes et detaille staigiaire
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
	 * Prend une liste et les insèrent chacun dans un groupes parmis la liste de groupes possible
	 * 
	 * @param liste : Liste à insérer dans le groupes pointé
	 * @param groupes : Ensemble de tout les groupes
	 * @param sens : Commencer les insertions à partir du début, ou de la fin
	 * 
	 * @return un ensemble groupes mis à jour
	 */
	private ArrayList< ArrayList<Stagiaire> > insererStagiaireDansGroupe(ArrayList<Stagiaire> liste, ArrayList< ArrayList<Stagiaire> > groupes, boolean sens){
		ArrayList< ArrayList<Stagiaire> > resultGroupe = (ArrayList<ArrayList<Stagiaire>>) groupes.clone();
		ArrayList<Stagiaire> tempListe = verifierDoublon(liste, groupes);;
		
		if(sens == true) {
//			Tant qu'il y des élément à insérer
			while(tempListe.size() > 0) {
				for(int g = 0; g < resultGroupe.size(); g++) {
					inserer(g, tempListe, resultGroupe);
				}
			}
		} else {
//			Tant qu'il y des élément à insérer
			while(tempListe.size() > 0) {
				for(int g = resultGroupe.size() - 1; g >= 0; g--) {
					inserer(g, tempListe, resultGroupe);
				}
			}
		}
		
		return resultGroupe;
	}
	
	/**
	 * Verifie les données déjà présente dans les groupes
	 * 
	 * @param liste : Liste à insérer à vérifier
	 * @param  groupes : Ensemble de tous les groupes
	 * @return une liste sans doublons
	 */
	private ArrayList<Stagiaire> verifierDoublon(ArrayList<Stagiaire> liste, ArrayList< ArrayList<Stagiaire> > groupes) {
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
	 * Inserer les valeurs d'une liste dans le groupes pointé par le curseur
	 * 
	 * /!\ Effet de bord : liste, groupes
	 * @param g : Curseur de distribution pointant sur un groupes
	 * @param liste : liste à insérer dans le groupes pointé
	 * @param groupes : Ensemble de tous les groupes
	 */
	private void inserer(int g, ArrayList<Stagiaire> liste, ArrayList< ArrayList<Stagiaire> > groupes){
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
	 * @param tailleMax : Taille maximal par groupe souhaité
	 * @return un ensemble de groupes mis à jour
	 */
	private ArrayList< ArrayList<Stagiaire> > equilibrerGroupes(ArrayList< ArrayList<Stagiaire> > groupes, int tailleMax){
		ArrayList< ArrayList<Stagiaire> > resultGroupes = (ArrayList<ArrayList<Stagiaire>>) groupes.clone();
		
		ArrayList<Stagiaire> lePlusGrand = new ArrayList<>();
		ArrayList<Stagiaire> lePlusPetit = new ArrayList<>();
		boolean stop = false;
		do {
	//		Determiner le plus grand et le plus petit groupe
			int indexLePlusGrand = 0;
			int indexLePlusPetit = 0;
			int max = 0;
			int min = 100;
			
			for(ArrayList<Stagiaire> g : resultGroupes) {
				if(g.size() > max) {				
					lePlusGrand = g;
					max = lePlusGrand.size();
				}
				if(g.size() < min) {				
					lePlusPetit = g;
					min = lePlusPetit.size();
				}
			}
			indexLePlusGrand = resultGroupes.indexOf(lePlusGrand);
			indexLePlusPetit = resultGroupes.indexOf(lePlusPetit);
			
			if(lePlusGrand.size() != lePlusPetit.size()) {
		//		Equilibrer entre le plus grand et le plus petit
				lePlusPetit.add( lePlusGrand.get(lePlusGrand.size() - 1) );
				lePlusGrand.remove(lePlusGrand.size() - 1);
				
		//		Ajoute les modification aux groupes
				resultGroupes.set(indexLePlusGrand, lePlusGrand);
				resultGroupes.set(indexLePlusPetit, lePlusPetit);
			} else {
				stop = true;
			}
			
		} while(lePlusGrand.size() >= tailleMax && stop == false);
		
		return resultGroupes;
	}
	
	
	/**
	 * Obtenir un nombre aléatoire entre 0 et max
	 * 
	 * @param max : Valeur maximal
	 * @return un nombre aléatoire
	 */
	private int getRandom(int max) {
		Random rand = new Random();
		return rand.nextInt(max);
	}
}
