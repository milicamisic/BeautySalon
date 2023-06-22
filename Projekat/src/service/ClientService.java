package service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import humanEntities.Beautician;
import humanEntities.Client;
import otherEntities.Appointment;
import otherEntities.AppointmentStatus;
import otherEntities.Expense;
import otherEntities.Service;
import otherEntities.Timeslot;
import paket1.BeautySalon;

public class ClientService {
	private BeautySalon beautySalon;
	
	public ClientService() {
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
			client.setMoneySpent(client.getMoneySpent() - 0.9 * service.getPrice() * 0.9);
		else
			client.setMoneySpent(client.getMoneySpent() - 0.9 * service.getPrice());
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
		Appointment appointment = new Appointment(0, beautician, client, timeslot, service, AppointmentStatus.SCHEDULED);
		beautySalon.addAppointment(appointment);
		updateMoneySpentScheduled(client, service);
		return true;
	}
	
	public boolean makeAppointment(Client client, Service service, LocalDateTime dateTime) {
		Timeslot timeslot = new Timeslot(dateTime, dateTime.plusMinutes(service.getDurationInMinutes()));
		for(Appointment a : beautySalon.getAppointments()) {
			if(TimeslotService.areOverlaping(a.getTimeslot(), timeslot)) {
				if(client.equals(a.getClient()))
					return false;
			}
		}
		Beautician beautician = beautySalon.getAvailableBeautician(timeslot);
		if(beautician == null)
			return false;
		Appointment appointment = new Appointment(0, beautician, client, timeslot, service, AppointmentStatus.SCHEDULED);
		beautySalon.addAppointment(appointment);
		updateMoneySpentScheduled(client, service);
		return true;
	}

	public ArrayList<Appointment> getAppointments(Client client) {
		ArrayList<Appointment> clientAppointments = new ArrayList<Appointment>();
		for(Appointment a : beautySalon.getAppointments()) {
			if(a.getClient().equals(client)) {
				clientAppointments.add(a);
			}
		}
		return clientAppointments;
	}

	public void cancelAppointment(Appointment appointment) {
		appointment.setStatus(AppointmentStatus.CLIENT_CANCELED);
		beautySalon.modifyAppointment(appointment);
		updateMoneySpentCancelled(appointment.getClient(), appointment.getService());
		Expense e = new Expense("ClientCancelled: Appointment " + appointment.getId(), 0.9*appointment.getService().getPrice(), LocalDate.now());
		beautySalon.addExpense(e);
	}
	
	public void viewPastAppointments(Client client) {
		for(Appointment a : beautySalon.getAppointments()) {
			if(a.getClient().equals(client) && a.getTimeslot().getEndTime().isBefore(LocalDateTime.now()))
				System.out.println(a);
		}
	}
}
