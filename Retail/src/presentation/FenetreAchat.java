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
public class FenetreAchat extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;

	public FenetreAchat(String[] lesProduits) {
		setTitle("Achat");
		setBounds(400, 450, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité achetée"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			ManageTransactionController mtc = new ManageTransactionController();
			String nom = combo.getSelectedItem().toString();
			int qte = Integer.parseInt(txtQuantite.getText());
			mtc.ajouterStock(nom, qte);
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			System.out.println("La quantité n'est pas valide.");
		}
		this.dispose();
	}

}
