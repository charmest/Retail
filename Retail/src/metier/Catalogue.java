package metier;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 * @author Thomas CHARMES
 *
 */
public class Catalogue implements I_Catalogue {
	
	private List<I_Produit> lesProduits;

	public Catalogue() {
		this.lesProduits = new ArrayList<I_Produit>();
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		String[] nameProduits = getNomProduits();
		boolean contient = false;
		for (String n : nameProduits) {
			if (n.equalsIgnoreCase(produit.getNom().trim())) contient = true; //trim l'input user pour enlever white space
		}
		if (!contient && produit != null && produit.getPrixUnitaireHT() > 0 && produit.getQuantite() >= 0) {
			this.lesProduits.add(produit);
			return true;
		}
		return false;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		boolean unique = true;
		for(I_Produit prod : this.lesProduits) {
			if (prod.getNom().equalsIgnoreCase(nom.trim())) unique = false;
		}
		if (unique && prix > 0 && qte >= 0) {
			if (nom.contains("\t")) nom = nom.replace("\t", " "); //remplace les tab par des espaces
			Produit p = new Produit(nom.trim(),prix,qte);
			this.lesProduits.add(p);
			return true;
		}
		return false;
	}

	@Override
	public int addProduits(List<I_Produit> l) {	
		int count = 0;
		if (l instanceof List) {
			for(I_Produit prod  : l) {
				//verifie si le nom du produit est dans le tableau des noms de produits enregistrés
				if (!Arrays.stream(getNomProduits()).anyMatch(x -> x.equalsIgnoreCase(prod.getNom().trim())) && prod.getPrixUnitaireHT() > 0 && prod.getQuantite() >= 0) {
					this.lesProduits.add(prod);
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public boolean removeProduit(String nom) {
		ListIterator<I_Produit> it = this.lesProduits.listIterator();
		
		if (nom != null) {
			while (it.hasNext()) {
				if (it.next().getNom().equalsIgnoreCase(nom)) {
					it.remove();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		if (qteAchetee > 0 && Arrays.stream(getNomProduits()).anyMatch(x -> x.equals(nomProduit.trim()))) {
			for(I_Produit prod : this.lesProduits) {
				if (prod.getNom().equals(nomProduit)) {
					prod.ajouter(qteAchetee);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		for(I_Produit prod : this.lesProduits) {
			if (prod.getNom().equals(nomProduit) && qteVendue <= prod.getQuantite() && qteVendue > 0) {
				prod.enlever(qteVendue);
				return true;
			}
		}
		return false;
	}

	@Override
	public String[] getNomProduits() {
		String[] produits = new String[this.lesProduits.size()];
		for (int i = 0; i < this.lesProduits.size(); i++) {
			produits[i] = this.lesProduits.get(i).getNom(); 
		}
		Arrays.sort(produits); //range dans l'ordre alphabétique
		return produits;
	}

	@Override
	public double getMontantTotalTTC() {
		double stock = 0;
		for (I_Produit prod : this.lesProduits) {
			stock += prod.getPrixStockTTC();
		}
		return Math.round(stock * 100.0)/100.0; //pour mettre à  deux chiffres après la virgule et arrondir
	}
	
	@Override
	public String toString() {
		String produits = "";
		DecimalFormat df = new DecimalFormat("0.00"); //pour afficher deux chiffres après la virgule
		//affiche les infos pour chaque produits
		for(I_Produit prod : this.lesProduits) {
			produits += 
				prod.getNom() + " - " +
				"prix HT : " + df.format(prod.getPrixUnitaireHT()) + " € - " +
				"prix TTC : " + df.format(prod.getPrixUnitaireTTC()) + " € - " +
				"quantité en stock : " + prod.getQuantite() + "\n";
		}
		//affiche le montant total
		produits += "\n" + "Montant total TTC du stock : " + df.format(this.getMontantTotalTTC()) + " €";
		return produits;
	}

	@Override
	public void clear() {
		this.lesProduits.clear();
	}

}
