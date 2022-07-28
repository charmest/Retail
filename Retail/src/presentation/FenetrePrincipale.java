package presentation;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import application.StockController;
import metier.I_Produit;

/**
 * 
 * @author Thomas CHARMES
 *
 */
public class FenetrePrincipale extends JFrame implements ActionListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btAfficher;
	private JButton btNouveauProduit;
	private JButton btSupprimerProduit;
	private JButton btAchat;
	private JButton btVente;
	private JButton btQuitter;
	
	public FenetrePrincipale() {
		setTitle("projet Produits");
		setBounds(400, 200, 320, 250);
		JPanel panAffichage = new JPanel();
		JPanel panNouveauSupprimerProduit = new JPanel();
		JPanel panAchatVente = new JPanel();
		JPanel panQuitter = new JPanel();
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAfficher = new JButton("Quantités en stock");
		btNouveauProduit = new JButton("Nouveau Produit");
		btSupprimerProduit = new JButton("Supprimer Produit");
		btAchat = new JButton("Achat Produits");
		btVente = new JButton("Vente Produits");
		btQuitter = new JButton("Quitter");
		panAffichage.add(btAfficher);
		panNouveauSupprimerProduit.add(btNouveauProduit); 
		panNouveauSupprimerProduit.add(btSupprimerProduit);
		panAchatVente.add(btAchat); 
		panAchatVente.add(btVente);  
		panQuitter.add(btQuitter);

		contentPane.add(panAffichage);
		contentPane.add(panNouveauSupprimerProduit);
		contentPane.add(panAchatVente);
		contentPane.add(panQuitter);

		btAfficher.addActionListener(this);
		btNouveauProduit.addActionListener(this);
		btSupprimerProduit.addActionListener(this);
		btAchat.addActionListener(this);
		btVente.addActionListener(this);
		btQuitter.addActionListener(this);
		
		addWindowListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		StockController s = new StockController();
		List<String> listProduits = new ArrayList<String>();
		for (I_Produit prod : s.getListProduits()) {
			listProduits.add(prod.getNom());
		}
		String[] tabProduits = listProduits.toArray(new String[listProduits.size()]);
		if (e.getSource() == btAfficher) {
			new FenetreAffichage(s.etatStock());
		}			
		if (e.getSource() == btNouveauProduit)
			new FenetreNouveauProduit();
		if (e.getSource() == btSupprimerProduit) {
			new FenetreSuppressionProduit(tabProduits);
		}
		if (e.getSource() == btAchat)
			new FenetreAchat(tabProduits);
		if (e.getSource() == btVente)
			new FenetreVente(tabProduits);
		if (e.getSource() == btQuitter){
			System.out.println("Au revoir");
			System.exit(0);
		}	
	}

	public void windowClosing(WindowEvent arg0) {
		System.out.println("Au revoir");
		System.exit(0);
	}

	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
	
	public static void main(String[] args) {
		new FenetrePrincipale();
	}

}
