package service;

import java.time.LocalDate;
import java.util.ArrayList;

import humanEntities.Client;
import otherEntities.Appointment;
import otherEntities.AppointmentStatus;
import otherEntities.Expense;
import paket1.BeautySalon;

public class ReceptionistService {
	private BeautySalon beautySalon;
	
	public ReceptionistService() {
		beautySalon = BeautySalon.getBeautySalon();
	}
	
	public boolean makeAppointment(Appointment appointment) {
		beautySalon.addAppointment(appointment);
		return true;
	}

	public boolean cancelAppointment(Appointment appointment) 
	{	
		if(appointment.getStatus() != AppointmentStatus.SCHEDULED) return false;
		
		Expense expense = new Expense("Appointment " + appointment.getId() + " SALON_CANCELED", appointment.getPrice(), LocalDate.now());
		beautySalon.addExpense(expense);
		
		Client client = appointment.getClient();
		client.setMoneySpent(client.getMoneySpent() - appointment.getPrice());
		
		ArrayList<Appointment> appointments = beautySalon.getAppointments();
		for(Appointment a :  appointments) {
			if(a.getId() == appointment.getId()) {
				a.setStatus(AppointmentStatus.SALON_CANCELED);
				a.setPrice(0);
			}
		}
		
		return true;
	}
	
	public int modifyAppointment(Appointment appointment) {
		return beautySalon.modifyAppointment(appointment);
	}

	public void expireAppointment(Appointment appointment) {
		appointment.setStatus(AppointmentStatus.DIDNT_SHOW_UP);
		beautySalon.modifyAppointment(appointment);
	}

}
