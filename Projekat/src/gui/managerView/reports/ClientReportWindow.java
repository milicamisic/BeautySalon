package gui.managerView.reports;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ClientReportWindow extends JDialog {

	private static final long serialVersionUID = -1885211125890545058L;
	
	private JTable table;
	private ClientModel model;
	private JScrollPane scrollPane;
	
	public ClientReportWindow() {
		setModal(true);
		setMinimumSize(new Dimension(1375, 510));
		model = new ClientModel();
		table = new JTable(model);
		
		table.setRowSelectionAllowed(true);
		table.setFocusable(true);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(27, 28, 975, 433);
		
		getContentPane().setLayout(null);
		getContentPane().add(scrollPane);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		closeButton.setBounds(1191, 411, 156, 51);
		getContentPane().add(closeButton);
		
		setLocationRelativeTo(null);
	}
}
