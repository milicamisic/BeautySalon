package gui.managerView.beauticianCRUD;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import gui.InitialWindow;
import java.awt.Dimension;

public class BeauticianCRUDWindow extends JDialog {

	private static final long serialVersionUID = -4851142263147950673L;
	
	private JTable table;
	private BeauticianModel model;
	private JScrollPane scrollPane;
	
	AddBeauticianWindow addBeauticianWindow;
	ModifyBeauticianWindow modifyBeauticianWindow;

	public BeauticianCRUDWindow(InitialWindow parent) {
		super(parent, true);
		setMinimumSize(new Dimension(1040, 610));
		setResizable(false);
		
		model = new BeauticianModel();
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
				addBeauticianWindow = new AddBeauticianWindow(BeauticianCRUDWindow.this);
				addBeauticianWindow.setVisible(true);
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
	               JOptionPane.showMessageDialog(null, "Beautician removed!", "Information message", JOptionPane.INFORMATION_MESSAGE);
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
					modifyBeauticianWindow = new ModifyBeauticianWindow(BeauticianCRUDWindow.this, table.getSelectedRow());
					modifyBeauticianWindow.setVisible(true);
				}
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
		
		setSize(1031, 607);
		setTitle("Beautician CRUD");
	}
	
	public void refreshTable()
	{	
		model.fireTableDataChanged();
	}
}
