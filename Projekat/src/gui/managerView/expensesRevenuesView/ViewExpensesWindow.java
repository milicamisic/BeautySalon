package gui.managerView.expensesRevenuesView;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewExpensesWindow extends JDialog {

	private static final long serialVersionUID = 4911616821569305906L;
	
	private JTable table;
	private ExpenseModel model;
	private JScrollPane scrollPane;

	public ViewExpensesWindow() {
		setModal(true);
		setMinimumSize(new Dimension(940, 470));
		setResizable(false);
		
		model = new ExpenseModel();
		table = new JTable(model);
		
		table.setRowSelectionAllowed(true);
		table.setFocusable(true);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(27, 28, 667, 370);
		
		getContentPane().setLayout(null);
		getContentPane().add(scrollPane);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		goBackButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		goBackButton.setBounds(731, 347, 156, 51);
		getContentPane().add(goBackButton);
		
		setSize(933, 465);
		setTitle("Expenses view");
		setLocationRelativeTo(null);
		
	}

}
