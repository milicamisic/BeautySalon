package service;

import humanEntities.Beautician;
import otherEntities.Appointment;
import paket1.BeautySalon;

public class BeauticianService {
	private BeautySalon beautySalon;
	
	public BeauticianService() {
		this.beautySalon = BeautySalon.getBeautySalon();
	}
	
	public Beautician getBeauticianByUsername(String username)
	{
		for(Beautician b : beautySalon.getBeauticians())
		{
			if(b.getUsername().equals(username))
			{
				return b;
			}
		}
		return null;
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
