package storage;

import java.io.File;
import java.util.ArrayList;

import citanje.AppointmentReader;
import otherEntities.Appointment;
import pisanje.MyWriter;

public class AppointmentStorage {
	private final String SEPARATOR = File.separator;
	private final String STORAGE_PATH = "src" + SEPARATOR + "data" + SEPARATOR + "appointments3";
	
	public ArrayList<Appointment> load() {
		
		AppointmentReader appointmentReader = new AppointmentReader(STORAGE_PATH);
		return appointmentReader.loadAppointments();
	}
	
	public void save(ArrayList<Appointment> appointments) {
		MyWriter writer = new MyWriter(STORAGE_PATH);
		writer.write(appointments);
	}
}
