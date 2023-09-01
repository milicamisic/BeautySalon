package gui.receptionistView;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import gui.general.LoginWindow;
import gui.receptionistView.appointmentScheduling.ScheduleAppointmentWindow;
import gui.receptionistView.appointmentScheduling.SearchServiceWindow;
import gui.receptionistView.appointmentViewing.ViewAppointmentsWindow;

public class ReceptionistMainWindow extends JFrame {

	private static final long serialVersionUID = -2451240142961601646L;
	
	public ReceptionistMainWindow() {
		getContentPane().setLayout(null);
		setTitle("Receptionist Main Window");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(804,490);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JButton appointmentsButton = new JButton("Appointments");
		appointmentsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAppointmentsWindow viewAppointmentsWindow = new ViewAppointmentsWindow();
				viewAppointmentsWindow.setVisible(true);
				dispose();
			}
		});
		appointmentsButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		appointmentsButton.setBounds(67, 51, 291, 58);
		getContentPane().add(appointmentsButton);
		
		JButton scheduleAppointmentButton = new JButton("Schedule Appointment");
		scheduleAppointmentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchServiceWindow searchServiceWindow = new SearchServiceWindow();
				searchServiceWindow.setVisible(true);
				dispose();
			}
		});
		scheduleAppointmentButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		scheduleAppointmentButton.setBounds(67, 148, 291, 58);
		getContentPane().add(scheduleAppointmentButton);
		
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
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	LoginWindow loginWindow = new LoginWindow();
				loginWindow.setVisible(true);
				dispose();
            }
        });
	}

}
