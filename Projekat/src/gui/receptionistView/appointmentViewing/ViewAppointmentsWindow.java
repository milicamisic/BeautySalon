package gui.receptionistView.appointmentViewing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import gui.receptionistView.ReceptionistMainWindow;
import otherEntities.Service;
import otherEntities.ServiceType;
import paket1.BeautySalon;

public class ViewAppointmentsWindow extends JFrame {

	private static final long serialVersionUID = -671125931992957826L;
	
	private BeautySalon beautySalon;
	
	private JTable table;
	private AppointmentModel model;
	private JScrollPane scrollPane;
	private JTextField fromPriceField;
	private JTextField toPriceField;

	public ViewAppointmentsWindow() {
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		beautySalon = BeautySalon.getBeautySalon();
		
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
		
		JLabel filterLabel = new JLabel("Filters");
		filterLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		filterLabel.setBounds(1269, 28, 68, 51);
		getContentPane().add(filterLabel);
		
		JLabel serviceTypeLabel = new JLabel("Service Type:");
		serviceTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		serviceTypeLabel.setBounds(1156, 79, 161, 48);
		getContentPane().add(serviceTypeLabel);
		
		ArrayList<String> serviceTypesString = new ArrayList<String>();
		ArrayList<ServiceType> serviceTypes = beautySalon.getServiceTypes();
		
		for(ServiceType st :  serviceTypes)
		{
			serviceTypesString.add(st.getType());
		}
		serviceTypesString.add("<no filter>");
		JComboBox serviceTypeComboBox = new JComboBox(serviceTypesString.toArray());
		serviceTypeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		serviceTypeComboBox.setBounds(1156, 138, 281, 48);
		getContentPane().add(serviceTypeComboBox);
		
		JLabel serviceLabel = new JLabel("Service:");
		serviceLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		serviceLabel.setBounds(1156, 197, 161, 48);
		getContentPane().add(serviceLabel);
		
		ArrayList<String> servicesString = new ArrayList<String>();
		ArrayList<Service> services = beautySalon.getServices();
		for(Service s :  services)
		{
			servicesString.add(s.getName());
		}
		servicesString.add("<no filter>");
		JComboBox serviceComboBox = new JComboBox(servicesString.toArray());
		serviceComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		serviceComboBox.setBounds(1156, 256, 281, 48);
		getContentPane().add(serviceComboBox);
		
		JLabel priceLabel = new JLabel("Price Range:");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		priceLabel.setBounds(1156, 315, 161, 48);
		getContentPane().add(priceLabel);
		
		fromPriceField = new JTextField();
		fromPriceField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fromPriceField.setBounds(1156, 370, 103, 48);
		getContentPane().add(fromPriceField);
		fromPriceField.setColumns(10);
		
		toPriceField = new JTextField();
		toPriceField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toPriceField.setColumns(10);
		toPriceField.setBounds(1318, 370, 103, 48);
		getContentPane().add(toPriceField);
		
		JLabel dashLabel = new JLabel(" - ");
		dashLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		dashLabel.setBounds(1269, 374, 39, 30);
		getContentPane().add(dashLabel);
		
		JButton filterButton = new JButton("Filter Appointments");
		filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String service = (String) serviceComboBox.getSelectedItem();
				String serviceType = (String) serviceTypeComboBox.getSelectedItem();
				
				boolean inputValid = validateFields(fromPriceField.getText(), toPriceField.getText());
				
				if(inputValid) {
					if(fromPriceField.getText().trim().isEmpty())
						fromPriceField.setText("0.0");
					if(toPriceField.getText().trim().isEmpty())
						toPriceField.setText(Double.toString(Double.MAX_VALUE));
					
					double fromPrice = Double.parseDouble(fromPriceField.getText());
					double toPrice = Double.parseDouble(toPriceField.getText());
					
					model.applyFilters(service, serviceType, fromPrice, toPrice);
				}
			}
		});
		filterButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		filterButton.setBounds(1184, 439, 237, 51);
		getContentPane().add(filterButton);
		
		setSize(1484, 600);
		setTitle("Appointment View");
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	ReceptionistMainWindow receptionistMainWindow = new ReceptionistMainWindow();
				receptionistMainWindow.setVisible(true);
				dispose();
            }
        });
	}
	
	private boolean validateFields(String fromPrice, String toPrice)
	{
		if(!fromPrice.trim().isEmpty()) {
			try
			{
				Double.parseDouble(fromPrice);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Price must be a double!", "Error message", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		if(!toPrice.trim().isEmpty()) {
			try
			{
				Double.parseDouble(toPrice);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Price must be a double!", "Error message", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
        
        return true;
	}
}
