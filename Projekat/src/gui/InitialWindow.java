package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.managerView.appointmentCRUD.AppointmentCRUDWindow;
import gui.managerView.beauticianCRUD.BeauticianCRUDWindow;
import gui.managerView.clientCRUD.ClientCRUDWindow;
import gui.managerView.managerCRUD.ManagerCRUDWindow;
import gui.managerView.receptionistCRUD.ReceptionistCRUDWindow;
import humanEntities.Role;
import humanEntities.Sex;
import service.UserService;

public class InitialWindow extends JFrame{

	private static final long serialVersionUID = 522934942135202726L;
	
	JFrame frmBeautySalon = new JFrame("Beauty Salon");
	JPanel panelCont = new JPanel();
	
	JPanel welcomePanel = new JPanel();
	JPanel loginPanel = new JPanel();
	JPanel registerPanel = new JPanel();
	
	JPanel clientPanel = new JPanel();
	JPanel managerPanel = new JPanel();
	JPanel receptionistPanel = new JPanel();
	JPanel beauticianPanel = new JPanel();
	
	JPanel managersTablePanel = new JPanel();
	
	CardLayout cl = new CardLayout();
	private final JLabel lblNewLabel = new JLabel("Password:");
	private JTextField passField;
	private JTextField usernameField;
	private final JLabel lblNewLabel_3 = new JLabel("Name:");
	private final JLabel lblNewLabel_3_1 = new JLabel("Surname:");
	private final JLabel lblNewLabel_3_2 = new JLabel("Sex");
	private final JLabel lblNewLabel_3_3 = new JLabel("Phone number:");
	private final JLabel lblNewLabel_3_4 = new JLabel("Address");
	private final JLabel lblNewLabel_3_5 = new JLabel("Username:");
	private final JLabel lblNewLabel_3_6 = new JLabel("Password:");
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField phoneNumberField;
	private JTextField addressField;
	private JTextField registrationUsernameField;
	private JTextField registrationPasswordField;
	private final JButton registerButton = new JButton("Register");
	private final JButton registrationCancelButton = new JButton("Cancel");
	private final JButton viewManagersButton = new JButton("Managers");
	private final JButton viewBeauticiansButton = new JButton("Beauticians");
	private final JButton viewReceptionistsButton = new JButton("Receptionists");
	private final JButton viewClientsButton = new JButton("Clients");
	private final JButton viewAppointmentsButton = new JButton("Appointments");
	
	ManagerCRUDWindow managerCRUDWindow;
	BeauticianCRUDWindow beauticianCRUDWindow;
	ReceptionistCRUDWindow receptionistCRUDWindow;
	ClientCRUDWindow clientCRUDWindow;
	AppointmentCRUDWindow appointmentCRUDWindow;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitialWindow window = new InitialWindow();
					window.frmBeautySalon.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public InitialWindow() {
		initialize();
	}
	
	private void initialize() {
		panelCont.setLayout(cl);
		loginPanel.setBackground(Color.RED);
		registerPanel.setBackground(Color.GREEN);
		clientPanel.setBackground(Color.BLUE);
		managerPanel.setBackground(Color.YELLOW);
		
		// <=================== Welcome Panel ===================>
		panelCont.add(welcomePanel, "welcomePanel");
		welcomePanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Beauty Salon App");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_2.setBounds(323, 120, 326, 140);
		welcomePanel.add(lblNewLabel_2);
		
		// shows login page
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelCont, "loginPanel");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(285, 327, 154, 66);
		welcomePanel.add(btnNewButton);
		
