package gui.managerView.appointmentCRUD;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import humanEntities.Beautician;
import humanEntities.Client;
import otherEntities.Appointment;
import otherEntities.AppointmentStatus;
import otherEntities.Service;
import otherEntities.Timeslot;
import paket1.BeautySalon;
import service.BeauticianService;
import service.ClientService;
import service.ManagerService;
import service.ServiceService;

public class AddAppointmentWindow extends JDialog {
	
	private static final long serialVersionUID = 6942042170462061710L;
	private JTextField dateField;
	private JTextField timeField;
	private BeautySalon beautySalon;

	public AddAppointmentWindow(AppointmentCRUDWindow parent) {
		super(parent, true);
		beautySalon = BeautySalon.getBeautySalon();
		
		setMinimumSize(new Dimension(980, 455));
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblSurname = new JLabel("Beautician:");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSurname.setBounds(62, 66, 127, 48);
		getContentPane().add(lblSurname);
		
		JLabel lblUsername = new JLabel("Client:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblUsername.setBounds(62, 160, 127, 48);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Date:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPassword.setBounds(552, 66, 127, 48);
		getContentPane().add(lblPassword);
		
		dateField = new JTextField();
		dateField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		dateField.setColumns(10);
		dateField.setBounds(645, 67, 161, 48);
		getContentPane().add(dateField);
		
		JLabel lblAddress = new JLabel("Service:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAddress.setBounds(62, 257, 127, 48);
		getContentPane().add(lblAddress);
		
		JLabel lblEducationLevel = new JLabel("Time:");
		lblEducationLevel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEducationLevel.setBounds(552, 160, 189, 48);
		getContentPane().add(lblEducationLevel);
		
		timeField = new JTextField();
		timeField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		timeField.setColumns(10);
		timeField.setBounds(645, 161, 161, 48);
		getContentPane().add(timeField);
		
		ArrayList<String> services = new ArrayList<String>();
		for(Service s :  beautySalon.getServices())
		{
			services.add(s.getName());
		}
		JComboBox serviceComboBox = new JComboBox(services.toArray());
		serviceComboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		serviceComboBox.setBounds(212, 257, 281, 48);
		getContentPane().add(serviceComboBox);
		
		ArrayList<String> beauticianUsernames = new ArrayList<String>();
		for(Beautician b : beautySalon.getBeauticians())
		{
			beauticianUsernames.add(b.getUsername());
		}
		JComboBox beauticianComboBox = new JComboBox(beauticianUsernames.toArray());
		beauticianComboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		beauticianComboBox.setBounds(212, 66, 203, 48);
		getContentPane().add(beauticianComboBox);
		
		ArrayList<String> clientUsernames = new ArrayList<String>();
		for(Client c : beautySalon.getClients())
		{
			clientUsernames.add(c.getUsername());
		}
		JComboBox clientComboBox = new JComboBox(clientUsernames.toArray());
		clientComboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		clientComboBox.setBounds(212, 160, 203, 48);
		getContentPane().add(clientComboBox);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				BeauticianService beauticianService = new BeauticianService();
				ClientService clientService = new ClientService();
				ServiceService serviceService = new ServiceService();
				
				Beautician beautician = beauticianService.getBeauticianByUsername((String) beauticianComboBox.getSelectedItem());
				Client client = clientService.getClientByUsername((String) clientComboBox.getSelectedItem());
				Service service = serviceService.getServiceByName((String) serviceComboBox.getSelectedItem());
				
				String dateString = dateField.getText();
				String timeString = timeField.getText();
				
				boolean inputValid = valideFields(beautician, client, service, dateString, timeString);
				
				if(inputValid) 
				{
					String dateTimeString = dateString + " " + timeString;
					DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
					
					LocalDateTime startTime = LocalDateTime.parse(dateTimeString, dateFormatter);
					LocalDateTime endTime = startTime.plusMinutes(service.getDurationInMinutes());
					
					Timeslot timeslot = new Timeslot(startTime, endTime);
					
					Appointment appointment = new Appointment(0, beautician, client, timeslot, service, AppointmentStatus.SCHEDULED);
					ManagerService managerService = new ManagerService();
					managerService.addAppointment(appointment);
					
					parent.refreshTable();
					
					dispose();
				}
			}
		});
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addButton.setBounds(579, 356, 156, 51);
		getContentPane().add(addButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cancelButton.setBounds(755, 356, 156, 51);
		getContentPane().add(cancelButton);
	}
	
	private boolean valideFields(Beautician beautician, Client client, Service service, String dateString, String timeString)
	{
		if(dateString.trim().isEmpty() || timeString.trim().isEmpty()) 
		{
			JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		
		try
		{
			LocalDate.parse(dateString, dateFormatter);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Date must be in format \"day.month.year\"!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        try 
        {
            LocalTime.parse(timeString, timeFormatter);
        } 
        catch (DateTimeParseException e) 
        {
        	JOptionPane.showMessageDialog(null, "Time must be in format \"hour:minute\"!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
        }
		
		JOptionPane.showMessageDialog(null, "Appointment added!", "Information message", JOptionPane.INFORMATION_MESSAGE);
		return true;
		
	}
}
