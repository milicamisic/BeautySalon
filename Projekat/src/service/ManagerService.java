package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import citanje.AppointmentReader;
import citanje.ServiceTypeReader;
import humanEntities.Beautician;
import humanEntities.Client;
import humanEntities.Manager;
import humanEntities.Receptionist;
import otherEntities.Appointment;
import otherEntities.AppointmentStatus;
import otherEntities.Expense;
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
	
	public void modifyManager(Manager manager) {
		beautySalon.modifyManager(manager);
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
	
	public void modifyClient(Client c) {
		beautySalon.modifyClient(c);
	}

	public void viewClients() {}
	
	// CRUD for appointments
	public int addAppointment(Appointment appointment) 
	{
		return beautySalon.addAppointment(appointment);
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
	
	public int modifyAppointment(Appointment a) {
		return beautySalon.modifyAppointment(a);
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
	
	// bonuses
	public void setBonusRules(int completedAppointments) {
		beautySalon.setCompletedAppointmentsForBonus(completedAppointments);
	}
	
	// loyalty card clients
	public void setLoyaltyCardPrecondition(int amount) {
		beautySalon.setLoyaltyCardPrecondition(amount);
	}
	
	public ArrayList<Object[]> getBeauticianPerformanceReport(LocalDate startDate, LocalDate endDate) {
		HashMap<String, double[]> report = new HashMap<String, double[]>();
		
		for(Beautician b : beautySalon.getBeauticians()) {
			double[] score = {0, 0};
			report.put(b.getUsername(), score);
		}
		
		for(Appointment a : beautySalon.getAppointments()) {
			LocalDate appointmentDate = a.getTimeslot().getStartTime().toLocalDate();
			if(a.getStatus() == AppointmentStatus.COMPLETED && appointmentDate.isAfter(startDate.minusDays(1)) && appointmentDate.isBefore(endDate.plusDays(1))) {
				double[] score = report.get(a.getBeautician().getUsername());
				score[0] += 1;
				score[1] += a.getPrice();
				report.put(a.getBeautician().getUsername(), score);
			}
		}
		
		ArrayList<Object[]> report2 = new ArrayList<Object[]>();
		
		report.forEach((key, value) -> {
			Object[] pair = {key, value[0], value[1]};
			report2.add(pair);
        });
		
		return report2;
	}
	
	public ArrayList<Object[]> getAppointmentStatusReport(LocalDate startDate, LocalDate endDate) {
		HashMap<String, Integer> report = new HashMap<String, Integer>();
		report.put("COMPLETED", 0);
		report.put("CLIENT_CANCELED", 0);
		report.put("SALON_CANCELED", 0);
		
		for(Appointment a : beautySalon.getAppointments()) {
			int score;
			LocalDate appointmentDate = a.getTimeslot().getStartTime().toLocalDate();
			if(appointmentDate.isAfter(startDate.minusDays(1)) && appointmentDate.isBefore(endDate.plusDays(1))) {
				switch(a.getStatus().toString()) {
				case "COMPLETED":
					score = report.get("COMPLETED");
					score += 1;
					report.put("COMPLETED", score);
					break;
					
				case "CLIENT_CANCELED":
					score = report.get("CLIENT_CANCELED");
					score += 1;
					report.put("CLIENT_CANCELED", score);
					break;
					
				case "SALON_CANCELED":
					score = report.get("SALON_CANCELED");
					score += 1;
					report.put("SALON_CANCELED", score);
					break;
				}
			}
		}
		ArrayList<Object[]> report2 = new ArrayList<Object[]>();
		
		report.forEach((key, value) -> {
			Object[] pair = {key, value};
			report2.add(pair);
        });
		
		return report2;
	}
	
	public ArrayList<Object[]> getServiceReport(LocalDate startDate, LocalDate endDate) {
		HashMap<String, double[]> report = new HashMap<String, double[]>();
		
		for(Service s : beautySalon.getServices()) {
			double[] score = {0.0, 0.0};
			report.put(s.getName(), score);
		}
		
		for(Appointment a : beautySalon.getAppointments()) {
			LocalDate appointmentDate = a.getTimeslot().getStartTime().toLocalDate();
			if(appointmentDate.isAfter(startDate.minusDays(1)) && appointmentDate.isBefore(endDate.plusDays(1))) {
				double[] score = report.get(a.getService().getName());
				score[0] += 1;
				score[1] += a.getPrice();
				report.put(a.getService().getName(), score);
			}
		}
		
		ArrayList<Object[]> report2 = new ArrayList<Object[]>();
		ServiceService serviceService = new ServiceService();
		
		report.forEach((key, value) -> {
			Service s = serviceService.getServiceByName(key);
			int appNum = (int) value[0];
			double money = value[1];
			Object[] item = {s, appNum, money};
			report2.add(item);
		});
		
		return report2;
	}
	
	public ArrayList<Client> getClientsWithLoyaltyCard() {
		ArrayList<Client> appropriateClients = new ArrayList<Client>();
		for(Client c : beautySalon.getClients()) {
			if(c.hasLoyaltyCard()) {
				appropriateClients.add(c);
			}
		}
		return appropriateClients;
	}
	
	public double getServiceTypeChartReport(LocalDate startDate, LocalDate endDate, String serviceType) {
		double money = 0;
		for(Appointment a : beautySalon.getAppointments()) {
			LocalDate appointmentDate = a.getTimeslot().getStartTime().toLocalDate();
			if(a.getService().getType().getType().equals(serviceType) && appointmentDate.isAfter(startDate.minusDays(1)) && appointmentDate.isBefore(endDate.plusDays(1))) {
				money += a.getPrice();
			}
		}
		return money;
	}
	
	public double getAllServiceTypesChartReport(LocalDate startDate, LocalDate endDate) {
		double money = 0;
		for(Appointment a : beautySalon.getAppointments()) {
			LocalDate appointmentDate = a.getTimeslot().getStartTime().toLocalDate();
			if(appointmentDate.isAfter(startDate.minusDays(1)) && appointmentDate.isBefore(endDate.plusDays(1))) {
				money += a.getPrice();
			}
		}
		return money;
	}
	
	public HashMap<String, Integer> getBeauticianChart() {
		HashMap<String, Integer> scores = new HashMap<String, Integer>();
		LocalDate startDate = LocalDate.now().minusDays(30);
	    LocalDate endDate = LocalDate.now();
	    
	    for(Beautician b : beautySalon.getBeauticians()) {
	    	scores.put(b.getUsername(), 0);
	    }
	    
	    for(Appointment a : beautySalon.getAppointments()) {
	    	LocalDate appointmentDate = a.getTimeslot().getStartTime().toLocalDate();
	    	if(a.getStatus() == AppointmentStatus.COMPLETED && appointmentDate.isAfter(startDate.minusDays(1)) && appointmentDate.isBefore(endDate.plusDays(1))) {
	    		int score = scores.get(a.getBeautician().getUsername());
		    	score += 1;
		    	scores.put(a.getBeautician().getUsername(), score);
	    	}
	    }
	    
	    return scores;
	}
	
	public HashMap<String, Integer> getAppointmentStatusChart() {
		HashMap<String, Integer> scores = new HashMap<String, Integer>();
		LocalDate startDate = LocalDate.now().minusDays(30);
	    LocalDate endDate = LocalDate.now();
		
		scores.put("SCHEDULED", 0);
		scores.put("COMPLETED", 0);
		scores.put("CLIENT_CANCELED", 0);
		scores.put("SALON_CANCELED", 0);
		scores.put("DIDNT_SHOW_UP", 0);
		
		for(Appointment a : beautySalon.getAppointments()) {
			LocalDate appointmentDate = a.getTimeslot().getStartTime().toLocalDate();
			if(appointmentDate.isAfter(startDate.minusDays(1)) && appointmentDate.isBefore(endDate.plusDays(1))) {
				int score = scores.get(a.getStatus().toString());
				score += 1;
				scores.put(a.getStatus().toString(), score);
			}
		}
		
		return scores;
	}
	
	
}
