package gui.clientView.appointmentScheduling;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import otherEntities.Service;
import otherEntities.ServiceType;
import paket1.BeautySalon;
import service.BeauticianService;
import service.ServiceService;

public class SearchServiceWindow extends JFrame {

	private static final long serialVersionUID = -3685031624691855717L;
	
	private BeautySalon beautySalon;
	private BeauticianService beauticianService;
	private JComboBox<String> beauticianComboBox;
	private JComboBox<String> serviceComboBox;
	private JTextField toDurationField;
	private JTextField fromDurationField;
	private JTextField toPriceField;
	private JTextField fromPriceField;
	
	
	public SearchServiceWindow() {
		setMinimumSize(new Dimension(982, 416));
		beautySalon = BeautySalon.getBeautySalon();
		beauticianService = new BeauticianService();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel beauticianLabel = new JLabel("Beautician:");
		beauticianLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		beauticianLabel.setBounds(62, 66, 127, 48);
		
		ArrayList<String> beauticianUsernames = new ArrayList<String>();
		for(Beautician b : beautySalon.getBeauticians())
		{
			beauticianUsernames.add(b.getUsername());
		}
		beauticianComboBox = new JComboBox(beauticianUsernames.toArray());
		beauticianComboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		beauticianComboBox.setBounds(212, 66, 203, 48);
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
		
		JLabel serviceTypeLabel = new JLabel("Service type:");
		serviceTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		serviceTypeLabel.setBounds(41, 29, 161, 48);
		getContentPane().add(serviceTypeLabel);
		
		
		ArrayList<String> serviceTypesString = new ArrayList<String>();
		ArrayList<ServiceType> serviceTypes = beautySalon.getServiceTypes();
		for(ServiceType st : serviceTypes)
		{
			serviceTypesString.add(st.getType());
		}
		serviceComboBox = new JComboBox(serviceTypesString.toArray());
		serviceComboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		serviceComboBox.setBounds(250, 29, 281, 48);
		getContentPane().add(serviceComboBox);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ArrayList<Service> appropriateServices = new ArrayList<Service>();
				ServiceService serviceService = new ServiceService();
				
				ServiceType serviceType = serviceService.getServiceTypeByName((String) serviceComboBox.getSelectedItem());
				
				boolean inputValid = valideFields(fromDurationField.getText(), toDurationField.getText(), fromPriceField.getText(), toPriceField.getText());
				
				if(inputValid) {
					if(fromDurationField.getText().trim().isEmpty())
						fromDurationField.setText("0");
					if(toDurationField.getText().trim().isEmpty())
						toDurationField.setText(Integer.toString(Integer.MAX_VALUE));
					if(fromPriceField.getText().trim().isEmpty())
						fromPriceField.setText("0.0");
					if(toPriceField.getText().trim().isEmpty())
						toPriceField.setText(Double.toString(Double.MAX_VALUE));
					
					int fromDuration = Integer.parseInt(fromDurationField.getText());
					int toDuration = Integer.parseInt(toDurationField.getText());
					double fromPrice = Double.parseDouble(fromPriceField.getText());
					double toPrice = Double.parseDouble(toPriceField.getText());
					appropriateServices = serviceService.getAppropriateServices(fromDuration, toDuration, fromPrice, toPrice, serviceType);
					if(appropriateServices.size() == 0)
						JOptionPane.showMessageDialog(null, "No appropriate services!", "Error message", JOptionPane.ERROR_MESSAGE);
					else {
						PickServiceWindow pickServiceWindow = new PickServiceWindow(appropriateServices);
						pickServiceWindow.setVisible(true);
						dispose();	
					}
				}
				
				
			}
		});
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		searchButton.setBounds(603, 306, 156, 51);
		getContentPane().add(searchButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientMainWindow clientMainWindow = new ClientMainWindow();
				clientMainWindow.setVisible(true);
				dispose();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cancelButton.setBounds(779, 306, 156, 51);
		getContentPane().add(cancelButton);
		
		JLabel durationLabel = new JLabel("Duration");
		durationLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		durationLabel.setBounds(41, 131, 112, 48);
		getContentPane().add(durationLabel);
		
		toDurationField = new JTextField();
		toDurationField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		toDurationField.setColumns(10);
		toDurationField.setBounds(477, 132, 161, 48);
		getContentPane().add(toDurationField);
		
		JLabel lblFrom_2 = new JLabel("From:");
		lblFrom_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblFrom_2.setBounds(158, 131, 77, 48);
		getContentPane().add(lblFrom_2);
		
		JLabel lblTo_2 = new JLabel("To:");
		lblTo_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTo_2.setBounds(423, 131, 61, 48);
		getContentPane().add(lblTo_2);
		
		fromDurationField = new JTextField();
		fromDurationField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		fromDurationField.setColumns(10);
		fromDurationField.setBounds(233, 131, 161, 48);
		getContentPane().add(fromDurationField);
		
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		priceLabel.setBounds(41, 211, 112, 48);
		getContentPane().add(priceLabel);
		
		toPriceField = new JTextField();
		toPriceField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		toPriceField.setColumns(10);
		toPriceField.setBounds(477, 212, 161, 48);
		getContentPane().add(toPriceField);
		
		JLabel lblFrom_2_1 = new JLabel("From:");
		lblFrom_2_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblFrom_2_1.setBounds(158, 211, 77, 48);
		getContentPane().add(lblFrom_2_1);
		
		JLabel lblTo_2_1 = new JLabel("To:");
		lblTo_2_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTo_2_1.setBounds(423, 211, 61, 48);
		getContentPane().add(lblTo_2_1);
		
		fromPriceField = new JTextField();
		fromPriceField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		fromPriceField.setColumns(10);
		fromPriceField.setBounds(233, 211, 161, 48);
		getContentPane().add(fromPriceField);
		
		JLabel instructionLabel = new JLabel("*Fields left empty will not be considered during the search");
		instructionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		instructionLabel.setBounds(41, 306, 417, 32);
		getContentPane().add(instructionLabel);
	}
	
	private boolean valideFields(String fromDuration, String toDuration, String fromPrice, String toPrice)
	{
		if(!fromDuration.trim().isEmpty()) {
			try
			{
				Integer.parseInt(fromDuration);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Duration must be an integer!", "Error message", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		if(!toDuration.trim().isEmpty()) {
			try
			{
				Integer.parseInt(toDuration);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Duration must be an integer!", "Error message", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
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
