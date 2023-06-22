package service;

import humanEntities.Beautician;
import otherEntities.Appointment;
import otherEntities.AppointmentStatus;
import paket1.BeautySalon;

public class BeauticianService {
	private BeautySalon beautySalon;
	
	public BeauticianService() {
		this.beautySalon = BeautySalon.getBeautySalon();
	}
	
	public void viewSchedule(Beautician beautician) {
		for(Appointment a: beautySalon.getAppointments()) {
			if(a.getBeautician().equals(beautician)) {
				System.out.println(a);
			}
		}
	}
	
	public void viewAppointment(Appointment appointment) {
		System.out.println(appointment);
	}
	
	public int getAppointmentNumber(Beautician beautician) {
		int num = 0;
		for(Appointment a: beautySalon.getAppointments()) {
			if(a.getBeautician().equals(beautician)) {
				num++;
			}
		}
		return num;
	}
}