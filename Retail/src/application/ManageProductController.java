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
			System.out.println("Le produit a bien été ajouté au catalogue.");
		} else {
			System.out.println("Le produit n'a pas été ajouté au catalogue (valeurs non valides).");
		}
	}
	
	/**
	 * Retire un produit du catalogue
	 * @param nom
	 */
	public void supprimerProduit(String nom) {
		if (catalogue.removeProduit(nom)) {
			produitDAO.supprimerProduit(nom);
			System.out.println("Le produit a bien été supprimé du catalogue.");
		} else {
			System.out.println("Le produit n'existe pas.");
		}
	}
}
