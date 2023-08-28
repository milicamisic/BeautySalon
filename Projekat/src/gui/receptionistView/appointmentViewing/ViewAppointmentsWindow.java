package gui.receptionistView.appointmentViewing;

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

import gui.receptionistView.ReceptionistMainWindow;
import paket1.BeautySalon;

public class ViewAppointmentsWindow extends JFrame {

	private static final long serialVersionUID = -671125931992957826L;
	
	private JTable table;
	private AppointmentModel model;
	private JScrollPane scrollPane;

	public ViewAppointmentsWindow() {
		setResizable(false);
		
		model = new AppointmentModel();
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
				ReceptionistMainWindow receptionistMainWindow = new ReceptionistMainWindow();
				receptionistMainWindow.setVisible(true);
				dispose();
			}
		});
		goBackButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		goBackButton.setBounds(963, 501, 156, 51);
		getContentPane().add(goBackButton);
		
		JButton cancelAppointmentButton = new JButton("Cancel Appointment");
		cancelAppointmentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel the appointment?", "Confirmation", JOptionPane.YES_NO_OPTION);

			        if (result == JOptionPane.YES_OPTION) {
			        	 boolean removed = model.removeRow(table.getSelectedRow());
			        	 if(removed) {
			        		 JOptionPane.showMessageDialog(null, "Appointment canceled successfully!");
			        	 }
			        	 else JOptionPane.showMessageDialog(null, "You can only cancel a scheduled appointment!", "Error message", JOptionPane.ERROR_MESSAGE);
			        }
		    	}
			}
		});
		cancelAppointmentButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cancelAppointmentButton.setBounds(27, 489, 264, 51);
		getContentPane().add(cancelAppointmentButton);
		
		setSize(1161, 600);
		setTitle("Appointment View");
	}
}
