package gui.managerView.serviceCRUD;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import gui.managerView.ManagerMainWindow;
import gui.managerView.serviceCRUD.AddServiceWindow;
import gui.managerView.serviceCRUD.ServiceCRUDWindow;
import gui.managerView.serviceCRUD.ServiceModel;
import gui.managerView.serviceCRUD.ModifyServiceWindow;
import java.awt.Dimension;

public class ServiceCRUDWindow extends JFrame {

	private static final long serialVersionUID = -4851142263147950673L;
	
	private JTable table;
	private ServiceModel model;
	private JScrollPane scrollPane;
	
	AddServiceWindow addServiceWindow;
	ModifyServiceWindow modifyServiceWindow;

	public ServiceCRUDWindow() {
		setMinimumSize(new Dimension(940, 470));
		setResizable(false);
		
		model = new ServiceModel();
		table = new JTable(model);
		
		table.setRowSelectionAllowed(true);
		table.setFocusable(true);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(27, 28, 667, 370);
		
		getContentPane().setLayout(null);
		getContentPane().add(scrollPane);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addServiceWindow = new AddServiceWindow();
				addServiceWindow.setVisible(true);
				dispose();
			}
		});
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addButton.setBounds(731, 28, 156, 51);
		getContentPane().add(addButton);
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            if(table.getSelectedRow() != -1) {
	               model.removeRow(table.getSelectedRow());
	               JOptionPane.showMessageDialog(null, "Service deleted successfully!");
	            }
			}
		});
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		removeButton.setBounds(731, 103, 156, 51);
		getContentPane().add(removeButton);
		
		JButton modifyButton = new JButton("Modify");
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					modifyServiceWindow = new ModifyServiceWindow(ServiceCRUDWindow.this, table.getSelectedRow());
					modifyServiceWindow.setVisible(true);
					dispose();
				}
			}
		});
		modifyButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		modifyButton.setBounds(731, 183, 156, 51);
		getContentPane().add(modifyButton);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerMainWindow managerMainWindow = new ManagerMainWindow();
				managerMainWindow.setVisible(true);
				dispose();
			}
		});
		goBackButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		goBackButton.setBounds(731, 347, 156, 51);
		getContentPane().add(goBackButton);
		
		setSize(933, 465);
		setTitle("Service CRUD");
		setLocationRelativeTo(null);
	}
	
	public void refreshTable()
	{	
		model.fireTableDataChanged();
	}
}

