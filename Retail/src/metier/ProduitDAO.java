package metier;

import java.util.List;

/**
 * 
 * @author Thomas CHARMES
 *
 */
public interface ProduitDAO {

    public boolean enregistrerProduit(I_Produit produit);
    public boolean modifierProduit(I_Produit produit);
    public boolean supprimerProduit(String nom);
    public I_Produit afficher(String nom);
    public List<I_Produit> afficherTout();
}