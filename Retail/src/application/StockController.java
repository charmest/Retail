package application;

import java.util.List;

import metier.I_Produit;

/**
 * 
 * @author Thomas CHARMES
 *
 */
public class StockController extends ParentController {

	public StockController() {
		super();
	}

	/**
	 * @return pour chaque produit du catalogue
	 * son prix et sa quantité disponible en stock
	 */
	public String etatStock() {
		for (I_Produit prod : getListProduits()) {
			catalogue.addProduit(prod);
		}
		return catalogue.toString();
	}

	/**
	 * @return la liste de tous les produits
	 */
	public List<I_Produit> getListProduits() {
		return this.produitDAO.afficherTout();
	}
}
