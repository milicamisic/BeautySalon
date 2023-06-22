package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import humanEntities.Manager;
import paket1.BeautySalon;

public class ManagerCRUDWindow extends JDialog {

	private static final long serialVersionUID = -4851142263147950673L;
	
	private JTable table;
	private ManagerModel model;
	private JScrollPane scrollPane;
	
	AddManagerWindow addManagerWindow;

	public ManagerCRUDWindow(InitialWindow parent) {
		super(parent, true);
		setResizable(false);
		
		generateTable();
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addManagerWindow = new AddManagerWindow(ManagerCRUDWindow.this);
				addManagerWindow.setVisible(true);
			}
		});
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addButton.setBounds(27, 501, 156, 51);
		getContentPane().add(addButton);
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		removeButton.setBounds(210, 501, 156, 51);
		getContentPane().add(removeButton);
		
		JButton modifyButton = new JButton("Modify");
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		modifyButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		modifyButton.setBounds(399, 501, 156, 51);
		getContentPane().add(modifyButton);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		goBackButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		goBackButton.setBounds(830, 501, 156, 51);
		getContentPane().add(goBackButton);
		
		setSize(1026, 600);
		setTitle("Manager CRUD");
	}
	
	public void generateTable()
	{
		BeautySalon beautySalon = BeautySalon.getBeautySalon();
		ArrayList<Manager> managers = beautySalon.getManagers();
		
		model = new ManagerModel(managers);
		table = new JTable(model);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(27, 28, 959, 433);
		
		getContentPane().setLayout(null);
		getContentPane().add(scrollPane);
	}
}
