package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import application.ManageProductController;

/**
 * 
 * @author Thomas CHARMES
 *
 */
public class FenetreSuppressionProduit extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btSupprimer;
	private JComboBox<String> combo;
	
	public FenetreSuppressionProduit(String lesProduits[]) {
		setTitle("Suppression produit");
		setBounds(400, 450, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		ManageProductController mp = new ManageProductController();
		String nom = combo.getSelectedItem().toString();
		mp.supprimerProduit(nom);
		this.dispose();
	}

}
