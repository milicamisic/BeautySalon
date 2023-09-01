package gui.clientView;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import humanEntities.Client;
import paket1.BeautySalon;
import service.ClientService;
import java.awt.Dimension;

public class ViewLoyaltyCardWindow extends JDialog{
	
	private static final long serialVersionUID = 6859039513399780136L;
	
	private JTextField textField;
	
	private BeautySalon beautySalon;
	private ClientService clientService;

	public ViewLoyaltyCardWindow() {
		setMinimumSize(new Dimension(375, 150));
		setModal(true);
		
		beautySalon = BeautySalon.getBeautySalon();
		clientService = new ClientService();
		Client c = clientService.getClientByUsername(beautySalon.getCurrentUser().getUsername());
		
		getContentPane().setLayout(null);
		
		JLabel loyaltyCardLabel = new JLabel("Loyalty Card:");
		loyaltyCardLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		loyaltyCardLabel.setBounds(28, 39, 132, 44);
		getContentPane().add(loyaltyCardLabel);
		
		textField = new JTextField(Boolean.toString(c.hasLoyaltyCard()));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(164, 39, 132, 36);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		setLocationRelativeTo(null);
	}
}
