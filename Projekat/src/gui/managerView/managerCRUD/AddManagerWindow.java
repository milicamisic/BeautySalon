package gui.managerView.managerCRUD;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import humanEntities.Manager;
import humanEntities.Sex;
import service.ManagerService;
import service.UserService;

public class AddManagerWindow extends JDialog {
	
	private static final long serialVersionUID = 6942042170462061710L;
	
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField usernameField;
	private JTextField passwordField;
	private JTextField addressField;
	private JTextField phoneNumberField;
	private JTextField basePayField;
	private JTextField educationLevelField;
	private JTextField serviceYearsField;

	public AddManagerWindow(ManagerCRUDWindow parent) {
		super(parent, true);
		
		setMinimumSize(new Dimension(980, 630));
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(60, 66, 127, 48);
		getContentPane().add(lblNewLabel);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		nameField.setBounds(259, 66, 144, 48);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSurname.setBounds(60, 161, 127, 48);
		getContentPane().add(lblSurname);
		
		surnameField = new JTextField();
		surnameField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		surnameField.setColumns(10);
		surnameField.setBounds(259, 161, 144, 48);
		getContentPane().add(surnameField);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblUsername.setBounds(60, 255, 127, 48);
		getContentPane().add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		usernameField.setColumns(10);
		usernameField.setBounds(259, 255, 144, 48);
		getContentPane().add(usernameField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPassword.setBounds(60, 350, 127, 48);
		getContentPane().add(lblPassword);
		
		passwordField = new JTextField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		passwordField.setColumns(10);
		passwordField.setBounds(259, 351, 144, 48);
		getContentPane().add(passwordField);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAddress.setBounds(490, 66, 127, 48);
		getContentPane().add(lblAddress);
		
		addressField = new JTextField();
		addressField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		addressField.setColumns(10);
		addressField.setBounds(679, 66, 144, 48);
		getContentPane().add(addressField);
		
		JLabel lblPhone = new JLabel("Phone Number:");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPhone.setBounds(490, 161, 179, 48);
		getContentPane().add(lblPhone);
		
		phoneNumberField = new JTextField();
		phoneNumberField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		phoneNumberField.setColumns(10);
		phoneNumberField.setBounds(679, 161, 144, 48);
		getContentPane().add(phoneNumberField);
		
		JLabel lblSex = new JLabel("Sex:");
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSex.setBounds(490, 255, 127, 48);
		getContentPane().add(lblSex);
		
		String[] sexStrings = { "MALE", "FEMALE"};
		JComboBox sexComboBox = new JComboBox(sexStrings);
		sexComboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		sexComboBox.setBounds(679, 255, 144, 48);
		getContentPane().add(sexComboBox);
		
		JLabel lblBasePay = new JLabel("Base Pay:");
		lblBasePay.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblBasePay.setBounds(490, 350, 127, 48);
		getContentPane().add(lblBasePay);
		
		basePayField = new JTextField();
		basePayField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		basePayField.setColumns(10);
		basePayField.setBounds(679, 350, 144, 48);
		getContentPane().add(basePayField);
		
		JLabel lblEducationLevel = new JLabel("Education Level:");
		lblEducationLevel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEducationLevel.setBounds(60, 446, 189, 48);
		getContentPane().add(lblEducationLevel);
		
		educationLevelField = new JTextField();
		educationLevelField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		educationLevelField.setColumns(10);
		educationLevelField.setBounds(259, 447, 144, 48);
		getContentPane().add(educationLevelField);
		
		JLabel lblServiceYears = new JLabel("Service Years:");
		lblServiceYears.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblServiceYears.setBounds(490, 446, 174, 48);
		getContentPane().add(lblServiceYears);
		
		serviceYearsField = new JTextField();
		serviceYearsField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		serviceYearsField.setColumns(10);
		serviceYearsField.setBounds(679, 447, 144, 48);
		getContentPane().add(serviceYearsField);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String surname = surnameField.getText();
				Sex sex = Sex.valueOf((String) sexComboBox.getSelectedItem());
				String phoneNumber = phoneNumberField.getText();
				String address = addressField.getText();
				String username = usernameField.getText();
				String password = passwordField.getText();
				String basePayString = basePayField.getText();
				String educationLevelString = educationLevelField.getText();
				String serviceYearsString = serviceYearsField.getText();
				
				boolean inputValid = valideFields(name, surname, phoneNumber, address, username, password, basePayString, educationLevelString, serviceYearsString);
				
				if(inputValid) 
				{
					Manager manager = new Manager(name, surname, sex, phoneNumber, address, username, password, Integer.parseInt(educationLevelString), Integer.parseInt(serviceYearsString), 0, Double.parseDouble(basePayString));
					ManagerService managerService = new ManagerService();
					managerService.addManager(manager);
					
					parent.refreshTable();
					
					dispose();
				}
			}
		});
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addButton.setBounds(623, 530, 156, 51);
		getContentPane().add(addButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cancelButton.setBounds(799, 530, 156, 51);
		getContentPane().add(cancelButton);
		
		setLocationRelativeTo(null);
		
	}
	
	private boolean valideFields(String name, String surname, String phoneNumber, String address, String username, String password, String basePayString, String educationLevelString, String serviceYearsString)
	{
		if(name.isEmpty() || surname.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || username.isEmpty() || password.isEmpty()) 
		{
			JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(!UserService.isAlpha(name)) 
		{
			JOptionPane.showMessageDialog(null, "Name can only contain letters!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(!UserService.isAlpha(surname)) 
		{
			JOptionPane.showMessageDialog(null, "Surname can only contain letters!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(!UserService.isNumber(phoneNumber)) 
		{
			JOptionPane.showMessageDialog(null, "Phone number can only contain digits!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(UserService.usernameTaken(username)) 
		{
			JOptionPane.showMessageDialog(null, "There already exists an account with that username!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try 
		{
			Double.parseDouble(basePayString);
		} 
		catch(Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Base pay must be a decimal!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try
		{
			Integer.parseInt(educationLevelString);
		} 
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Education level must be an integer!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try
		{
			Integer.parseInt(serviceYearsString);
		} 
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Number of service years must be an integer!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		JOptionPane.showMessageDialog(null, "Manager added!", "Information message", JOptionPane.INFORMATION_MESSAGE);
		return true;
		
	}
}
