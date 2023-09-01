package service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
	
	public Client getClientByUsername(String username)
	{
		for(Client c : beautySalon.getClients())
		{
			if(c.getUsername().equals(username))
			{
				return c;
			}
		}
		return null;
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
	
	public int makeAppointment(Appointment appointment) {
		int result = beautySalon.addAppointment(appointment);
		if(result == 0)
			updateMoneySpentScheduled(appointment.getClient(), appointment.getService());
		return result;
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

	public boolean cancelAppointment(Appointment appointment) {
		if(appointment.getStatus() == AppointmentStatus.SCHEDULED) {
			appointment.setStatus(AppointmentStatus.CLIENT_CANCELED);
			appointment.setPrice(0.1*appointment.getPrice());
			beautySalon.modifyAppointment(appointment);
			updateMoneySpentCancelled(appointment.getClient(), appointment.getService());
			Expense e = new Expense("ClientCancelled: Appointment " + appointment.getId(), 0.9*appointment.getPrice(), LocalDate.now());
			beautySalon.addExpense(e);	
			return true;
		}
		return false;
	}
	
	public void viewPastAppointments(Client client) {
		for(Appointment a : beautySalon.getAppointments()) {
			if(a.getClient().equals(client) && a.getTimeslot().getEndTime().isBefore(LocalDateTime.now()))
				System.out.println(a);
		}
	}
	
	public ArrayList<Appointment> getScheduledAppointments(Client client)
	{
		ArrayList<Appointment> scheduledAppointments = new ArrayList<Appointment>();
		for(Appointment a : beautySalon.getAppointments())
		{
			if(a.getClient().equals(client) && a.getStatus().equals(AppointmentStatus.SCHEDULED))
			{
				scheduledAppointments.add(a);
			}
		}
		return scheduledAppointments;
	}

	public boolean isAvailable(Client client, Timeslot timeslot) 
	{
		ArrayList<Appointment> scheduledAppointments = getScheduledAppointments(client);
		
		if(scheduledAppointments == null)
			return true;
		for(Appointment a : scheduledAppointments)
		{
			if(TimeslotService.areOverlaping(a.getTimeslot(), timeslot))
			{
				return false;
			}
		}
		return true;
	}
}
