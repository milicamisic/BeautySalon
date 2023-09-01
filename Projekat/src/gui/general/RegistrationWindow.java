package gui.general;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gui.clientView.ClientMainWindow;
import humanEntities.Sex;
import paket1.MainWindow;
import service.UserService;

public class RegistrationWindow extends JFrame {

	private static final long serialVersionUID = -3931298970489487084L;
	
	private JPanel contentPanel = new JPanel();
	
	private JLabel nameLabel = new JLabel("Name:");
	private JLabel surnameLabel = new JLabel("Surname:");
	private JLabel sexLabel = new JLabel("Sex");
	private JLabel phoneNumberLabel = new JLabel("Phone number:");
	private JLabel addressLabel = new JLabel("Address");
	private JLabel usernameLabel = new JLabel("Username:");
	private JLabel passwordLabel = new JLabel("Password:");
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField phoneNumberField;
	private JTextField addressField;
	private JTextField usernameField;
	private JButton registerButton = new JButton("Register");
	private JButton cancelButton = new JButton("Cancel");
	private JPasswordField passwordField;

	public RegistrationWindow() {
		
		setBounds(100, 100, 976, 592);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabel.setBounds(90, 96, 173, 52);
		
		contentPanel.add(nameLabel);
		surnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		surnameLabel.setBounds(90, 159, 173, 52);
		
		contentPanel.add(surnameLabel);
		sexLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sexLabel.setBounds(90, 222, 173, 52);
		
		contentPanel.add(sexLabel);
		phoneNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		phoneNumberLabel.setBounds(90, 285, 173, 52);
		
		contentPanel.add(phoneNumberLabel);
		addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addressLabel.setBounds(90, 348, 96, 52);
		
		contentPanel.add(addressLabel);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameLabel.setBounds(519, 96, 173, 52);
		
		contentPanel.add(usernameLabel);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordLabel.setBounds(519, 159, 173, 52);
		
		contentPanel.add(passwordLabel);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameField.setBounds(246, 96, 159, 40);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		surnameField = new JTextField();
		surnameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		surnameField.setColumns(10);
		surnameField.setBounds(246, 159, 159, 40);
		contentPanel.add(surnameField);
		
		phoneNumberField = new JTextField();
		phoneNumberField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		phoneNumberField.setColumns(10);
		phoneNumberField.setBounds(246, 285, 159, 40);
		contentPanel.add(phoneNumberField);
		
		addressField = new JTextField();
		addressField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addressField.setColumns(10);
		addressField.setBounds(246, 348, 159, 40);
		contentPanel.add(addressField);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameField.setColumns(10);
		usernameField.setBounds(646, 96, 159, 40);
		contentPanel.add(usernameField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(646, 170, 157, 39);
		contentPanel.add(passwordField);
		
		String[] sexStrings = { "MALE", "FEMALE"};
		JComboBox sexComboBox = new JComboBox(sexStrings);
		sexComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sexComboBox.setBounds(246, 222, 159, 41);
		contentPanel.add(sexComboBox);
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String surname = surnameField.getText();
				Sex sex = Sex.valueOf((String) sexComboBox.getSelectedItem());
				String phoneNumber = phoneNumberField.getText();
				String address = addressField.getText();
				String username = usernameField.getText();
				char[] password = passwordField.getPassword();
				
				boolean inputValid = checkRegistrationForm(name, surname, phoneNumber, address, username, password);
				
				if(inputValid) {
					UserService userService = new UserService();
					boolean registered = userService.registerClient(name, surname, sex, phoneNumber, address, username, password);
					if(registered)
						switchToClientMainWindow();
				}
			}
		});
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		registerButton.setBounds(400, 451, 154, 58);
		contentPanel.add(registerButton);
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToMainWindow();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cancelButton.setBounds(819, 495, 121, 40);
		contentPanel.add(cancelButton);
		
		setLocationRelativeTo(null);
		
		
	}
	
	protected void switchToMainWindow() {
		MainWindow mainWindow =  new MainWindow();
		mainWindow.setVisible(true);
		dispose();
	}

	protected void switchToClientMainWindow() {
		ClientMainWindow clientMainWindow = new ClientMainWindow();
		clientMainWindow.setVisible(true);
		dispose();
	}

	public boolean checkRegistrationForm(String name, String surname, String phoneNumber, String address, String username, char[] password) {
		if(name.isEmpty() || surname.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || username.isEmpty() || password.length == 0) {
			JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(!UserService.isAlpha(name)) {
			JOptionPane.showMessageDialog(null, "Name can only contain letters!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(!UserService.isAlpha(surname)) {
			JOptionPane.showMessageDialog(null, "Surname can only contain letters!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(!UserService.isNumber(phoneNumber)) {
			JOptionPane.showMessageDialog(null, "Phone number can only contain digits!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(UserService.usernameTaken(username)) {
			JOptionPane.showMessageDialog(null, "There already exists an account with that username!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		JOptionPane.showMessageDialog(null, "Registration successful!", "Information message", JOptionPane.INFORMATION_MESSAGE);
		return true;
	}
}
