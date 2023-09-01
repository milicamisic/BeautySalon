package gui.receptionistView.appointmentScheduling;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gui.clientView.ClientMainWindow;
import humanEntities.Beautician;
import humanEntities.Client;
import otherEntities.Appointment;
import otherEntities.Service;
import paket1.BeautySalon;
import service.AppointmentService;
import service.ClientService;

public class ScheduleAppointmentWindow extends JFrame {

	private static final long serialVersionUID = -6405744801443286092L;
	
	private static BeautySalon beautySalon;
	private static ClientService clientService;
	
	private JTextField beauticianTextField;
	private JTextField serviceTextField;
	private JTextField dateTextField;
	
	ArrayList<Appointment> availableAppointments;

	public ScheduleAppointmentWindow(Service service, Beautician beautician, ArrayList<Service> appropriateServices) {
		setMinimumSize(new Dimension(790, 315));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		beautySalon = BeautySalon.getBeautySalon();
		clientService = new ClientService();
		availableAppointments = null;
		
		getContentPane().setLayout(null);
		
		if(beautician != null) {
			beauticianTextField = new JTextField(beautician.getUsername());	
		} else {
			beauticianTextField = new JTextField("[not selected]");
		}
		
		beauticianTextField.setEditable(false);
		beauticianTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		beauticianTextField.setColumns(10);
		beauticianTextField.setBounds(529, 43, 216, 48);
		getContentPane().add(beauticianTextField);
		
		JLabel serviceLabel = new JLabel("Service:");
		serviceLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		serviceLabel.setBounds(37, 42, 97, 48);
		getContentPane().add(serviceLabel);
		
		JLabel beauticianLabel = new JLabel("Beautician:");
		beauticianLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		beauticianLabel.setBounds(398, 42, 140, 48);
		getContentPane().add(beauticianLabel);
		
		serviceTextField = new JTextField(service.getName());
		serviceTextField.setEditable(false);
		serviceTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		serviceTextField.setColumns(10);
		serviceTextField.setBounds(144, 43, 235, 48);
		getContentPane().add(serviceTextField);
		
		JLabel dateLabel = new JLabel("Date:");
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		dateLabel.setBounds(37, 136, 97, 48);
		getContentPane().add(dateLabel);
		
		dateTextField = new JTextField();
		dateTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		dateTextField.setColumns(10);
		dateTextField.setBounds(144, 137, 161, 48);
		getContentPane().add(dateTextField);
		
		JComboBox<String> timeComboBox = new JComboBox<String>();
		timeComboBox.setBounds(529, 136, 161, 48);
		getContentPane().add(timeComboBox);
		
		dateTextField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				availableAppointments = generateAppointments(service, beautician, dateTextField.getText().trim(), timeComboBox);		
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});
		
		JLabel timeLabel = new JLabel("Time:");
		timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		timeLabel.setBounds(398, 135, 97, 48);
		getContentPane().add(timeLabel);
		
		JButton scheduleButton = new JButton("Schedule");
		scheduleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean inputValid = validateFields(dateTextField.getText());
				if(inputValid) {
					Appointment appointment = availableAppointments.get(timeComboBox.getSelectedIndex());
					clientService.makeAppointment(appointment);
					JOptionPane.showMessageDialog(null, "AppointmentScheduled!", "Info message", JOptionPane.INFORMATION_MESSAGE);
					ClientMainWindow clientMainWindow = new ClientMainWindow();
					clientMainWindow.setVisible(true);
					dispose();
				}
			}
		});
		scheduleButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scheduleButton.setBounds(172, 215, 147, 48);
		getContentPane().add(scheduleButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PickServiceWindow pickServiceWindow = new PickServiceWindow(appropriateServices);
				pickServiceWindow.setVisible(true);
				dispose();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cancelButton.setBounds(344, 215, 147, 48);
		getContentPane().add(cancelButton);
		
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	PickServiceWindow pickServiceWindow = new PickServiceWindow(appropriateServices);
				pickServiceWindow.setVisible(true);
				dispose();
            }
        });
	}
	
	private static ArrayList<Appointment> generateAppointments(Service service, Beautician beautician, String dateString, JComboBox<String> timeComboBox) {
		LocalDate date = null;
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");
		
		try
		{
			date = LocalDate.parse(dateString, dateFormatter);
			if(date.isBefore(LocalDate.now())) {
				JOptionPane.showMessageDialog(null, "Date must be in the future!", "Error message", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Date must be in format \"day.month.year\"!", "Error message", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		AppointmentService appointmentService = new AppointmentService();
		Client client = clientService.getClientByUsername(beautySalon.getCurrentUser().getUsername());
		ArrayList<Appointment> availableAppointments = appointmentService.getAllPossibleAppointmentsForDay(client, date, service);
		
		if(availableAppointments != null) {
			DefaultComboBoxModel<String> newModel = new DefaultComboBoxModel<>();
	        for(Appointment a : availableAppointments)
	        {
	        	newModel.addElement(a.getTimeslot().getStartTime().format(timeFormatter));
	        }
	        timeComboBox.setModel(newModel);
		} else {
			JOptionPane.showMessageDialog(null, "No available appointment time for that day!", "Error message", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		return availableAppointments;
	}
	
	private boolean validateFields(String dateString) {
		LocalDate date = null;
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		
		try
		{
			date = LocalDate.parse(dateString, dateFormatter);
			if(date.isBefore(LocalDate.now())) {
				JOptionPane.showMessageDialog(null, "Date must be in the future!", "Error message", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Date must be in format \"day.month.year\"!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
}
