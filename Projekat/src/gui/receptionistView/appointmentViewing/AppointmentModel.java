package gui.receptionistView.appointmentViewing;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import otherEntities.Appointment;
import paket1.BeautySalon;
import service.AppointmentService;
import service.ReceptionistService;

public class AppointmentModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 2691262425559951118L;
	
	private String[] columns = {"Id", "Beautician", "Client", "Start", "Duration", "Service", "Service Type", "Price", "Status"};
	private BeautySalon beautySalon;
	private ReceptionistService receptionistService;
	private AppointmentService appointmentService;
	
	private ArrayList<Appointment> appointments;

	public AppointmentModel()
	{
		this.beautySalon = BeautySalon.getBeautySalon();
		this.appointments = beautySalon.getAppointments();
		this.receptionistService = new ReceptionistService();
		this.appointmentService = new AppointmentService();
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

	public boolean removeRow(int rowIndex) {
		Appointment a = appointments.get(rowIndex);
		
		boolean isCanceled = receptionistService.cancelAppointment(a);
		
		fireTableDataChanged();
		return isCanceled;
	}

	public void applyFilters(String service, String serviceType, double fromPrice, double toPrice) {
		if(!service.equals("<no filter>")) {
			appointments = appointmentService.getAppointmentsByService(service);
		}
		if(!serviceType.equals("<no filter>")) {
			appointments = appointmentService.getAppointmentsByServiceType(serviceType);
		}
		ArrayList<Appointment> filteredAppointments = new ArrayList<Appointment>();
		
		for(Appointment a : appointments) {
			if(fromPrice <= a.getPrice() && a.getPrice() <= toPrice) {
				filteredAppointments.add(a);
			}
		}
		appointments = filteredAppointments;
		fireTableDataChanged();
	}
}
