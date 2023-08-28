package gui.beauticianView.skillsView;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import gui.beauticianView.BeauticianMainWindow;

public class ViewSkillsWindow extends JFrame {

	private static final long serialVersionUID = -2847606224605766548L;
	
	private JTable table;
	private ServiceModel model;
	private JScrollPane scrollPane;
	
	public ViewSkillsWindow() {
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
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeauticianMainWindow beauticianMainWindow = new BeauticianMainWindow();
				beauticianMainWindow.setVisible(true);
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

}
