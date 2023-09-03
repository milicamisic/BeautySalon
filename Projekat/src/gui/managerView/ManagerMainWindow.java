package gui.managerView;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import charts.ViewChartsWindow;
import gui.general.LoginWindow;
import gui.managerView.appointmentCRUD.AppointmentCRUDWindow;
import gui.managerView.beauticianCRUD.BeauticianCRUDWindow;
import gui.managerView.clientCRUD.ClientCRUDWindow;
import gui.managerView.expensesRevenuesView.ViewExpensesWindow;
import gui.managerView.expensesRevenuesView.ViewRevenuesWindow;
import gui.managerView.managerCRUD.ManagerCRUDWindow;
import gui.managerView.receptionistCRUD.ReceptionistCRUDWindow;
import gui.managerView.reports.AppointmentReportWindow;
import gui.managerView.reports.BeauticianReportWindow;
import gui.managerView.reports.ClientReportWindow;
import gui.managerView.reports.ServiceReportWindow;
import gui.managerView.serviceCRUD.ServiceCRUDWindow;
import gui.managerView.serviceTypeCRUD.ServiceTypeCRUDWindow;

public class ManagerMainWindow extends JFrame{
	
	private static final long serialVersionUID = 8942721022293712656L;
	
	private final JButton viewManagersButton = new JButton("Managers");
	private final JButton viewBeauticiansButton = new JButton("Beauticians");
	private final JButton viewReceptionistsButton = new JButton("Receptionists");
	private final JButton viewClientsButton = new JButton("Clients");
	private final JButton viewAppointmentsButton = new JButton("Appointments");
	private final JButton rulesWindow = new JButton("Rules");
	
	public ManagerMainWindow() {
		setMinimumSize(new Dimension(890, 645));
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		viewManagersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerCRUDWindow managerCRUDWindow = new ManagerCRUDWindow();
				managerCRUDWindow.setVisible(true);
				dispose();
			}
		});
		viewManagersButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewManagersButton.setBounds(31, 73, 195, 58);
		getContentPane().add(viewManagersButton);
		
		viewBeauticiansButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeauticianCRUDWindow beauticianCRUDWindow = new BeauticianCRUDWindow();
				beauticianCRUDWindow.setVisible(true);
				dispose();
			}
		});
		viewBeauticiansButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewBeauticiansButton.setBounds(31, 169, 195, 58);
		getContentPane().add(viewBeauticiansButton);
		
		viewReceptionistsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReceptionistCRUDWindow receptionistCRUDWindow = new ReceptionistCRUDWindow();
				receptionistCRUDWindow.setVisible(true);
				dispose();
			}
		});
		
		viewReceptionistsButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewReceptionistsButton.setBounds(31, 264, 195, 58);
		getContentPane().add(viewReceptionistsButton);
		
		viewClientsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientCRUDWindow clientCRUDWindow = new ClientCRUDWindow();
				clientCRUDWindow.setVisible(true);
				dispose();
			}
		});
		viewClientsButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewClientsButton.setBounds(31, 358, 195, 58);
		getContentPane().add(viewClientsButton);
		viewAppointmentsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppointmentCRUDWindow appointmentCRUDWindow = new AppointmentCRUDWindow();
				appointmentCRUDWindow.setVisible(true);
				dispose();
			}
		});
		
		viewAppointmentsButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewAppointmentsButton.setBounds(31, 451, 195, 58);
		
		getContentPane().add(viewAppointmentsButton);
		
		JButton viewServiceTypesButton = new JButton("Service Types");
		viewServiceTypesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServiceTypeCRUDWindow serviceTypeCRUDWindow = new ServiceTypeCRUDWindow();
				serviceTypeCRUDWindow.setVisible(true);
				dispose();
			}
		});
		viewServiceTypesButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewServiceTypesButton.setBounds(286, 73, 195, 58);
		getContentPane().add(viewServiceTypesButton);
		
		JButton viewServicesButton = new JButton("Services");
		viewServicesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServiceCRUDWindow serviceCRUDWindow = new ServiceCRUDWindow();
				serviceCRUDWindow.setVisible(true);
				dispose();
			}
		});
		viewServicesButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewServicesButton.setBounds(286, 169, 195, 58);
		getContentPane().add(viewServicesButton);
		
		JButton logoutButton = new JButton("Log Out");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindow loginWindow = new LoginWindow();
				loginWindow.setVisible(true);
				dispose();
			}
		});
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		logoutButton.setBounds(671, 542, 195, 58);
		getContentPane().add(logoutButton);
		
		JButton viewRevenuesButton = new JButton("Revenues");
		viewRevenuesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewRevenuesWindow viewRevenuesWindow = new ViewRevenuesWindow();
				viewRevenuesWindow.setVisible(true);
			}
		});
		viewRevenuesButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewRevenuesButton.setBounds(286, 264, 195, 58);
		getContentPane().add(viewRevenuesButton);
		
		JButton viewExpensesButton = new JButton("Expenses");
		viewExpensesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewExpensesWindow viewExpensesWindow = new ViewExpensesWindow();
				viewExpensesWindow.setVisible(true);
			}
		});
		viewExpensesButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewExpensesButton.setBounds(286, 358, 195, 58);
		getContentPane().add(viewExpensesButton);
		rulesWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RulesWindow rulesWindow = new RulesWindow();
				rulesWindow.setVisible(true);
			}
		});
		rulesWindow.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rulesWindow.setBounds(286, 451, 195, 58);
		
		getContentPane().add(rulesWindow);
		
		JButton reportBeauticianButton = new JButton("Beautician Report");
		reportBeauticianButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeauticianReportWindow beauticianReportWindow = new BeauticianReportWindow();
				beauticianReportWindow.setVisible(true);
			}
		});
		reportBeauticianButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		reportBeauticianButton.setBounds(546, 73, 274, 58);
		getContentPane().add(reportBeauticianButton);
		
		JButton reportAppointmentButton = new JButton("Appointment Report");
		reportAppointmentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppointmentReportWindow appointmentReportWindow = new AppointmentReportWindow();
				appointmentReportWindow.setVisible(true);
			}
		});
		reportAppointmentButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		reportAppointmentButton.setBounds(546, 169, 274, 58);
		getContentPane().add(reportAppointmentButton);
		
		JButton reportServiceButton = new JButton("Service Report");
		reportServiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServiceReportWindow serviceReportWindow = new ServiceReportWindow();
				serviceReportWindow.setVisible(true);
			}
		});
		reportServiceButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		reportServiceButton.setBounds(546, 264, 274, 58);
		getContentPane().add(reportServiceButton);
		
		JButton reportCLientButton = new JButton("Client Report");
		reportCLientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientReportWindow clientReportWindow = new ClientReportWindow();
				clientReportWindow.setVisible(true);
			}
		});
		reportCLientButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		reportCLientButton.setBounds(546, 358, 274, 58);
		getContentPane().add(reportCLientButton);
		
		JButton viewChartsButton = new JButton("Charts");
		viewChartsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewChartsWindow viewChartsWindow = new ViewChartsWindow();
				viewChartsWindow.setVisible(true);
			}
		});
		viewChartsButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewChartsButton.setBounds(546, 451, 274, 58);
		getContentPane().add(viewChartsButton);
		
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	LoginWindow loginWindow = new LoginWindow();
				loginWindow.setVisible(true);
				dispose();
            }
        });
	}
}
