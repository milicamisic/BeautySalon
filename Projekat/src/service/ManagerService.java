package service;

import java.time.LocalDate;
import java.util.ArrayList;

import citanje.AppointmentReader;
import citanje.ServiceReader;
import citanje.ServiceTypeReader;
import humanEntities.Beautician;
import humanEntities.Client;
import humanEntities.Manager;
import humanEntities.Receptionist;
import otherEntities.Appointment;
import otherEntities.Expense;
import otherEntities.Revenue;
import otherEntities.Service;
import otherEntities.ServiceType;
import paket1.BeautySalon;

public class ManagerService {
	
	private BeautySalon beautySalon;
	
	public ManagerService() {
		this.beautySalon = BeautySalon.getBeautySalon();
	}
	// CRUD for managers
	public boolean addManager(Manager manager) {
		return beautySalon.addManager(manager);
	}
	
	public boolean removeManager(String username) {
		return beautySalon.removeManager(username);
	}
	
	public boolean modifyManager(Manager manager) {
		return beautySalon.modifyManager(manager);
	}
	
	public void viewManagers() {}
	
	// CRUD for beauticians
	public boolean addBeautician(Beautician beautician) {
		return beautySalon.addBeautician(beautician);
	}
	
	public boolean removeBeautician(String username) {
		return beautySalon.removeBeautician(username);
	}
	
	public boolean modifyBeautician(Beautician b) {
		return beautySalon.modifyBeautician(b);
	}

	public void viewBeauticians() {}
	
	// CRUD for receptionists
	public boolean addReceptionist(Receptionist r) {
		return beautySalon.addReceptionist(r);
	}
	
	public boolean removeReceptionist(String username) {
		return beautySalon.removeReceptionist(username);
	}
	
	public boolean modifyReceptionist(Receptionist r) {
		return beautySalon.modifyReceptionist(r);
	}

	public void viewReceptionists() {}
	
	// CRUD for clients
	public boolean addClient(Client c) {
		return beautySalon.addClient(c);
	}
	
	public boolean removeClient(String username) {
		return beautySalon.removeClient(username);
	}
	
	public boolean modifyClient(Client c) {
		return beautySalon.modifyClient(c);
	}

	public void viewClients() {}
	
	// CRUD for appointments
	public int addAppointment(Appointment appointment) 
	{
		ClientService clientService = new ClientService();
		
		int result = beautySalon.addAppointment(appointment);
		if(result == 0)
			clientService.updateMoneySpentScheduled(appointment.getClient(), appointment.getService());
		
		return result;
	}
	
	public boolean removeAppointment(Appointment appointment) 
	{	
		ReceptionistService receptionistService = new ReceptionistService();
		boolean removed = beautySalon.removeAppointment(appointment);
		if(removed == true)
			receptionistService.updateMoneySpentCancelled(appointment.getClient(), appointment.getService());
		return removed;
	}
	
	public int modifyAppointment(Appointment a) {
		int result = addAppointment(a);
		if(result == 0) {
			removeAppointment(a); // we know that this appointment exists because it is being modified
		}
		return result;
	}

	public void viewAppointments() {
		AppointmentReader ar = new AppointmentReader("src/data/appointments3");
		ArrayList<Appointment> appointments = ar.loadAppointments();
		
		for(Appointment a : appointments)
			System.out.println(a);
	}
	
	// CRUD for service type
	public boolean addServiceType(ServiceType st) {
		return beautySalon.addServiceType(st);
	}
	
	public boolean removeServiceType(String type) {
		return beautySalon.removeServiceType(type);
	}
	
	public void viewServiceTypes() {
		ServiceTypeReader str = new ServiceTypeReader("src/data/service_types3");
		ArrayList<ServiceType> types = str.loadServiceTypes();
		
		for(ServiceType at : types)
			System.out.println(at);
	}
	
	// CRUD for services
	public boolean addService(Service s) {
		return beautySalon.addService(s);
	}

	public boolean removeService(String name) {
		return beautySalon.removeService(name);
	}
	
	public boolean modifyService(Service s) {
		return beautySalon.modifyService(s);
	}
	
	public void viewServices() {
		ServiceReader sr = new ServiceReader("src/data/services3");
		ArrayList<Service> services = sr.loadServices();
		
		for(Service s : services)
			System.out.println(s);
	}
	
	
	
	// view revenues and expenses
	public void viewRevenues(LocalDate month) {
		for(Revenue r : beautySalon.getRevenues()) {
			if(r.getDate().getMonth().equals(month.getMonth()))
				System.out.println(r);
		}
	}
	
	public void viewExpenses(LocalDate month) {
		for(Expense e : beautySalon.getExpenses()) {
			if(e.getDate().getMonth().equals(month.getMonth()))
				System.out.println(e);
		}
	}
	
	// bonuses
	public void setBonusRules(int completedAppointments) {
		beautySalon.setCompletedAppointmentsForBonus(completedAppointments);
	}
	
	// loyalty card clients
	public void setLoyaltyCardPrecondition(int amount) {
		beautySalon.setLoyaltyCardPrecondition(amount);
	}

}
