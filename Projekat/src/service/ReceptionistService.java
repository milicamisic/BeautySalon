package service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import humanEntities.Beautician;
import humanEntities.Client;
import otherEntities.Appointment;
import otherEntities.AppointmentStatus;
import otherEntities.Expense;
import otherEntities.Service;
import otherEntities.Timeslot;
import paket1.BeautySalon;

public class ReceptionistService {
	private BeautySalon beautySalon;
	
	public ReceptionistService() {
		beautySalon = BeautySalon.getBeautySalon();
	}
	
	public void updateMoneySpentScheduled(Client client, Service service) {
		if(client.hasLoyaltyCard())
			client.setMoneySpent(client.getMoneySpent() + service.getPrice() * 0.9);
		else
			client.setMoneySpent(client.getMoneySpent() + service.getPrice());
		beautySalon.modifyClient(client);
	}
	
	public void updateMoneySpentCancelled(Client client, Service service) {
		if(client.hasLoyaltyCard())
			client.setMoneySpent(client.getMoneySpent() - service.getPrice() * 0.9);
		else
			client.setMoneySpent(client.getMoneySpent() - service.getPrice());
		beautySalon.modifyClient(client);
	}
	
	public boolean makeAppointment(Client client, Beautician beautician, Service service, LocalDateTime dateTime) {
		Timeslot timeslot = new Timeslot(dateTime, dateTime.plusMinutes(service.getDurationInMinutes()));
		for(Appointment a : beautySalon.getAppointments()) {
			if(TimeslotService.areOverlaping(a.getTimeslot(), timeslot)) {
				if(beautician.equals(a.getBeautician()) || client.equals(a.getClient()))
					return false;
			}
		}
		Appointment appointment = new Appointment(0, beautician, client, timeslot, service, AppointmentStatus.SCHEDULED, service.getPrice());
		beautySalon.addAppointment(appointment);
		updateMoneySpentScheduled(client, service);
		return true;
		
	}

	public boolean cancelAppointment(Appointment appointment) {
		if(appointment.getStatus() != AppointmentStatus.SCHEDULED)
			return false;
		appointment.setPrice(0);
		appointment.setStatus(AppointmentStatus.SALON_CANCELED);
		beautySalon.modifyAppointment(appointment);
		updateMoneySpentCancelled(appointment.getClient(), appointment.getService());
		Expense e = new Expense("SalonCancelled: Appointment " + appointment.getId(), appointment.getService().getPrice(), LocalDate.now());
		beautySalon.addExpense(e);
		return true;
	}

	public void expireAppointment(Appointment appointment) {
		appointment.setStatus(AppointmentStatus.DIDNT_SHOW_UP);
		beautySalon.modifyAppointment(appointment);
	}

}
