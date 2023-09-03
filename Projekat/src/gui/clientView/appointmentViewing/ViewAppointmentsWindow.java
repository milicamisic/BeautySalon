package gui.clientView.appointmentViewing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import gui.clientView.ClientMainWindow;
import humanEntities.Client;
import paket1.BeautySalon;
import service.ClientService;

public class ViewAppointmentsWindow extends JDialog{
	
	private static final long serialVersionUID = 3201256523262097041L;
	
	private JTable table;
	private AppointmentModel model;
	private JScrollPane scrollPane;
	
	private BeautySalon beautySalon;
	private ClientService clientService;
	private Client client;
	
	private JTextField totalCostTextField;

	public ViewAppointmentsWindow(ClientMainWindow parent) {
		super(parent, true);
		setResizable(false);
		
		beautySalon = BeautySalon.getBeautySalon();
		clientService = new ClientService();
		client = clientService.getClientByUsername(beautySalon.getCurrentUser().getUsername());
		
		
		model = new AppointmentModel(client);
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
			        	 boolean removed = model.cancelAppointment(table.getSelectedRow());
			        	 if(removed) {
			        		 JOptionPane.showMessageDialog(null, "Appointment cancelled!");
			        		 totalCostTextField.setText(Double.toString(client.getMoneySpent()));
			        	 }
			        	 else JOptionPane.showMessageDialog(null, "You can only cancel a scheduled appointment!", "Error message", JOptionPane.ERROR_MESSAGE);
			        }
		    	}
			}
		});
		cancelAppointmentButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cancelAppointmentButton.setBounds(27, 489, 264, 51);
		getContentPane().add(cancelAppointmentButton);
		
		JLabel lblNewLabel = new JLabel("Total cost: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(329, 489, 106, 51);
		getContentPane().add(lblNewLabel);
		
		totalCostTextField = new JTextField(Double.toString(client.getMoneySpent()));
		totalCostTextField.setEditable(false);
		totalCostTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalCostTextField.setBounds(440, 489, 114, 41);
		getContentPane().add(totalCostTextField);
		
		setSize(1161, 600);
		setTitle("Appointment View");
		
		setLocationRelativeTo(null);
	}
}

