package gui.clientView;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import gui.clientView.appointmentScheduling.SearchServiceWindow;
import gui.clientView.appointmentViewing.ViewAppointmentsWindow;
import gui.general.LoginWindow;

public class ClientMainWindow extends JFrame{
	
	private static final long serialVersionUID = 7921575468057779347L;

	public ClientMainWindow() {
		getContentPane().setLayout(null);
		setTitle("Client Main Window");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(804,490);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JButton appointmentsButton = new JButton("My Appointments");
		appointmentsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAppointmentsWindow viewAppointmentsWindow = new ViewAppointmentsWindow(ClientMainWindow.this);
				viewAppointmentsWindow.setVisible(true);
			}
		});
		appointmentsButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		appointmentsButton.setBounds(67, 51, 291, 58);
		getContentPane().add(appointmentsButton);
		
		JButton scheduleAppointmentButton = new JButton("Schedule Appointment");
		scheduleAppointmentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToScheduleAppointmentWindow();
			}
		});
		scheduleAppointmentButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		scheduleAppointmentButton.setBounds(67, 148, 291, 58);
		getContentPane().add(scheduleAppointmentButton);
		
		JButton loyaltyCardButton = new JButton("Loyalty Card");
		loyaltyCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		loyaltyCardButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		loyaltyCardButton.setBounds(67, 250, 291, 58);
		getContentPane().add(loyaltyCardButton);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindow loginWindow = new LoginWindow();
				loginWindow.setVisible(true);
				dispose();
			}
		});
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		logoutButton.setBounds(583, 393, 197, 49);
		getContentPane().add(logoutButton);
		
	}
	
	protected void switchToScheduleAppointmentWindow() {
		SearchServiceWindow searchServiceWindow = new SearchServiceWindow();
		searchServiceWindow.setVisible(true);
		dispose();
	}
}
