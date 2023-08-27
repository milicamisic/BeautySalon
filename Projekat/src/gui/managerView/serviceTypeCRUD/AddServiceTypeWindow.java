package gui.managerView.serviceTypeCRUD;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import otherEntities.ServiceType;
import service.ManagerService;

public class AddServiceTypeWindow extends JDialog{

	private static final long serialVersionUID = 5755179227809707425L;

	private JTextField serviceTypeTextField;
	
	public AddServiceTypeWindow(ServiceTypeCRUDWindow parent) {
		super(parent, true);
		
		setMinimumSize(new Dimension(450, 270));
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel serviceTypeLabel = new JLabel("Service Type:");
		serviceTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		serviceTypeLabel.setBounds(68, 79, 156, 48);
		getContentPane().add(serviceTypeLabel);
		
		serviceTypeTextField = new JTextField();
		serviceTypeTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		serviceTypeTextField.setColumns(10);
		serviceTypeTextField.setBounds(234, 80, 161, 48);
		getContentPane().add(serviceTypeTextField);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String serviceTypeString = serviceTypeTextField.getText().trim();
				if(serviceTypeString.isEmpty()) {
					JOptionPane.showMessageDialog(null, "You can't leave the field blank!", "Error message", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ServiceType serviceType = new ServiceType(serviceTypeString);
				ManagerService managerService = new ManagerService();
				boolean added = managerService.addServiceType(serviceType);
				if(added) {
					JOptionPane.showMessageDialog(null, "Service type successfully added!", "Info message", JOptionPane.INFORMATION_MESSAGE);
					parent.refreshTable();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "That service type already exists!", "Error message", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addButton.setBounds(68, 163, 156, 51);
		getContentPane().add(addButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cancelButton.setBounds(244, 163, 156, 51);
		getContentPane().add(cancelButton);
		
		setLocationRelativeTo(null);
	}
}
