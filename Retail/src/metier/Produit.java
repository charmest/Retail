package metier;

/**
 * 
 * @author Thomas CHARMES
 *
 */
public class Produit implements I_Produit {
	
	private int quantiteStock;
	private String nom;
	private double prixUnitaireHT;
	private double tauxTVA = 0.2;
	
	public Produit(String nom, double prixUnitaireHT, int quantiteStock) {
		this.nom = nom.trim();
		this.prixUnitaireHT = prixUnitaireHT;
		this.quantiteStock = quantiteStock;		
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		if (qteAchetee > 0 && getPrixUnitaireHT() >= 0) {
			this.quantiteStock += qteAchetee;
			return true;
		}
		return false;
	}

	@Override
	public boolean enlever(int qteVendue) {
		if (qteVendue > 0) {
			this.quantiteStock -= qteVendue;
			return true;
		}
		return false;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public int getQuantite() {
		return this.quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return (this.prixUnitaireHT);
	}

	@Override
	public double getPrixUnitaireTTC() {
		return (this.prixUnitaireHT + (this.prixUnitaireHT * this.tauxTVA));
	}

	@Override
	public double getPrixStockTTC() {
		return (this.getPrixUnitaireTTC() * this.quantiteStock);
	}

	@Override
	public String toString() {
		return "Produit [quantiteStock=" + quantiteStock + ", nom=" + nom + ", prixUnitaireHT=" + prixUnitaireHT
				+ ", tauxTVA=" + tauxTVA + "]";
	}
}
