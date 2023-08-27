package gui.general;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gui.beauticianView.BeauticianMainWindow;
import gui.clientView.ClientMainWindow;
import gui.managerView.ManagerMainWindow;
import humanEntities.Role;
import paket1.MainWindow;
import service.UserService;

public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 1073139784638961039L;
	
	private JPanel contentPanel = new JPanel();
	private JTextField usernameField;
	private JPasswordField passwordField;

	public LoginWindow() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 377, 468);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		{
			JLabel usernameLabel = new JLabel("Username:");
			usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
			usernameLabel.setBounds(33, 73, 127, 48);
			contentPanel.add(usernameLabel);
		}
		{
			JLabel passwordLabel = new JLabel("Password:");
			passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
			passwordLabel.setBounds(33, 170, 127, 48);
			contentPanel.add(passwordLabel);
		}
		{
			usernameField = new JTextField();
			usernameField.setFont(new Font("Tahoma", Font.PLAIN, 25));
			usernameField.setBounds(162, 73, 175, 48);
			contentPanel.add(usernameField);
			usernameField.setColumns(10);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setFont(new Font("Tahoma", Font.PLAIN, 25));
			passwordField.setBounds(162, 170, 175, 48);
			contentPanel.add(passwordField);
		}
		{
			JButton loginButton = new JButton("Login");
			loginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login();
				}
			});
			loginButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
			loginButton.setBounds(109, 263, 149, 53);
			contentPanel.add(loginButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switchToMainWindow();
				}
			});
			cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
			cancelButton.setBounds(109, 344, 149, 53);
			contentPanel.add(cancelButton);
		}
		setLocationRelativeTo(null);
	}
	
	protected void login() {
		String username = usernameField.getText();
		char[] pass = passwordField.getPassword();
		Role role = UserService.login(username, pass);
		if(role==null)
			JOptionPane.showMessageDialog(null, "Invalid username and/or password!", "Error message", JOptionPane.ERROR_MESSAGE);
		else {
			switch(role) {
			case CLIENT:
				ClientMainWindow clientMainWindow = new ClientMainWindow();
				clientMainWindow.setVisible(true);
				dispose();
				break;
			case MANAGER:
				ManagerMainWindow managerMainWindow = new ManagerMainWindow();
				managerMainWindow.setVisible(true);
				dispose();
				break;
			case BEAUTICIAN:
				BeauticianMainWindow beauticianMainWindow = new BeauticianMainWindow();
				beauticianMainWindow.setVisible(true);
				dispose();
				break;
			case RECEPTIONIST:
				
				break;
			}
		}
	}

	private void switchToMainWindow() {
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
		dispose();
	}
}
