package application;

import java.util.ArrayList;
import java.util.List;

import metier.Catalogue;
import metier.FabriqueProduitDAO;
import metier.I_Produit;
import metier.ProduitDAO;

/**
 * 
 * @author Thomas CHARMES
 *
 */
public class ParentController {

	protected ProduitDAO produitDAO;
	protected Catalogue catalogue;

	public ParentController() {
		FabriqueProduitDAO fp = FabriqueProduitDAO.getInstance();
		// this.produitDAO = fp.createProduitBDR();
		this.produitDAO = fp.createProduitXML();

		this.catalogue = new Catalogue();
		List<I_Produit> l = new ArrayList<I_Produit>();
		l.addAll(this.produitDAO.afficherTout());
		this.catalogue.addProduits(l);
	}
}
