package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import application.ManageTransactionController;

/**
 * 
 * @author Thomas CHARMES
 *
 */
public class FenetreVente extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;

	public FenetreVente(String[] lesProduits) {
		setTitle("Vente");
		setBounds(400, 450, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			ManageTransactionController mtc = new ManageTransactionController();
			String nom = combo.getSelectedItem().toString();
			int qte = Integer.parseInt(txtQuantite.getText());
			mtc.enleverStock(nom, qte);
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			System.out.println("La quantité n'est pas valide.");
		}
		this.dispose();
	}

}
