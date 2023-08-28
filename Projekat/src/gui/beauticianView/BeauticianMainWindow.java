package gui.beauticianView;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import gui.beauticianView.appointmentViewing.ViewAppointmentsWindow;
import gui.beauticianView.skillsView.ViewSkillsWindow;
import gui.general.LoginWindow;

public class BeauticianMainWindow extends JFrame{
	
	private static final long serialVersionUID = 8249842782685964927L;
	private final JButton skillsButton = new JButton("Skills");
	
	public BeauticianMainWindow() {
		setMinimumSize(new Dimension(830, 590));
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		skillsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewSkillsWindow viewSkillsWindow = new ViewSkillsWindow();
				viewSkillsWindow.setVisible(true);
				dispose();
			}
		});
		skillsButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		skillsButton.setBounds(112, 73, 195, 58);
		getContentPane().add(skillsButton);
		
		JButton appointmentsButton = new JButton("My Appointments");
		appointmentsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAppointmentsWindow viewAppointmentsWindow = new ViewAppointmentsWindow();
				viewAppointmentsWindow.setVisible(true);
				dispose();
			}
		});
		appointmentsButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		appointmentsButton.setBounds(112, 169, 268, 58);
		getContentPane().add(appointmentsButton);
		
		JButton logoutButton = new JButton("Log Out");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindow loginWindow = new LoginWindow();
				loginWindow.setVisible(true);
				dispose();
			}
		});
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		logoutButton.setBounds(612, 484, 195, 58);
		getContentPane().add(logoutButton);
		
		setLocationRelativeTo(null);
	}
}

