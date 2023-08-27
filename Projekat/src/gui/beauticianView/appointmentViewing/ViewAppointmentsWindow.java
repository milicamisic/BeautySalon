package gui.beauticianView.appointmentViewing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import gui.beauticianView.BeauticianMainWindow;
import gui.beauticianView.BeauticianMainWindow;
import gui.beauticianView.appointmentViewing.AppointmentModel;
import humanEntities.Beautician;
import paket1.BeautySalon;
import service.BeauticianService;

public class ViewAppointmentsWindow extends JFrame{
	
	private static final long serialVersionUID = -37585810608145216L;
	
	private JTable table;
	private AppointmentModel model;
	private JScrollPane scrollPane;
	
	private BeautySalon beautySalon;
	private BeauticianService beauticianService;
	private Beautician beautician;

	public ViewAppointmentsWindow() {
		setResizable(false);
		
		beautySalon = BeautySalon.getBeautySalon();
		beauticianService = new BeauticianService();
		beautician = beauticianService.getBeauticianByUsername(beautySalon.getCurrentUser().getUsername());
		
		
		model = new AppointmentModel(beautician);
		table = new JTable(model);
		
		table.setRowSelectionAllowed(true);
		table.setFocusable(true);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(27, 28, 1092, 433);
		
		getContentPane().setLayout(null);
		getContentPane().add(scrollPane);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeauticianMainWindow beauticianMainWindow = new BeauticianMainWindow();
				beauticianMainWindow.setVisible(true);
				dispose();
			}
		});
		goBackButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		goBackButton.setBounds(963, 501, 156, 51);
		getContentPane().add(goBackButton);
		
		setSize(1161, 600);
		setTitle("Appointment View");
		setLocationRelativeTo(null);
	}
}

