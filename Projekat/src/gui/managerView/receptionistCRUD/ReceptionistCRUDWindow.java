package gui.managerView.receptionistCRUD;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import gui.managerView.ManagerMainWindow;

public class ReceptionistCRUDWindow extends JFrame {

	private static final long serialVersionUID = -4851142263147950673L;
	
	private JTable table;
	private ReceptionistModel model;
	private JScrollPane scrollPane;
	
	AddReceptionistWindow addReceptionistWindow;
	ModifyReceptionistWindow modifyReceptionistWindow;

	public ReceptionistCRUDWindow() {
		setResizable(false);
		
		model = new ReceptionistModel();
		table = new JTable(model);
		
		table.setRowSelectionAllowed(true);
		table.setFocusable(true);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(27, 28, 959, 433);
		
		getContentPane().setLayout(null);
		getContentPane().add(scrollPane);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addReceptionistWindow = new AddReceptionistWindow(ReceptionistCRUDWindow.this);
				addReceptionistWindow.setVisible(true);
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
	               JOptionPane.showMessageDialog(null, "Selected row deleted successfully!");
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
					modifyReceptionistWindow = new ModifyReceptionistWindow(ReceptionistCRUDWindow.this, table.getSelectedRow());
					modifyReceptionistWindow.setVisible(true);
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
		goBackButton.setBounds(830, 501, 156, 51);
		getContentPane().add(goBackButton);
		
		setSize(1026, 600);
		setTitle("Receptionist CRUD");
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	ManagerMainWindow managerMainWindow = new ManagerMainWindow();
				managerMainWindow.setVisible(true);
				dispose();
            }
        });
	}
	
	public void refreshTable()
	{	
		model.fireTableDataChanged();
	}
}
