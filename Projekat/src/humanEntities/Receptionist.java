package humanEntities;

import java.util.ArrayList;

import citanje.AppointmentReader;
import otherEntities.Appointment;
import pisanje.MyWriter;

public class Receptionist extends Worker{

	public Receptionist(String name, String surname, Sex sex, String phoneNumber, String address, String username,
			String password, int proEduLvl, int serviceYears, double bonus, double basePay) {
		super(name, surname, sex, phoneNumber, address, username, password, proEduLvl, serviceYears, bonus, basePay);
	}
	
	// CRUD for appointments
	public void addAppointment(Appointment a) {
		MyWriter mw = new MyWriter("src/data/appointments2");
		mw.write(a);
	}
	
	public boolean removeAppointment(int id) {
		AppointmentReader ar = new AppointmentReader("src/data/appointments2");
		ArrayList<Appointment> appointments = ar.loadAppointments();
		
		boolean found = false;
		for(int i = 0; i < appointments.size(); i++) {
			if(appointments.get(i).getId() == id) {
				appointments.remove(i);
				found = true;
				break;
			}
		}
		
		if(found) {
			MyWriter mw = new MyWriter("src/data/appointments2");
			mw.write(appointments);
			return true;
		}
		
		return false;
	}
	
	public boolean modifyAppointment(Appointment a) {
		AppointmentReader ar = new AppointmentReader("src/data/appointments2");
		ArrayList<Appointment> appointments = ar.loadAppointments();
		
		boolean found = false;
		for(int i = 0; i < appointments.size(); i++) {
			if(appointments.get(i).getId() == a.getId()) {
				appointments.set(i, a);
				found = true;
				break;
			}
		}
		
		if(found) {
			MyWriter mw = new MyWriter("src/data/appointments2");
			mw.write(appointments);
			return true;
		}
		
		return false;
	}

	public void viewAppointments() {
		AppointmentReader ar = new AppointmentReader("src/data/appointments2");
		ArrayList<Appointment> appointments = ar.loadAppointments();
		
		for(Appointment a : appointments)
			System.out.println(a);
	}

}
