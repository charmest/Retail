package application;

import metier.I_Produit;
import metier.Produit;

/**
 * 
 * @author Thomas CHARMES
 *
 */
public class ManageTransactionController extends ParentController {

    public ManageTransactionController() {
        super();
    }

    /**
     * Augmente le stock disponible à l'achat pour le produit "nomProduit"
     * d'une certaine quantité "qteAchetee"
     * @param nomProduit
     * @param qteAchetee
     */
    public void ajouterStock(String nomProduit, int qteAchetee) {
        if (catalogue.acheterStock(nomProduit, qteAchetee)) {
            I_Produit prodExistant = produitDAO.afficher(nomProduit);

            String nom = nomProduit;
            double prix = prodExistant.getPrixUnitaireHT();
            int qte = prodExistant.getQuantite() + qteAchetee;

            produitDAO.modifierProduit(new Produit(nom, prix, qte));
            System.out.println("Le stock du produit contient " + qte + " produits.");
        } else {
            System.out.println("Impossible d'ajouter au stock.");
        }
    }

    /**
     * Réduit le stock disponible à l'achat pour le produit "nomProduit"
     * selon la quantité vendue "qteVendue"
     * @param nomProduit
     * @param qteVendue
     */
    public void enleverStock(String nomProduit, int qteVendue) {
        if (catalogue.vendreStock(nomProduit, qteVendue)) {
            I_Produit prodExistant = produitDAO.afficher(nomProduit);

            String nom = nomProduit;
            double prix = prodExistant.getPrixUnitaireHT();
            int qte = prodExistant.getQuantite() - qteVendue;

            produitDAO.modifierProduit(new Produit(nom, prix, qte));
            System.out.println("Le stock du produit contient " + qte + " produits.");
        } else {
            System.out.println("Impossible d'enlever au stock.");
        }
    }
}
