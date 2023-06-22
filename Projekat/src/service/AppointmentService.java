package service;

import otherEntities.Appointment;
import otherEntities.AppointmentStatus;
import paket1.BeautySalon;

public class AppointmentService {
	
	private BeautySalon beautySalon;
	
	public AppointmentService() {
		beautySalon = BeautySalon.getBeautySalon();
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
}
