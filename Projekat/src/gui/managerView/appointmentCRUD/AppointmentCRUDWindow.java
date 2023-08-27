package gui.managerView.appointmentCRUD;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import gui.managerView.ManagerMainWindow;

public class AppointmentCRUDWindow extends JFrame {

	private static final long serialVersionUID = -4851142263147950673L;
	
	private JTable table;
	private AppointmentModel model;
	private JScrollPane scrollPane;
	
	AddAppointmentWindow addAppointmentWindow;
	ModifyAppointmentWindow modifyAppointmentWindow;

	public AppointmentCRUDWindow() {
		setResizable(false);
		
		model = new AppointmentModel();
		table = new JTable(model);
		
		table.setRowSelectionAllowed(true);
		table.setFocusable(true);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(27, 28, 1092, 433);
		
		getContentPane().setLayout(null);
		getContentPane().add(scrollPane);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAppointmentWindow = new AddAppointmentWindow(AppointmentCRUDWindow.this);
				addAppointmentWindow.setVisible(true);
			}
		});
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addButton.setBounds(27, 501, 156, 51);
		getContentPane().add(addButton);
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            if(table.getSelectedRow() != -1) {
	               model.removeRow(table.getSelectedRow());
	               JOptionPane.showMessageDialog(null, "Appointment deleted successfully!");
	            }
			}
		});
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		removeButton.setBounds(210, 501, 156, 51);
		getContentPane().add(removeButton);
		
		JButton modifyButton = new JButton("Modify");
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					modifyAppointmentWindow = new ModifyAppointmentWindow(AppointmentCRUDWindow.this, table.getSelectedRow());
					modifyAppointmentWindow.setVisible(true);
				}
			}
		});
		modifyButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		modifyButton.setBounds(399, 501, 156, 51);
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
		goBackButton.setBounds(963, 501, 156, 51);
		getContentPane().add(goBackButton);
		
		setSize(1161, 600);
		setTitle("Appointment CRUD");
		setLocationRelativeTo(null);
	}
	
	public void refreshTable()
	{	
		model.fireTableDataChanged();
	}
}
