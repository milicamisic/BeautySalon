package gui.managerView.serviceCRUD;

import java.awt.Dimension;
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
import javax.swing.JTextField;

import gui.managerView.ManagerMainWindow;
import otherEntities.Service;
import otherEntities.ServiceType;
import paket1.BeautySalon;
import service.ManagerService;
import service.ServiceService;

public class AddServiceWindow extends JFrame {
	
	private static final long serialVersionUID = -5166764318543446530L;
	
	private JTextField nameTextField;
	private JTextField durationTextField;
	private JTextField priceTextField;
	private JComboBox<String> serviceTypeComboBox;
	
	private BeautySalon beautySalon;
	private ManagerService managerService;

	public AddServiceWindow() {
		
		beautySalon = BeautySalon.getBeautySalon();
		managerService = new ManagerService();
		
		setMinimumSize(new Dimension(880, 455));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		nameTextField.setColumns(10);
		nameTextField.setBounds(259, 45, 364, 48);
		getContentPane().add(nameTextField);
		
		JLabel durationLabel = new JLabel("Duration:");
		durationLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		durationLabel.setBounds(62, 205, 189, 48);
		getContentPane().add(durationLabel);
		
		durationTextField = new JTextField();
		durationTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		durationTextField.setColumns(10);
		durationTextField.setBounds(259, 206, 161, 48);
		getContentPane().add(durationTextField);
		
		JLabel lblAddress = new JLabel("Service Type:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAddress.setBounds(62, 125, 189, 48);
		getContentPane().add(lblAddress);
		
		
		ArrayList<String> serviceTypesString = new ArrayList<String>();
		ArrayList<ServiceType> serviceTypes = beautySalon.getServiceTypes();
		
		for(ServiceType st :  serviceTypes)
		{
			serviceTypesString.add(st.getType());
		}
		serviceTypeComboBox = new JComboBox(serviceTypesString.toArray());
		serviceTypeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		serviceTypeComboBox.setBounds(259, 125, 281, 48);
		getContentPane().add(serviceTypeComboBox);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ServiceService serviceService = new ServiceService();
				ServiceType serviceType = serviceService.getServiceTypeByName((String)serviceTypeComboBox.getSelectedItem());
				
				boolean inputValid = validateFields(nameTextField.getText(), durationTextField.getText(), priceTextField.getText());
				
				if(inputValid) {
					
					String name = nameTextField.getText();
					int duration = Integer.parseInt(durationTextField.getText());
					double price = Double.parseDouble(priceTextField.getText());
					
					Service service = new Service(name, serviceType, duration, price);
					boolean added = managerService.addService(service);
					if(added) {
						JOptionPane.showMessageDialog(null, "Service successfully added!", "Information message", JOptionPane.INFORMATION_MESSAGE);
						
						ServiceCRUDWindow serviceCRUDWindow = new ServiceCRUDWindow();
						serviceCRUDWindow.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Service with that name already exists!", "Error message", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addButton.setBounds(513, 356, 156, 51);
		getContentPane().add(addButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServiceCRUDWindow serviceCRUDWindow = new ServiceCRUDWindow();
				serviceCRUDWindow.setVisible(true);
				dispose();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cancelButton.setBounds(689, 356, 156, 51);
		getContentPane().add(cancelButton);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		nameLabel.setBounds(62, 44, 189, 48);
		getContentPane().add(nameLabel);
		
		JLabel priceLabel = new JLabel("Price:");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		priceLabel.setBounds(62, 285, 189, 48);
		getContentPane().add(priceLabel);
		
		priceTextField = new JTextField();
		priceTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		priceTextField.setColumns(10);
		priceTextField.setBounds(259, 286, 161, 48);
		getContentPane().add(priceTextField);
		
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	ServiceCRUDWindow serviceCRUDWindow = new ServiceCRUDWindow();
            	serviceCRUDWindow.setVisible(true);
				dispose();
            }
        });
	}
	
	private boolean validateFields(String nameString, String durationString, String priceString)
	{
		if(nameString.trim().isEmpty() || durationString.trim().isEmpty() || priceString.trim().isEmpty()) 
		{
			JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try {
			Integer.parseInt(durationString);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Duration must be an integer!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try {
			Double.parseDouble(priceString);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Price must be a double!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
}

