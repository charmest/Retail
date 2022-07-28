package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import application.ManageProductController;
import metier.Produit;

/**
 * 
 * @author Thomas CHARMES
 *
 */
public class FenetreNouveauProduit extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPrixHT;
	private JTextField txtNom;
	private JTextField txtQte;
	private JButton btValider;

	public FenetreNouveauProduit() {
		setTitle("Creation Produit");
		setBounds(400, 450, 200, 250);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JLabel labNom = new JLabel("Nom produit");
		JLabel labPrixHT = new JLabel("Prix Hors Taxe");
		JLabel labQte = new JLabel("Quantité en stock");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labPrixHT);
		txtPrixHT = new JTextField(15);
		contentPane.add(txtPrixHT);
		contentPane.add(labQte);
		txtQte = new JTextField(15);
		contentPane.add(txtQte);

		btValider = new JButton("Valider");
		contentPane.add(btValider);

		btValider.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			ManageProductController mp = new ManageProductController();
			String nom = txtNom.getText();
			int prix = Integer.parseInt(txtPrixHT.getText());
			int qte = Integer.parseInt(txtQte.getText());
			mp.creerProduit(new Produit(nom, prix, qte));
		} catch (NumberFormatException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Une ou plusieurs valeurs ne sont pas valides.");
		}
		this.dispose();
	}

}