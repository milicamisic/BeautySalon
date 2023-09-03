package charts;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import java.awt.Dimension;

public class ViewChartsWindow extends JDialog {
	
	private static final long serialVersionUID = 469097576902481803L;

	public ViewChartsWindow() {
		setMinimumSize(new Dimension(1220, 1000));
		setModal(true);
		
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel serviceTypeChartPanel = new JPanel();
		serviceTypeChartPanel.setBounds(10, 507, 895, 427);
		JPanel serviceTypeChart = new XChartPanel<XYChart>(Chart.getServiceTypeChart());
		serviceTypeChartPanel.add(serviceTypeChart);
		getContentPane().add(serviceTypeChartPanel);
		
		JPanel beauticianChartPanel = new JPanel();
		beauticianChartPanel.setBounds(10, 11, 550, 462);
		JPanel beauticianChart = new XChartPanel<PieChart>(Chart.getBeauticianChart());
		beauticianChartPanel.add(beauticianChart);
		getContentPane().add(beauticianChartPanel);
		
		JPanel appointmentStatusChartPanel = new JPanel();
		appointmentStatusChartPanel.setBounds(570, 11, 591, 462);
		JPanel appointmentStatusChart = new XChartPanel<PieChart>(Chart.getAppointmentStatusChart());
    	appointmentStatusChartPanel.add(appointmentStatusChart);
		getContentPane().add(appointmentStatusChartPanel);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		closeButton.setBounds(1032, 899, 156, 51);
		getContentPane().add(closeButton);
		setLocationRelativeTo(null);
	}
}