		// shows register page
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelCont, "registerPanel");
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegister.setBounds(517, 327, 154, 66);
		welcomePanel.add(btnRegister);
		
		// <=================== Login Panel ===================> 
		panelCont.add(loginPanel, "loginPanel");
		loginPanel.setLayout(null);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(121, 278, 169, 44);
		
		loginPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(121, 173, 169, 44);
		loginPanel.add(lblNewLabel_1);
		
		passField = new JTextField();
		passField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		passField.setBounds(258, 272, 169, 50);
		loginPanel.add(passField);
		passField.setColumns(10);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		usernameField.setColumns(10);
		usernameField.setBounds(258, 173, 169, 50);
		loginPanel.add(usernameField);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String pass = passField.getText();
				Role role = UserService.login(username, pass);
				if(role==null)
					JOptionPane.showMessageDialog(null, "Invalid username and/or password!", "Error message", JOptionPane.ERROR_MESSAGE);
				else
					switch(role) {
					case CLIENT:
						cl.show(panelCont, "clientPanel");
						break;
					case MANAGER:
						cl.show(panelCont, "managerPanel");
						break;
					case BEAUTICIAN:
						cl.show(panelCont, "beauticianPanel");
						break;
					case RECEPTIONIST:
						cl.show(panelCont, "receptionistPanel");
						break;
					}
			}
		});
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		loginButton.setBounds(405, 450, 154, 58);
		loginPanel.add(loginButton);
		
		JButton registrationCancelButton_1 = new JButton("Cancel");
		registrationCancelButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelCont, "welcomePanel");
			}
		});
		registrationCancelButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registrationCancelButton_1.setBounds(822, 498, 121, 40);
		loginPanel.add(registrationCancelButton_1);
		
		// <=================== Register Panel ===================>
		panelCont.add(registerPanel, "registerPanel");
		registerPanel.setLayout(null);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(90, 96, 173, 52);
		
		registerPanel.add(lblNewLabel_3);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_1.setBounds(90, 159, 173, 52);
		
		registerPanel.add(lblNewLabel_3_1);
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_2.setBounds(90, 222, 173, 52);
		
		registerPanel.add(lblNewLabel_3_2);
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_3.setBounds(90, 285, 173, 52);
		
		registerPanel.add(lblNewLabel_3_3);
		lblNewLabel_3_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_4.setBounds(90, 348, 96, 52);
		
		registerPanel.add(lblNewLabel_3_4);
		lblNewLabel_3_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_5.setBounds(519, 96, 173, 52);
		
		registerPanel.add(lblNewLabel_3_5);
		lblNewLabel_3_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_6.setBounds(519, 159, 173, 52);
		
		registerPanel.add(lblNewLabel_3_6);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameField.setBounds(246, 96, 159, 40);
		registerPanel.add(nameField);
		nameField.setColumns(10);
		
		surnameField = new JTextField();
		surnameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		surnameField.setColumns(10);
		surnameField.setBounds(246, 159, 159, 40);
		registerPanel.add(surnameField);
		
		phoneNumberField = new JTextField();
		phoneNumberField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		phoneNumberField.setColumns(10);
		phoneNumberField.setBounds(246, 285, 159, 40);
		registerPanel.add(phoneNumberField);
		
		addressField = new JTextField();
		addressField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addressField.setColumns(10);
		addressField.setBounds(246, 348, 159, 40);
		registerPanel.add(addressField);
		
		registrationUsernameField = new JTextField();
		registrationUsernameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		registrationUsernameField.setColumns(10);
		registrationUsernameField.setBounds(646, 96, 159, 40);
		registerPanel.add(registrationUsernameField);
		
		registrationPasswordField = new JTextField();
		registrationPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		registrationPasswordField.setColumns(10);
		registrationPasswordField.setBounds(646, 159, 159, 40);
		registerPanel.add(registrationPasswordField);
		
		String[] sexStrings = { "MALE", "FEMALE"};
		JComboBox sexComboBox = new JComboBox(sexStrings);
		sexComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sexComboBox.setBounds(246, 222, 159, 41);
		registerPanel.add(sexComboBox);
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String surname = surnameField.getText();
				Sex sex = Sex.valueOf((String) sexComboBox.getSelectedItem());
				String phoneNumber = phoneNumberField.getText();
				String address = addressField.getText();
				String rUsername = registrationUsernameField.getText();
				String rPassword = registrationPasswordField.getText();
				
				boolean inputValid = checkRegistrationForm(name, surname, phoneNumber, address, rUsername, rPassword);
				
				if(inputValid)
					UserService.registerClient(name, surname, sex, phoneNumber, address, rUsername, rPassword);
				
				cl.show(panelCont, "clientPanel");
			}
		});
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		registerButton.setBounds(400, 451, 154, 58);
		
		registerPanel.add(registerButton);
		registrationCancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelCont, "welcomePanel");
			}
		});
		registrationCancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registrationCancelButton.setBounds(819, 495, 121, 40);
		
		registerPanel.add(registrationCancelButton);
		
		// <=================== Client Panel ===================>
		panelCont.add(clientPanel, "clientPanel");
		
		// <=================== Manager Panel ===================>
		
		panelCont.add(managerPanel, "managerPanel");
		managerPanel.setLayout(null);
		viewManagersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				managerCRUDWindow = new ManagerCRUDWindow(InitialWindow.this);
				managerCRUDWindow.setVisible(true);
			}
		});
		viewManagersButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewManagersButton.setBounds(110, 73, 195, 58);
		managerPanel.add(viewManagersButton);
		
		viewBeauticiansButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beauticianCRUDWindow = new BeauticianCRUDWindow(InitialWindow.this);
				beauticianCRUDWindow.setVisible(true);
			}
		});
		viewBeauticiansButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewBeauticiansButton.setBounds(110, 169, 195, 58);
		managerPanel.add(viewBeauticiansButton);
		
		viewReceptionistsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				receptionistCRUDWindow = new ReceptionistCRUDWindow(InitialWindow.this);
				receptionistCRUDWindow.setVisible(true);
			}
		});
		
		viewReceptionistsButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewReceptionistsButton.setBounds(110, 264, 195, 58);
		managerPanel.add(viewReceptionistsButton);
		
		viewClientsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientCRUDWindow = new ClientCRUDWindow(InitialWindow.this);
				clientCRUDWindow.setVisible(true);
			}
		});
		viewClientsButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewClientsButton.setBounds(110, 358, 195, 58);
		managerPanel.add(viewClientsButton);
		viewAppointmentsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appointmentCRUDWindow = new AppointmentCRUDWindow(InitialWindow.this);
				appointmentCRUDWindow.setVisible(true);
			}
		});
		
		viewAppointmentsButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewAppointmentsButton.setBounds(110, 451, 195, 58);
		
		managerPanel.add(viewAppointmentsButton);
		
		// <=================== Beautician Panel ===================>
		panelCont.add(beauticianPanel, "beauticianPanel");
		
		// <=================== Receptionist Panel ===================>
		panelCont.add(receptionistPanel, "receptionistPanel");
		// the end
		frmBeautySalon.getContentPane().add(panelCont);
		frmBeautySalon.setLocation(new Point(375, 250));
		frmBeautySalon.setPreferredSize(new Dimension(1000, 600));
		frmBeautySalon.setResizable(false);
		frmBeautySalon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBeautySalon.pack();
		frmBeautySalon.setVisible(true);
	}
	
	public boolean checkRegistrationForm(String name, String surname, String phoneNumber, String address, String rUsername, String rPassword) {
		if(name.isEmpty() || surname.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || rUsername.isEmpty() || rPassword.isEmpty()) {
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
		else if(UserService.usernameTaken(rUsername)) {
			JOptionPane.showMessageDialog(null, "There already exists an account with that username!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		JOptionPane.showMessageDialog(null, "Registration successful!", "Information message", JOptionPane.INFORMATION_MESSAGE);
		return true;
	}
}


























