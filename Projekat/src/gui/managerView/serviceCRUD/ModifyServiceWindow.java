package gui.managerView.serviceCRUD;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import otherEntities.Service;
import otherEntities.ServiceType;
import paket1.BeautySalon;
import service.ManagerService;
import service.ServiceService;

public class ModifyServiceWindow extends JFrame {
	
	private static final long serialVersionUID = 2114854761708091437L;
	
	private JTextField nameTextField;
	private JTextField durationTextField;
	private JTextField priceTextField;
	private JComboBox<String> serviceTypeComboBox;
	
	private BeautySalon beautySalon;
	private ManagerService managerService;

	public ModifyServiceWindow(ServiceCRUDWindow parent, int rowIndex) {
		
		beautySalon = BeautySalon.getBeautySalon();
		Service serviceForModification = beautySalon.getServices().get(rowIndex);
		
		setMinimumSize(new Dimension(880, 455));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		nameTextField = new JTextField(serviceForModification.getName());
		nameTextField.setEditable(false);
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
		
		JLabel lblModifyress = new JLabel("Service Type:");
		lblModifyress.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblModifyress.setBounds(62, 125, 189, 48);
		getContentPane().add(lblModifyress);
		
		
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
		
		JButton addButton = new JButton("Modify");
		addButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ServiceService serviceService = new ServiceService();
				ServiceType serviceType = serviceService.getServiceTypeByName((String)serviceTypeComboBox.getSelectedItem());
				
				boolean inputValid = validateFields(durationTextField.getText(), priceTextField.getText());
				
				if(inputValid) {
					int duration = Integer.parseInt(durationTextField.getText());
					double price = Double.parseDouble(priceTextField.getText());
					
					Service service = new Service(serviceForModification.getName(), serviceType, duration, price);
					ManagerService managerService = new ManagerService();
					managerService.modifyService(service);
					
					JOptionPane.showMessageDialog(null, "Service successfully modified!", "Information message", JOptionPane.INFORMATION_MESSAGE);
					
					ServiceCRUDWindow serviceCRUDWindow = new ServiceCRUDWindow();
					serviceCRUDWindow.setVisible(true);
					dispose();
				}
			}
		});
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addButton.setBounds(513, 356, 156, 51);
		getContentPane().add(addButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
	}
	
	private boolean validateFields(String durationString, String priceString)
	{
		if(durationString.trim().isEmpty() || priceString.trim().isEmpty()) 
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


