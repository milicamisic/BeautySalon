package gui.beauticianView.appointmentViewing;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import humanEntities.Beautician;
import otherEntities.Appointment;
import service.AppointmentService;
import service.BeauticianService;

public class AppointmentModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 2691262425559951118L;
	
	private String[] columns = {"Id", "Beautician", "Client", "Start", "Duration", "Service", "Service Type", "Price", "Status"};
	private BeauticianService beauticianService;
	private AppointmentService appointmentService;
	
	private ArrayList<Appointment> appointments;

	public AppointmentModel(Beautician beautician)
	{
		this.beauticianService = new BeauticianService();
		this.appointmentService = new AppointmentService();
		this.appointments = beauticianService.getScheduledAppointments(beautician);
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
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Appointment a = appointments.get(rowIndex);
		return a.toCell(columnIndex);
	}

	public boolean completeAppointment(int selectedRow) {
		Appointment appointment = appointments.get(selectedRow);
		boolean done = appointmentService.completeAppointment(appointment.getId());
		if(done) {
			fireTableDataChanged();
			return true;
		}
		return false;
	}
	
	public boolean clientDidntShowUp(int selectedRow) {
		Appointment appointment = appointments.get(selectedRow);
		boolean done = appointmentService.clientDidntShowUpForAppointment(appointment.getId());
		if(done) {
			fireTableDataChanged();
			return true;
		}
		return false;
	}
}
