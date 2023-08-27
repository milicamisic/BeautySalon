package gui.clientView.appointmentScheduling;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import humanEntities.Beautician;
import humanEntities.Client;
import otherEntities.Service;
import paket1.BeautySalon;
import service.BeauticianService;
import service.ServiceService;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class PickServiceWindow extends JFrame {

	private BeautySalon beautySalon;
	private BeauticianService beauticianService;
	private JComboBox<String> beauticianComboBox;
	private ServiceService serviceService;
	private JComboBox<String> serviceComboBox;
	private boolean autoBeautician = true;
	
	public PickServiceWindow(ArrayList<Service> appropriateServices) {
		beautySalon = BeautySalon.getBeautySalon();
		beauticianService = new BeauticianService();
		serviceService =  new ServiceService();
		
		setMinimumSize(new Dimension(975, 240));
		setPreferredSize(new Dimension(975, 316));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblService = new JLabel("Service:");
		lblService.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblService.setBounds(51, 51, 161, 48);
		getContentPane().add(lblService);
		
		ArrayList<String> appropriateServicesString = new ArrayList<String>();
		for(Service s : appropriateServices) {
			appropriateServicesString.add(s.getName());
		}
		serviceComboBox = new JComboBox(appropriateServicesString.toArray());
		serviceComboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		serviceComboBox.setBounds(260, 51, 281, 48);
		getContentPane().add(serviceComboBox);
		
		JLabel beauticianLabel = new JLabel("Beautician:");
		beauticianLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		beauticianLabel.setBounds(51, 134, 161, 48);
		
		ArrayList<String> beauticiansString = new ArrayList<String>();
		for(Beautician b : beautySalon.getBeauticians()) {
			if(b.hasSkill(appropriateServices.get(0).getType())) {
				beauticiansString.add(b.getUsername());
			}
		}
		beauticianComboBox = new JComboBox(beauticiansString.toArray());
		beauticianComboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		beauticianComboBox.setBounds(260, 134, 281, 48);
		
		if (JOptionPane.showConfirmDialog(null, "Do you want to choose the beautician?", "Decision", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
		{
			getContentPane().add(beauticianComboBox);
			getContentPane().add(beauticianLabel);
		    autoBeautician = false;
		}
		
		JButton pickButton = new JButton("Pick");
		pickButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Beautician beautician = beauticianService.getBeauticianByUsername((String) beauticianComboBox.getSelectedItem());
				Service service = serviceService.getServiceByName((String) serviceComboBox.getSelectedItem());
				
				if(autoBeautician) beautician = null;
				
				ScheduleAppointmentWindow scheduleAppointmentWindow = new ScheduleAppointmentWindow(service, beautician, appropriateServices);
				scheduleAppointmentWindow.setVisible(true);
				dispose();
			}
		});
		pickButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pickButton.setBounds(723, 48, 156, 51);
		getContentPane().add(pickButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchServiceWindow searchServiceWindow = new SearchServiceWindow();
				searchServiceWindow.setVisible(true);
				dispose();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cancelButton.setBounds(723, 134, 156, 51);
		getContentPane().add(cancelButton);
		setLocationRelativeTo(null);
	}
}
