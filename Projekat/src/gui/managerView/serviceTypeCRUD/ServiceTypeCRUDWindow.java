package gui.managerView.serviceTypeCRUD;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import gui.managerView.ManagerMainWindow;

public class ServiceTypeCRUDWindow extends JFrame{

	private static final long serialVersionUID = -6881347678748318060L;

	private JTable table;
	private ServiceTypeModel model;
	private JScrollPane scrollPane;
	
	AddServiceTypeWindow addServiceTypeWindow;
	
	public ServiceTypeCRUDWindow() {
		setMinimumSize(new Dimension(410, 360));
		setResizable(false);
		
		model = new ServiceTypeModel();
		table = new JTable(model);
		
		table.setRowSelectionAllowed(true);
		table.setFocusable(true);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(27, 28, 177, 251);
		
		getContentPane().setLayout(null);
		getContentPane().add(scrollPane);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addServiceTypeWindow = new AddServiceTypeWindow(ServiceTypeCRUDWindow.this);
				addServiceTypeWindow.setVisible(true);
			}
		});
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addButton.setBounds(215, 28, 156, 51);
		getContentPane().add(addButton);
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            if(table.getSelectedRow() != -1) {
	               model.removeRow(table.getSelectedRow());
	               JOptionPane.showMessageDialog(null, "Service type deleted successfully!");
	            }
			}
		});
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		removeButton.setBounds(215, 108, 156, 51);
		getContentPane().add(removeButton);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerMainWindow managerMainWindow = new ManagerMainWindow();
				managerMainWindow.setVisible(true);
				dispose();
			}
		});
		goBackButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		goBackButton.setBounds(214, 228, 156, 51);
		getContentPane().add(goBackButton);
		
		setTitle("Service Type CRUD");
		setLocationRelativeTo(null);
	}
	
	public void refreshTable()
	{	
		model.fireTableDataChanged();
	}
}
