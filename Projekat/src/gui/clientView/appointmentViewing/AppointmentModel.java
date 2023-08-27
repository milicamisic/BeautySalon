package gui.clientView.appointmentViewing;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import humanEntities.Client;
import otherEntities.Appointment;
import service.AppointmentService;
import service.ClientService;

public class AppointmentModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 7480224552092106397L;
	
	private String[] columns = {"Id", "Beautician", "Client", "Start", "Duration", "Service", "Service Type", "Cost", "Status"};
	
	private AppointmentService appointmentService;
	private ClientService clientService;
	private ArrayList<Appointment> appointments;

	public AppointmentModel(Client client)
	{
		clientService = new ClientService();
		this.appointmentService = new AppointmentService();
		this.appointments = appointmentService.getClientAppointments(client);
	}

	@Override
	/*
	 * Ako se ova metoda ne redefinise, koristi se default renderer/editor za 
	 * celiju. To znaci da, ako je kolona tipa boolean, onda ce se u tabeli 
	 * prikazati true/false, a ovako ce se za takav tip kolone pojaviti
	 * checkbox.
	 */
	public Class<?> getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}

	@Override
	public int getRowCount()
	{
		return this.appointments.size();
	}

	@Override
	public int getColumnCount()
	{
		return this.columns.length;
	}
	
	@Override
	public String getColumnName(int columnIndex)
	{
		return this.columns[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Appointment a = appointments.get(rowIndex);
		return a.toCell(columnIndex);
	}

	public boolean cancelAppointment(int rowIndex) {
		Appointment a = appointments.get(rowIndex);
		
		boolean isCanceled = clientService.cancelAppointment(a);
		
		fireTableDataChanged();
		return isCanceled;
	}
}
