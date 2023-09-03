package gui.managerView.reports;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AppointmentReportWindow extends JDialog {

	private static final long serialVersionUID = -2279148909714975429L;

	private JTable table;
	private AppointmentStatusModel model;
	private JScrollPane scrollPane;
	private JTextField startDateField;
	private JTextField endDateField;
	
	public AppointmentReportWindow() {
		setMinimumSize(new Dimension(700, 510));
		setModal(true);
		model = new AppointmentStatusModel(LocalDate.now(), LocalDate.now());
		table = new JTable(model);
		
		table.setRowSelectionAllowed(true);
		table.setFocusable(true);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(27, 28, 275, 433);
		
		getContentPane().setLayout(null);
		getContentPane().add(scrollPane);
		
		JLabel lblBeginDate = new JLabel("Begin Date:");
		lblBeginDate.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblBeginDate.setBounds(329, 28, 148, 48);
		getContentPane().add(lblBeginDate);
		
		startDateField = new JTextField(LocalDate.now().format(DateTimeFormatter.ofPattern("d.M.y")));
		startDateField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		startDateField.setColumns(10);
		startDateField.setBounds(487, 28, 161, 48);
		getContentPane().add(startDateField);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEndDate.setBounds(329, 100, 148, 48);
		getContentPane().add(lblEndDate);
		
		endDateField = new JTextField(LocalDate.now().format(DateTimeFormatter.ofPattern("d.M.y")));
		endDateField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		endDateField.setColumns(10);
		endDateField.setBounds(487, 101, 161, 48);
		getContentPane().add(endDateField);
		
		JButton generateButton = new JButton("Generate");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean fieldsValid = validateFields(startDateField.getText(), endDateField.getText());
				if(fieldsValid) {
					LocalDate startDate = LocalDate.parse(startDateField.getText(), DateTimeFormatter.ofPattern("d.M.y"));
					LocalDate endDate = LocalDate.parse(endDateField.getText(), DateTimeFormatter.ofPattern("d.M.y"));
					
					model = new AppointmentStatusModel(startDate, endDate);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}

		});
		generateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		generateButton.setBounds(412, 188, 156, 51);
		getContentPane().add(generateButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		closeButton.setBounds(524, 410, 156, 51);
		getContentPane().add(closeButton);
		
		setLocationRelativeTo(null);
	}
	
	private boolean validateFields(String startDate, String endDate) {
		if(startDate.trim().isEmpty() || endDate.trim().isEmpty()) 
		{
			JOptionPane.showMessageDialog(null, "Please set the date range!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.y");
		
		try
		{
			LocalDate.parse(startDate, dateFormatter);
			LocalDate.parse(endDate, dateFormatter);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Date must be in format \"day.month.year\"!", "Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
}
