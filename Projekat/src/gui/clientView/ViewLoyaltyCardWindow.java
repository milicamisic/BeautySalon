package gui.clientView;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class ViewLoyaltyCardWindow extends JDialog{

	private static final long serialVersionUID = -7549694012348296965L;
	private JTextField textField;

	public ViewLoyaltyCardWindow() {
		getContentPane().setLayout(null);
		
		JLabel loyaltyCardLabel = new JLabel("Loyalty Card:");
		loyaltyCardLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		loyaltyCardLabel.setBounds(28, 39, 132, 44);
		getContentPane().add(loyaltyCardLabel);
		
		textField = new JTextField();
		textField.setBounds(164, 39, 132, 36);
		getContentPane().add(textField);
		textField.setColumns(10);
		
	}
}
