package service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import humanEntities.Beautician;
import humanEntities.Client;
import otherEntities.Appointment;
import otherEntities.AppointmentStatus;
import otherEntities.Service;
import otherEntities.Timeslot;
import paket1.BeautySalon;

public class AppointmentService {
	
	private BeautySalon beautySalon;
	private BeauticianService beauticianService;
	private ClientService clientService;
	
	public AppointmentService() {
		beautySalon = BeautySalon.getBeautySalon();
		beauticianService = new BeauticianService();
		clientService = new ClientService();
	}
	
	public Appointment getAppointmentById(int id) {
		for(Appointment a : beautySalon.getAppointments()) {
			if(a.getId() == id) {
				return a;
			}
		}
		return null;
	}

	public void endAppointment(Appointment appointment) {
		appointment.setStatus(AppointmentStatus.COMPLETED);
		beautySalon.modifyAppointment(appointment);
	}
	
	public ArrayList<Appointment> getScheduledAppointmentsForDay(LocalDate date) {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		for(Appointment a: beautySalon.getAppointments()) {
			if(a.getTimeslot().getStartTime().toLocalDate().equals(date) && a.getStatus() == AppointmentStatus.SCHEDULED) {
				appointments.add(a);
			}
		}
		return appointments;
	}
	
	public static LocalTime roundUpToNextHour(LocalTime time) {
        int currentMinute = time.getMinute();
        
        if (currentMinute == 0) {
            return time;
        } else {
            int minutesToNextHour = 60 - currentMinute;
            return time.plusMinutes(minutesToNextHour);
        }
    }
	
	public ArrayList<LocalTime> generateAllPossibleAppointmentTimes(int duration) {
        ArrayList<LocalTime> possibleTimes = new ArrayList<LocalTime>();
        
        LocalTime startTime = roundUpToNextHour(beautySalon.getWorkingHoursStart());
        LocalTime endTime = beautySalon.getWorkingHoursEnd();
        while (startTime.plusMinutes(duration).isBefore(endTime)) {
            possibleTimes.add(startTime);
            startTime = startTime.plusMinutes(60); // You can adjust the interval as needed
        }
        
        return possibleTimes;
    }
	
	public ArrayList<Appointment> getAllPossibleAppointmentsForDay(Client client, LocalDate date, Service service) {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		ArrayList<LocalTime> startTimeOptions = generateAllPossibleAppointmentTimes(service.getDurationInMinutes());
		
		for(LocalTime time : startTimeOptions) {
			LocalDateTime startTime = date.atTime(time);
			if(startTime.isBefore(LocalDateTime.now())) continue;
			
			LocalDateTime endTime = date.atTime(time).plusMinutes(service.getDurationInMinutes());
			Timeslot timeslot = new Timeslot(startTime, endTime); 
			appointments.add(new Appointment(-1, client, timeslot, service, AppointmentStatus.SCHEDULED, service.getPrice()));
		}
		
		
		// we want to see if there is an available beautician for that appointment and if the client is available
		ArrayList<Appointment> validAppointments = new ArrayList<Appointment>();
		for(Appointment a : appointments) {
			Beautician b = beauticianService.getAvailableBeautician(a);
			
			if(b != null && clientService.isAvailable(client, a.getTimeslot())) {
				a.setBeautician(b);
				validAppointments.add(a);
			}
		}
		
		return validAppointments;
	}
	
	public ArrayList<Appointment> getClientAppointments(Client client) {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		for(Appointment a : beautySalon.getAppointments()) {
			if(a.getClient().equals(client)) {
				appointments.add(a);
			}
		}
		return appointments;
	}
	
	public ArrayList<Appointment> getAppointmentsByService(String serviceName) {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		for(Appointment a : beautySalon.getAppointments()) {
			if(a.getService().getName().equals(serviceName)) {
				appointments.add(a);
			}
		}
		return appointments;
	}
	
	public ArrayList<Appointment> getAppointmentsByServiceType(String serviceType) {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		for(Appointment a : beautySalon.getAppointments()) {
			if(a.getService().getType().getType().equals(serviceType)) {
				appointments.add(a);
			}
		}
		return appointments;
	}
	
}
