package metier;

import java.util.List;

/**
 * 
 * @author Thomas CHARMES
 *
 */
public class AdaptateurProduitDAO_XML implements ProduitDAO {

    private ProduitDAO_XML produitDAO_XML;

    public AdaptateurProduitDAO_XML() {
        produitDAO_XML = new ProduitDAO_XML();
    }

    @Override
    public boolean enregistrerProduit(I_Produit produit) {
        return produitDAO_XML.creer(produit);
    }

    @Override
    public boolean modifierProduit(I_Produit produit) {
        return produitDAO_XML.maj(produit);
    }

    @Override
    public boolean supprimerProduit(String nomProduit) {
        I_Produit prod = produitDAO_XML.lire(nomProduit);
        String nom = nomProduit;
        double prix = prod.getPrixUnitaireHT();
        int qte = prod.getQuantite();
        return produitDAO_XML.supprimer(new Produit(nom,prix,qte));
    }

    @Override
    public I_Produit afficher(String nom) {
        return produitDAO_XML.lire(nom);
    }

    @Override
    public List<I_Produit> afficherTout() {
        return produitDAO_XML.lireTous();
    }

}