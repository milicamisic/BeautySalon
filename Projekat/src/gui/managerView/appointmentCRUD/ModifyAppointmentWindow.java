package gui.managerView.appointmentCRUD;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
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

public class ModifyAppointmentWindow extends JDialog {
	
	private static final long serialVersionUID = 6942042170462061710L;
	private JTextField dateField;
	private JTextField timeField;
	private JComboBox<String> beauticianComboBox;
	private JComboBox<String> serviceComboBox;
	private BeautySalon beautySalon;
	private BeauticianService beauticianService;

	public ModifyAppointmentWindow(AppointmentCRUDWindow parent, int rowIndex) {
		super(parent, true);
		beautySalon = BeautySalon.getBeautySalon();
		beauticianService = new BeauticianService();
		
		Appointment appointmentForModification = beautySalon.getAppointments().get(rowIndex);
		LocalDateTime dateTime = appointmentForModification.getTimeslot().getStartTime();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.y H:m");
        String[] dateTimeString = dateTime.format(formatter).split(" ");
		
		setMinimumSize(new Dimension(980, 455));
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel beauticianLabel = new JLabel("Beautician:");
		beauticianLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		beauticianLabel.setBounds(62, 66, 127, 48);
		getContentPane().add(beauticianLabel);
		
		JLabel clientLabel = new JLabel("Client:");
		clientLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		clientLabel.setBounds(62, 160, 127, 48);
		getContentPane().add(clientLabel);
		
		JLabel lblPassword = new JLabel("Date:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPassword.setBounds(552, 66, 127, 48);
		getContentPane().add(lblPassword);
		
		dateField = new JTextField(dateTimeString[0]);
		dateField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		dateField.setColumns(10);
		dateField.setBounds(645, 67, 161, 48);
		getContentPane().add(dateField);
		
		JLabel lblEducationLevel = new JLabel("Time:");
		lblEducationLevel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEducationLevel.setBounds(552, 160, 189, 48);
		getContentPane().add(lblEducationLevel);
		
		timeField = new JTextField(dateTimeString[1]);
		timeField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		timeField.setColumns(10);
		timeField.setBounds(645, 161, 161, 48);
		getContentPane().add(timeField);
		
		ArrayList<String> beauticianUsernames = new ArrayList<String>();
		for(Beautician b : beautySalon.getBeauticians())
		{
			beauticianUsernames.add(b.getUsername());
		}
		beauticianComboBox = new JComboBox(beauticianUsernames.toArray());
		beauticianComboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		beauticianComboBox.setBounds(212, 66, 203, 48);
		beauticianComboBox.setSelectedItem(appointmentForModification.getBeautician().getUsername());
		beauticianComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    Beautician beautician = beauticianService.getBeauticianByUsername((String) beauticianComboBox.getSelectedItem());
                    
                    DefaultComboBoxModel<String> newModel = new DefaultComboBoxModel<>();
                    for(Service s : beauticianService.getBeauticianServices(beautician))
                    {
                    	newModel.addElement(s.getName());
                    }
                    serviceComboBox.setModel(newModel);
                }
            }
        });
		getContentPane().add(beauticianComboBox);
		
		
		ArrayList<String> clientUsernames = new ArrayList<String>();
		for(Client c : beautySalon.getClients())
		{
			clientUsernames.add(c.getUsername());
		}
		JComboBox clientComboBox = new JComboBox(clientUsernames.toArray());
		clientComboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		clientComboBox.setBounds(212, 160, 203, 48);
		clientComboBox.setSelectedItem(appointmentForModification.getClient().getUsername());
		getContentPane().add(clientComboBox);
		
		JLabel lblAddress = new JLabel("Service:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAddress.setBounds(62, 257, 127, 48);
		getContentPane().add(lblAddress);
		
		
		ArrayList<String> servicesString = new ArrayList<String>();
		ArrayList<Service> services = beauticianService.getBeauticianServices(beautySalon.getBeauticians().get(0));
		
		for(Service s :  services)
		{
			servicesString.add(s.getName());
		}
		serviceComboBox = new JComboBox(servicesString.toArray());
		serviceComboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		serviceComboBox.setBounds(212, 257, 281, 48);
		serviceComboBox.setSelectedItem(appointmentForModification.getService().getName());
		getContentPane().add(serviceComboBox);
		
		JButton modifyButton = new JButton("Modify");
		modifyButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ClientService clientService = new ClientService();
				ServiceService serviceService = new ServiceService();
				
				Beautician beautician = beauticianService.getBeauticianByUsername((String) beauticianComboBox.getSelectedItem());
				Client client = clientService.getClientByUsername((String) clientComboBox.getSelectedItem());
				Service service = serviceService.getServiceByName((String) serviceComboBox.getSelectedItem());
				
				String dateString = dateField.getText();
				String timeString = timeField.getText();
				
				boolean inputValid = valideFields(dateString, timeString);
				
				if(inputValid) 
				{
					// ovde pokusavamo da napravimo appointment i izbacujemo odgovarajucu gresku ako nesto ne valja
					String dateTimeString = dateString + " " + timeString;
					DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.y H:m");
					
					LocalDateTime startTime = LocalDateTime.parse(dateTimeString, dateFormatter);
					LocalDateTime endTime = startTime.plusMinutes(service.getDurationInMinutes());
					
					Timeslot timeslot = new Timeslot(startTime, endTime);
					
					Appointment appointment = new Appointment(appointmentForModification.getId(), beautician, client, timeslot, service, AppointmentStatus.SCHEDULED);
					
					ManagerService managerService = new ManagerService();
					int result = managerService.modifyAppointment(appointment);
					
					switch(result) 
					{
						case -3: JOptionPane.showMessageDialog(null, "Client is not available at that time!", "Error message", JOptionPane.ERROR_MESSAGE); break;
						case -2: JOptionPane.showMessageDialog(null, "No available beauticians at that time!", "Error message", JOptionPane.ERROR_MESSAGE); break;
						case -1: JOptionPane.showMessageDialog(null, "Beautician is not available at that time!", "Error message", JOptionPane.ERROR_MESSAGE); break;
						default: 
							JOptionPane.showMessageDialog(null, "Appointment modified!", "Information message", JOptionPane.INFORMATION_MESSAGE);
							parent.refreshTable();
							dispose();
							break;
					}
				}
			}
		});
		modifyButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		modifyButton.setBounds(579, 356, 156, 51);
		getContentPane().add(modifyButton);
		
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
	
	private boolean valideFields(String dateString, String timeString)
	{
		if(dateString.trim().isEmpty() || timeString.trim().isEmpty()) 
		{
			JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.y");
		
		try
		{
			LocalDate.parse(dateString, dateFormatter);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Date must be in format \"day.month.year\"!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");

        try 
        {
            LocalTime.parse(timeString, timeFormatter);
        } 
        catch (DateTimeParseException e) 
        {
        	JOptionPane.showMessageDialog(null, "Time must be in format \"hour:minute\"!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        
        return true;
	}
}
