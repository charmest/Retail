package metier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Thomas CHARMES
 *
 */
public class ProduitDAO_BDR implements ProduitDAO {

	private Connection cn;
	private Statement st;
	private ResultSet rs;

	public ProduitDAO_BDR() {
		String driver, url, login, mdp;
		driver = "oracle.jdbc.driver.OracleDriver";
		url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
		login = "charmest";
		mdp = "1110207242L";
		try {
			Class.forName(driver);
			this.cn = DriverManager.getConnection(url, login, mdp);
			this.st = this.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean enregistrerProduit(I_Produit produit) {
		try {	
			this.st.executeUpdate(
				"call insertValues('" +
					produit.getNom() + "'," + 
					produit.getPrixUnitaireHT() + "," + 
					produit.getQuantite() +
				")"
			);
			return true;
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		return false;
	}

	@Override
	public boolean modifierProduit(I_Produit produit) {
		try {
			// this.st.executeUpdate("UPDATE Produit SET prixUnitaireHT = " + produit.getPrixUnitaireHT() + " WHERE nom = '" + produit.getNom() + "'");
			this.st.executeUpdate("UPDATE Produit SET quantiteStock = " + produit.getQuantite() + " WHERE nom = '" + produit.getNom() + "'");
			return true;
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		return false;
	}

	@Override
	public boolean supprimerProduit(String nom) {
		try {	
			this.st.executeUpdate("DELETE FROM Produit WHERE nom = '" + nom + "'");
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public I_Produit afficher(String nomProduit) {
		I_Produit produit = null;
		String nom;
		int quantiteStock;
		float prixUnitaireHT; 
		try {
			this.rs = this.st.executeQuery("SELECT * FROM Produit WHERE nom = '" + nomProduit + "'");
			while(this.rs.next()) {
				nom = this.rs.getString("nom");				
				if (this.rs.wasNull()) 
					nom = "Produit Sans Nom";
				prixUnitaireHT = this.rs.getInt("prixUnitaireHT");
				if (this.rs.wasNull()) 
					prixUnitaireHT = 1;
				quantiteStock = this.rs.getInt("quantiteStock");
				if (this.rs.wasNull()) 
					quantiteStock = 0;
				produit = new Produit(nom, prixUnitaireHT, quantiteStock);
			}
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		return produit;
	}

	@Override
	public List<I_Produit> afficherTout() {
		List<I_Produit> listeProduits = new ArrayList<I_Produit>();
		String nom;
		int quantiteStock;
		float prixUnitaireHT; 
		try {
			this.rs = this.st.executeQuery("SELECT * FROM Produit");
			while(this.rs.next()) {
				nom = this.rs.getString("nom");				
				if (this.rs.wasNull()) 
					nom = "Produit Sans Nom";
				prixUnitaireHT = this.rs.getInt("prixUnitaireHT");
				if (this.rs.wasNull()) 
					prixUnitaireHT = 1;
				quantiteStock = this.rs.getInt("quantiteStock");
				if (this.rs.wasNull()) 
					quantiteStock = 0;
				listeProduits.add(new Produit(nom, prixUnitaireHT, quantiteStock));
			}
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		return listeProduits;
	}
}
