package controller;

import java.util.ArrayList;

import dao.AppointmentDAO;
import observer.IObserver;
import otherEntities.Appointment;

public class AppointmentController {
	
	private AppointmentDAO _appointments;
	
	public AppointmentController() {
		
		_appointments = new AppointmentDAO();
	}
	
	public ArrayList<Appointment> getAllAppointments() {
		
		return _appointments.getAll();
	}
	
	public Appointment getAppointmentById(int appointmentId) {
		
		return _appointments.getById(appointmentId);
	}
	
	public void create(Appointment appointment) {
		
		_appointments.add(appointment);
	}
	
	public void delete(Appointment appointment) {
		
		_appointments.remove(appointment);
	}
	
	public void subscribe(IObserver observer) {
		
		_appointments.subscribe(observer);
	}
	
}
