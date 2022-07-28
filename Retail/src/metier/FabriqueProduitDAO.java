package metier;

/**
 * 
 * @author Thomas CHARMES
 *
 */
public class FabriqueProduitDAO {

    private static FabriqueProduitDAO instance;

    public ProduitDAO_BDR createProduitBDR() {
        return new ProduitDAO_BDR();
    }

    public AdaptateurProduitDAO_XML createProduitXML() {
        return new AdaptateurProduitDAO_XML();
    }

    public static synchronized FabriqueProduitDAO getInstance() {
        if (instance == null) {
            instance = new FabriqueProduitDAO();
        }
        return instance;
    }

}