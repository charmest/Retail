package application;

import metier.I_Produit;

/**
 * 
 * @author Thomas CHARMES
 *
 */
public class ManageProductController extends ParentController {

	public ManageProductController() {
		super();
	}
	
	/**
	 * Ajoute un produit au catalogue
	 * @param produit
	 */
	public void creerProduit(I_Produit produit) {
		if (catalogue.addProduit(produit)) {
			produitDAO.enregistrerProduit(produit);
			System.out.println("Le produit a bien �t� ajout� au catalogue.");
		} else {
			System.out.println("Le produit n'a pas �t� ajout� au catalogue (valeurs non valides).");
		}
	}
	
	/**
	 * Retire un produit du catalogue
	 * @param nom
	 */
	public void supprimerProduit(String nom) {
		if (catalogue.removeProduit(nom)) {
			produitDAO.supprimerProduit(nom);
			System.out.println("Le produit a bien �t� supprim� du catalogue.");
		} else {
			System.out.println("Le produit n'existe pas.");
		}
	}
}
