package metier;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Thomas CHARMES
 *
 */
public class CatalogueTest2 {

	I_Catalogue cat;

	@Before
	public void setUp() {
		cat = new Catalogue();
	}
	
	@Test
	public void testConstructeurCatalogue() {
		assertNotNull("créer catalogue", cat);
	}

	@Test
	public void testAddProduitIProduit_null() {
		I_Produit p1 = null;
		assertFalse("ajout produit null", cat.addProduit(p1));
	}

	@Test
	public void testAddProduitIProduit_unProduit() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		assertTrue("ajout un produit", cat.addProduit(p1));
	}

	@Test
	public void testAddProduitIProduit_deuxProduits() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		assertTrue("ajout deux produits", cat.addProduit(p2));
	}

	@Test
	public void testAddProduitIProduit_deuxFoisMemeProduitConsecutif() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		assertFalse("ajout deux fois même produit consécutif", cat.addProduit(p2));
	}

	@Test
	public void testAddProduitIProduit_deuxFoisMemeProduitNonConsecutif() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		assertFalse("ajout deux fois même produit non consécutif", cat.addProduit(p1));
	}

	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomConsecutif() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		I_Produit p3 = createProduit("Treets", 15, 2);
		assertFalse("ajout deux produits avec même nom consécutif", cat.addProduit(p3));
	}

	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomNonConsecutif() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		I_Produit p3 = createProduit("Mars", 15, 2);
		assertFalse("ajout deux produits avec même nom non consécutif", cat.addProduit(p3));
	}

	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomEspacesAuDebut() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit(" Mars", 15, 2);
		assertFalse("ajout deux produits avec même nom mais un avec des espaces au début", cat.addProduit(p2));
	}

	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomTabulationsAuDebut() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("	Mars", 15, 2);
		assertFalse("ajout deux produits avec même nom mais un avec des tabulations au début", cat.addProduit(p2));
	}

	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomEspacesALaFin() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Mars ", 15, 2);
		assertFalse("ajout deux produits avec même nom mais un avec des espaces à  la fin", cat.addProduit(p2));
	}

	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomTabulationsALaFin() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Mars	", 15, 2);
		assertFalse("ajout deux produits avec même nom mais un avec des tabulations à  la fin", cat.addProduit(p2));
	}

	@Test
	public void testAddProduitIProduit_stockNegatif() {
		I_Produit p1 = createProduit("Raider", 10, -1);
		assertFalse("ajout produit avec un stock négatif", cat.addProduit(p1));
	}

	@Test
	public void testAddProduitIProduit_stockNul() {
		I_Produit p1 = createProduit("Snickers", 1, 0);
		assertTrue("ajout produit avec un stock nul", cat.addProduit(p1));
	}

	@Test
	public void testAddProduitIProduit_prixNul() {
		I_Produit p1 = createProduit("Lion", 0, 3);
		assertFalse("ajout produit avec un prix nul", cat.addProduit(p1));
	}

	@Test
	public void testAddProduitIProduit_prixNegatif() {
		I_Produit p1 = createProduit("Bounty", -5, 4);
		assertFalse("ajout produit avec un prix négatif", cat.addProduit(p1));
	}

	@Test
	public void testAddProduitStringDoubleInt_unProduit() {
		assertTrue("ajout un produit", cat.addProduit("Mars", 10, 1));
	}

	@Test
	public void testAddProduitStringDoubleInt_deuxProduit() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		assertTrue("ajout deux produits", cat.addProduit("Treets", 10, 1));
	}

	@Test
	public void testAddProduitStringDoubleInt_deuxFoisMemeNomConsecutif() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		assertFalse("ajout deux fois même produit consécutif", cat.addProduit("Treets", 10, 1));
	}

	@Test
	public void testAddProduitStringDoubleInt_deuxFoisMemeNomNonConsecutif() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		assertFalse("ajout deux fois même produit non consécutif", cat.addProduit("Mars", 10, 1));
	}

	@Test
	public void testAddProduitStringDoubleInt_deuxFoisProduitMemeNomEspacesAuDebut() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		assertFalse("ajout deux fois même produit mais un avec espaces au début", cat.addProduit(" Mars", 10, 1));
	}

	@Test
	public void testAddProduitStringDoubleInt_deuxFoisProduitMemeNomTabulationsAuDebut() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		assertFalse("ajout deux fois même produit mais un avec tabulations au début", cat.addProduit("	Mars", 10, 1));
	}

	@Test
	public void testAddProduitStringDoubleInt_deuxFoisProduitMemeNomEspacesALaFin() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		assertFalse("ajout deux fois même produit mais un avec espaces à  la fin", cat.addProduit("Mars ", 10, 1));
	}

	@Test
	public void testAddProduitStringDoubleInt_deuxFoisProduitMemeNomTabulationsALaFin() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		assertFalse("ajout deux fois même produit mais un avec tabulations à  la fin", cat.addProduit("Mars	", 10, 1));
	}

	@Test
	public void testAddProduitStringDoubleInt_stockNegatif() {
		assertFalse("ajout produit avec stock négatif", cat.addProduit("Raider", 10, -1));
	}

	@Test
	public void testAddProduitStringDoubleInt_stockNul() {
		assertTrue("ajout produit avec stock nul", cat.addProduit("Snickers", 1, 0));
	}

	@Test
	public void testAddProduitStringDoubleInt_prixNul() {
		assertFalse("ajout produit avec prix nul", cat.addProduit("Lion", 0, 3));
	}

	@Test
	public void testAddProduitStringDoubleInt_prixNegatif() {
		assertFalse("ajout produit avec prix négatif", cat.addProduit("Bounty", -5, 4));
	}

	@Test
	public void testAddProduits_null() {
		List<I_Produit> liste = null;
		assertEquals("ajout liste null", 0, cat.addProduits(liste));
	}

	@Test
	public void testAddProduits_vide() {
		List<I_Produit> liste = new ArrayList<I_Produit>();
		assertEquals("ajout liste vide", 0, cat.addProduits(liste));
	}

	@Test
	public void testAddProduits_produitsSansDoublonsAvecCatalogueVide() {
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		liste.add(p1);
		liste.add(p2);
		assertEquals("ajout liste avec deux produits dans un catalogue vide", 2, cat.addProduits(liste));
	}

	@Test
	public void testAddProduits_produitsSansDoublonsAvecCatalogueDejaRempli() {
		I_Produit p0 = createProduit("Twix", 10, 6);
		cat.addProduit(p0);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		liste.add(p1);
		liste.add(p2);
		assertEquals("ajout liste avec deux produits dans un catalogue plein", 2, cat.addProduits(liste));
	}

	@Test
	public void testAddProduits_produitsAvecUnSeulDoublon() {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec un seul des produits déjà  dans le catalogue", 3, cat.addProduits(liste));
	}

	@Test
	public void testAddProduits_produitsAvecCertainsDoublons() {
		I_Produit p1 = createProduit("Twix", 10, 4);
		I_Produit p2 = createProduit("Bounty", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec plusieurs produits déjà  dans le catalogue", 2, cat.addProduits(liste));
	}

	@Test
	public void testAddProduits_produitsAvecQueDesDoublons() {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		liste.add(p1);
		liste.add(p2);
		assertEquals("ajout liste avec tous les produits déjà  dans le catalogue", 0, cat.addProduits(liste));
	}

	@Test
	public void testAddProduits_produitsAvecNomsDoublons() {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars", 15, 2);
		I_Produit p4 = createProduit("Treets", 10, 6);
		liste.add(p3);
		liste.add(p4);
		assertEquals("ajout liste produits dont tous les noms dans le catalogue", 0, cat.addProduits(liste));
	}

	@Test
	public void testAddProduits_produitsAvecDoublonsNomProduitsEspacesAuDebut() {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit(" Mars", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec produit espaces au début du nom", 3, cat.addProduits(liste));
	}

	@Test
	public void testAddProduits_produitsAvecDoublonsNomProduitsTabulationsAuDebut() {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("	Mars", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec produit tabulations au début du nom", 3, cat.addProduits(liste));
	}

	@Test
	public void testAddProduits_produitsAvecDoublonsNomProduitsEspacesALaFin() {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars ", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec produit espace à  la fin du nom", 3, cat.addProduits(liste));
	}

	@Test
	public void testAddProduits_produitsAvecDoublonsNomProduitsTabulationsALaFin() {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars ", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec produit tabulation à  la fin du nom", 3, cat.addProduits(liste));
	}

	@Test
	public void testAddProduits_avecStocksNegatifs() {
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Mars", 15, 2);
		I_Produit p2 = createProduit("Kit Kat", 8, -3);
		I_Produit p3 = createProduit("Lion", 4, 6);
		liste.add(p1);
		liste.add(p2);
		liste.add(p3);
		assertEquals("ajout liste produit avec stock négatif", 2, cat.addProduits(liste));
	}

	@Test
	public void testAddProduits_avecStocksNull() {
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Mars", 15, 2);
		I_Produit p2 = createProduit("Snickers", 1, 0);
		I_Produit p3 = createProduit("Lion", 4, 6);
		liste.add(p1);
		liste.add(p2);
		liste.add(p3);
		assertEquals("ajout liste produit avec stock nul", 3, cat.addProduits(liste));
	}

	@Test
	public void testAddProduits_avecPrixNul() {
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Nuts", 0, 1);
		liste.add(p1);
		assertEquals("ajout liste produit avec prix nul", 0, cat.addProduits(liste));
	}

	@Test
	public void testAddProduits_avecPrixNegatif() {
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Topset", -8, 3);
		I_Produit p2 = createProduit("Nuts", 4, 6);
		liste.add(p1);
		liste.add(p2);
		assertEquals("ajout liste produit avec prix négatif", 1, cat.addProduits(liste));
	}

	@Test
	public void testRemoveProduit_existe() {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertTrue("suppression produit existant", cat.removeProduit("Mars"));
	}

	@Test
	public void testRemoveProduit_existePas() {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertFalse("suppression produit qui n'existe pas", cat.removeProduit("Lion"));
	}

	@Test
	public void testRemoveProduit_null() {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertFalse("suppression avec un nom null", cat.removeProduit(null));
	}

	@Test
	public void testAcheterProduit_existePas() {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertFalse("acheter produit qui n'existe pas", cat.acheterStock("Nuts", 3));
	}

	@Test
	public void testAcheterProduit_existe() {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertTrue("acheter produit qui existe", cat.acheterStock("Raider", 3));
	}

	@Test
	public void testAcheterProduit_quantiteNegative() {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertFalse("acheter quantité négative", cat.acheterStock("Mars", -4));
	}

	@Test
	public void testAcheterProduit_quantiteNulle() {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertFalse("acheter quantité nulle", cat.acheterStock("Treets", 0));
	}

	@Test
	public void testVendreProduit_existePas() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertFalse("vendre produit qui n'existe pas", cat.vendreStock("Nuts", 3));
	}

	@Test
	public void testVendreProduit_existe() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertTrue("vendre produit qui existe", cat.vendreStock("Raider", 1));
	}

	@Test
	public void testVendreProduit_quantiteNegative() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertFalse("vendre quantité négative", cat.vendreStock("Mars", -4));
	}

	@Test
	public void testVendreProduit_quantiteNulle() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertFalse("vendre quantité nulle", cat.vendreStock("Treets", 0));
	}

	@Test
	public void testVendreProduit_stockNul() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertFalse("vendre produit sans stock", cat.vendreStock("Treets", 4));
	}

	@Test
	public void testVendreProduit_stockInsuffisant() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertFalse("vendre produit stock insuffisant", cat.vendreStock("Raider", 10));
	}

	@Test
	public void testGetNomProduits_vide() {
		String[] tab0 = new String[0];
		assertArrayEquals("recupère nom produits catalogue vide", tab0, cat.getNomProduits());
	}

	@Test
	public void testGetNomProduits_unProduit() {
		String[] tab = { "Mars" };
		cat.addProduit("Mars", 10, 1);
		assertArrayEquals("récupère nom produits avec un seul produit", tab, cat.getNomProduits());
	}

	@Test
	public void testGetNomProduits_unProduitAvecNomEspacesAuDebut() {
		String[] tab = { "Mars" };
		cat.addProduit(" Mars", 10, 1);
		assertArrayEquals("récupère nom produit avec espace debut ; les espaces au début ne doivent pas être stockés",
				tab, cat.getNomProduits());
	}

	@Test
	public void testGetNomProduits_unProduitAvecNomEspacesALaFin() {
		String[] tab = { "Mars" };
		cat.addProduit("Mars ", 10, 1);
		assertArrayEquals("récupère nom produit avec espace fin ; les espaces à  la fin ne doivent pas être stockés",
				tab, cat.getNomProduits());
	}

	@Test
	public void testGetNomProduits_unProduitAvecNomEspacesAuMilieu() {
		String[] tab = { "Kit Kat" };
		cat.addProduit("Kit Kat", 10, 1);
		assertArrayEquals("récupère nom produit avec des espace au milieu", tab, cat.getNomProduits());
	}

	@Test
	public void testGetNomProduits_unProduitAvecNomTabulationsAuDebut() {
		String[] tab = { "Mars" };
		cat.addProduit("	Mars", 10, 1);
		assertArrayEquals(
				"récupère nom produit avec tabulation debut ; les tabulations au début ne doivent pas être stockés",
				tab, cat.getNomProduits());
	}

	@Test
	public void testGetNomProduits_unProduitAvecNomTabulationsALaFin() {
		String[] tab = { "Mars" };
		cat.addProduit("Mars	", 10, 1);
		assertArrayEquals(
				"récupère nom produit avec tabulation fin ; les tabulations à  la fin ne doivent pas être stockés", tab,
				cat.getNomProduits());
	}

	@Test
	public void testGetNomProduits_unProduitAvecNomTabulationsAuMilieu() {
		String[] tab = { "Kit Kat" };
		cat.addProduit("Kit	Kat", 10, 1);
		assertArrayEquals(
				"récupère nom produit avec des tabulations au milieu ; les tabulations au milieu doivent être remplacées par des espaces",
				tab, cat.getNomProduits());
	}

	@Test
	public void testGetNomProduits_deuxProduits() {
		String[] tab = { "Mars", "Treets" };
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		assertArrayEquals("récupère nom de deux produits", tab, cat.getNomProduits());
	}

	@Test
	public void testGetNomProduits_plusieursProduitsInseresOrdreAlphabetique() {
		String[] tab = { "Mars", "Raider", "Treets" };
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Raider", 12, 2);
		cat.addProduit("Treets", 10, 1);
		assertArrayEquals("récupère nom de plusieurs produits ajoutés dans ordre alphabétique", tab,
				cat.getNomProduits());
	}

	@Test
	public void testGetNomProduits_plusieursProduitsInseresOrdreAleatoire() {
		String[] tab = { "Bounty", "Mars", "Raider", "Treets" };
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		cat.addProduit("Bounty", 12, 2);
		assertArrayEquals(
				"récupère nom de plusieurs produits ajoutés dans ordre aléatoire (doivent être retournés dans l'ordre alphabétique)",
				tab, cat.getNomProduits());
	}

	@Test
	public void testMontantTotalTTC_catalogueVide() {
		assertEquals("montant TTC catalogue vide", 0, cat.getMontantTotalTTC(), 0);
	}

	@Test
	public void testMontantTotalTTC_pasDeStock() {
		cat.addProduit("Nuts", 1, 0);
		assertEquals("montant TTC sans stock", 0, cat.getMontantTotalTTC(), 0);
	}

	@Test
	public void testMontantTotalTTC_sansVirgule() {
		cat.addProduit("Mars", 100, 4);
		cat.addProduit("Raider", 20, 5);
		assertEquals("montant TTC sans virgule ", 600, cat.getMontantTotalTTC(), 0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_SansArrondi_UnChiffreApresLaVirgule() {
		cat.addProduit("Mars", 10, 6);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 12);
		assertEquals("montant TTC avec virgule ; 1 chiffre", 134.4, cat.getMontantTotalTTC(), 0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_SansArrondi_DeuxChiffresApresLaVirgule() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 12.6, 1);
		assertEquals("montant TTC avec virgule ; 2 chiffres", 135.12, cat.getMontantTotalTTC(), 0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_AvecArrondiInferieur_TroisChiffresApresLaVirgule() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 12.66, 1);
		assertEquals("montant TTC avec virgule ; 135.192 doit être arrondi à  135.19", 135.19, cat.getMontantTotalTTC(),
				0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_AvecArrondiSuperieur_TroisChiffresApresLaVirgule() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 12.69, 1);
		assertEquals("montant TTC avec virgule ; 135.228 doit être arrondi à  135.23", 135.23, cat.getMontantTotalTTC(),
				0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_AvecArrondiSuperieur_TroisChiffresApresLaVirgule_IlNeFautPasArrondirLePrixDuStockUnitaireMaisLePrixDuStockTotal() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 12.67, 1);
		cat.addProduit("Nuts", 12.67, 1);
		assertEquals("c'est le montant total TTC qu'il faut arrondir, pas les prix TTC des différents produits", 150.41,
				cat.getMontantTotalTTC(), 0);

	}

	@Test
	public void testToString_CatalogueVide() {
		String resultatAttendu = "\n" + "Montant total TTC du stock : 0,00 €";
		assertEquals("toString catalogue vide", resultatAttendu, cat.toString());
	}

	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecAucunChiffreApresVirgule() {
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n"
				+ "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n"
				+ "Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" + "\n"
				+ "Montant total TTC du stock : 120,00 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		assertEquals("toString catalogue sans virgule", resultatAttendu, cat.toString());
	}

	@Test
	public void testToString_CatalogueAvecDesProduits_AvecDesEspaceDansLesNomsDesProduit() {
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n"
				+ "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n"
				+ "Kit Kat - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" + "\n"
				+ "Montant total TTC du stock : 120,00 €";
		cat.addProduit("Mars ", 10, 5);
		cat.addProduit(" Treets", 10, 4);
		cat.addProduit("Kit Kat", 1, 10);
		assertEquals("toString avec des espaces dans les noms des produits", resultatAttendu, cat.toString());
	}

	@Test
	public void testToString_CatalogueAvecDesProduits_AvecDesTabulationsDansLesNomsDesProduit() {
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n"
				+ "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n"
				+ "Kit Kat - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" + "\n"
				+ "Montant total TTC du stock : 120,00 €";
		cat.addProduit("Mars	", 10, 5);
		cat.addProduit("	Treets", 10, 4);
		cat.addProduit("Kit	Kat", 1, 10);
		assertEquals("toString avec des tabulations dans les noms des produits", resultatAttendu, cat.toString());
	}

	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecUnChiffreApresVirgule() {
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n"
				+ "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n"
				+ "Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n"
				+ "Twix - prix HT : 10,45 € - prix TTC : 12,54 € - quantité en stock : 5" + "\n" + "\n"
				+ "Montant total TTC du stock : 182,70 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 10.45, 5);
		assertEquals("toString catalogue avec un total d'un chiffre après la virgule", resultatAttendu, cat.toString());
	}

	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecDeuxChiffresApresVirgule() {
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n"
				+ "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n"
				+ "Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n"
				+ "Twix - prix HT : 10,40 € - prix TTC : 12,48 € - quantité en stock : 1" + "\n" + "\n"
				+ "Montant total TTC du stock : 132,48 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 10.4, 1);
		assertEquals("toString catalogue avec un total de deux chiffres après virgule", resultatAttendu,
				cat.toString());
	}

	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecTroisChiffresApresVirguleArrondiInferieur() {
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n"
				+ "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n"
				+ "Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n"
				+ "Twix - prix HT : 10,47 € - prix TTC : 12,56 € - quantité en stock : 1" + "\n" + "\n"
				+ "Montant total TTC du stock : 132,56 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 10.47, 1);
		assertEquals(
				"on affiche que deux chiffres après la virgule dans le prix unitaires TTC, mais le montant total TTC du catalogue est calculé avec les prix unitaires TTC non arrondis",
				resultatAttendu, cat.toString());
	}

	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecTroisChiffresApresVirguleArrondiSuperieur() {
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n"
				+ "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n"
				+ "Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n"
				+ "Twix - prix HT : 10,47 € - prix TTC : 12,56 € - quantité en stock : 2" + "\n" + "\n"
				+ "Montant total TTC du stock : 145,13 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 10.47, 2);
		assertEquals(
				"on affiche que deux chiffres après la virgule dans le prix unitaires TTC, mais le montant total TTC du catalogue est calculé avec les prix unitaires TTC non arrondis",
				resultatAttendu, cat.toString());
	}

	@Test
	public void testClear() {
		cat.addProduit("Mars", 10, 3);
		cat.addProduit("Twix", 8, 0);
		cat.addProduit("Kinder Bueno", 5, 12);
		
		cat.clear();
		assertEquals("le stock doit etre vide",0,cat.getNomProduits().length);
	}

	private I_Produit createProduit(String nom, double prixHT, int quantite) {
		try {
			return new Produit(nom,prixHT,quantite);
		}
		catch (Exception e) { return null; }
	}		

}
