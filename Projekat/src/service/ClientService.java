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
	
	public int makeAppointment(Appointment appointment) {
		return beautySalon.addAppointment(appointment);
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

	public boolean cancelAppointment(Appointment appointment) 
	{	
		if(appointment.getStatus() != AppointmentStatus.SCHEDULED) return false;
		
		Expense expense = new Expense("Appointment " + appointment.getId() + " CLIENT_CANCELED", appointment.getPrice() * 0.9, LocalDate.now());
		beautySalon.addExpense(expense);
		
		Client client = appointment.getClient();
		client.setMoneySpent(client.getMoneySpent() - appointment.getPrice() * 0.9);
		
		ArrayList<Appointment> appointments = beautySalon.getAppointments();
		for(Appointment a :  appointments) {
			if(a.getId() == appointment.getId()) {
				a.setStatus(AppointmentStatus.CLIENT_CANCELED);
				a.setPrice(a.getPrice()*0.1);
			}
		}
		
		return true;
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
