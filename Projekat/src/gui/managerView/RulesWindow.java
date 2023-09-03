package gui.managerView;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import paket1.BeautySalon;

public class RulesWindow extends JDialog {

	private static final long serialVersionUID = 3344207707057918460L;
	
	private BeautySalon beautySalon;
	
	private JTextField bonusTextBox;
	private JTextField loyaltyCardTextBox;
	private JTextField workHoursStartTextBox;
	private JTextField workHoursEndTextBox;

	public RulesWindow() {
		setMinimumSize(new Dimension(800, 420));
		
		beautySalon = BeautySalon.getBeautySalon();
		
		setModal(true);
		getContentPane().setLayout(null);
		setResizable(false);
		
		JLabel bonusLabel = new JLabel("Number of completed appointments for bonus:");
		bonusLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		bonusLabel.setBounds(71, 68, 542, 48);
		getContentPane().add(bonusLabel);
		
		bonusTextBox = new JTextField(Integer.toString(beautySalon.getCompletedAppointmentsForBonus()));
		bonusTextBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		bonusTextBox.setColumns(10);
		bonusTextBox.setBounds(608, 69, 74, 48);
		getContentPane().add(bonusTextBox);
		
		JLabel loyaltyCardLabel = new JLabel("Requiered money spent to get loyalty card:");
		loyaltyCardLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		loyaltyCardLabel.setBounds(71, 145, 502, 48);
		getContentPane().add(loyaltyCardLabel);
		
		loyaltyCardTextBox = new JTextField(Double.toString(beautySalon.getLoyaltyCardPrecondition()));
		loyaltyCardTextBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		loyaltyCardTextBox.setColumns(10);
		loyaltyCardTextBox.setBounds(567, 146, 107, 48);
		getContentPane().add(loyaltyCardTextBox);
		
		JLabel workingHoursLabel = new JLabel("Working hours:");
		workingHoursLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		workingHoursLabel.setBounds(71, 239, 186, 48);
		getContentPane().add(workingHoursLabel);
		
		workHoursStartTextBox = new JTextField(beautySalon.getWorkingHoursStart().format(DateTimeFormatter.ofPattern("H:m")));
		workHoursStartTextBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		workHoursStartTextBox.setColumns(10);
		workHoursStartTextBox.setBounds(281, 240, 107, 48);
		getContentPane().add(workHoursStartTextBox);
		
		workHoursEndTextBox = new JTextField(beautySalon.getWorkingHoursEnd().format(DateTimeFormatter.ofPattern("H:m")));
		workHoursEndTextBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		workHoursEndTextBox.setColumns(10);
		workHoursEndTextBox.setBounds(430, 240, 107, 48);
		getContentPane().add(workHoursEndTextBox);
		
		JLabel workingHoursLabel_1 = new JLabel(" - ");
		workingHoursLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 44));
		workingHoursLabel_1.setBounds(384, 239, 51, 48);
		getContentPane().add(workingHoursLabel_1);
		
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean fieldsValid = validateFields(bonusTextBox.getText(), loyaltyCardTextBox.getText(), workHoursStartTextBox.getText(), workHoursEndTextBox.getText());
				if(fieldsValid) {
					beautySalon.setCompletedAppointmentsForBonus(Integer.parseInt(bonusTextBox.getText()));
					beautySalon.setLoyaltyCardPrecondition(Double.parseDouble(loyaltyCardTextBox.getText()));
					beautySalon.setWorkingHoursStart(LocalTime.parse(workHoursStartTextBox.getText(), DateTimeFormatter.ofPattern("H:m")));
					beautySalon.setWorkingHoursEnd(LocalTime.parse(workHoursEndTextBox.getText(), DateTimeFormatter.ofPattern("H:m")));
					dispose();
				}
			}
		});
		applyButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		applyButton.setBounds(429, 322, 156, 51);
		getContentPane().add(applyButton);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		goBackButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		goBackButton.setBounds(608, 322, 156, 51);
		getContentPane().add(goBackButton);
		
		setLocationRelativeTo(null);
	}
	
	private boolean validateFields(String bonusRequirement, String loyaltyCardRequirement, String startTime, String endTime) {
		
		if(bonusRequirement.trim().isEmpty() || loyaltyCardRequirement.trim().isEmpty() || startTime.trim().isEmpty() || endTime.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try
		{
			if(Integer.parseInt(bonusRequirement) < 0) {
				JOptionPane.showMessageDialog(null, "Bonus requirement must be more then 0!", "Error message", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Bonus requirement must be an integer!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try
		{
			if(Double.parseDouble(loyaltyCardRequirement) < 0) {
				JOptionPane.showMessageDialog(null, "Loyalty card requirement must be more then 0!", "Error message", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Loyalty card requirement must be a decimal!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");

        try 
        {
            LocalTime.parse(startTime, timeFormatter);
            LocalTime.parse(endTime, timeFormatter);
        } 
        catch (DateTimeParseException e) 
        {
        	JOptionPane.showMessageDialog(null, "Time must be in format \"hour:minute\"!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        
        JOptionPane.showMessageDialog(null, "Changes saved!", "Info message", JOptionPane.INFORMATION_MESSAGE);
        return true;
	}
}
