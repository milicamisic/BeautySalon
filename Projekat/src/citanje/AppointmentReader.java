package citanje;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import humanEntities.Beautician;
import humanEntities.Client;
import otherEntities.Appointment;
import otherEntities.AppointmentStatus;
import otherEntities.Service;
import otherEntities.Timeslot;
import service.ServiceService;

public class AppointmentReader {
	
	String fileName;
	ServiceService serviceService;
	
	public AppointmentReader(String fileName) {
		this.fileName = fileName;
		this.serviceService = new ServiceService();
	}
	
	public ArrayList<Appointment> loadAppointments() {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		
		try {
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											new FileInputStream(this.fileName), "utf-8"));
			String line;
			
			while((line = br.readLine()) != null) {
				String[] tokens = line.split("\\|");
				
				int id = Integer.parseInt(tokens[0]);
				Beautician b = Beautician.findBeauticianByUsername(tokens[1]);
				Client c = Client.findClientByUsername(tokens[2]);
				String[] ts = tokens[3].split(";");
				Timeslot t = new Timeslot(LocalDateTime.parse(ts[0]), LocalDateTime.parse(ts[1]));
				Service service = serviceService.getServiceByName(tokens[4]);
				AppointmentStatus status = AppointmentStatus.valueOf(tokens[5]);
				double price = Double.parseDouble(tokens[6]);
				
				Appointment a = new Appointment(id, b, c, t, service, status,price);
				appointments.add(a);
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return appointments;
	}
}
