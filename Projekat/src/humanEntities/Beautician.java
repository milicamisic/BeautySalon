package humanEntities;

import java.util.ArrayList;

import citanje.AppointmentReader;
import citanje.BeauticianReader;
import otherEntities.Appointment;
import otherEntities.ServiceType;

public class Beautician extends Worker{

	private ArrayList<ServiceType> trainedFor;
	
	public Beautician(String name, String surname, Sex sex, String phoneNumber, String address, String username,
			String password, int proEduLvl, int serviceYears, double bonus, double basePay, ArrayList<ServiceType> trainedFor) {
		super(name, surname, sex, phoneNumber, address, username, password, proEduLvl, serviceYears, bonus, basePay);
		
		this.trainedFor = trainedFor;
	}
	
	public String toString() {
		String str =  "Beautician [name=" + name + ", surname=" + surname + ", sex=" + sex
						+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", username=" + username + ", password="
						+ password + ", proEduLvl=" + proEduLvl + ", serviceYears=" + serviceYears + ", bonus=" + bonus + ", basePay="
								+ basePay + ", pay=" + pay + ", trainedFor= [";
		
		if (!trainedFor.isEmpty()) {
			str += trainedFor.get(0);
			
			for(int i = 1; i < trainedFor.size(); i++)
				str += ", " + trainedFor.get(i).toString();
		}
		str += "]]";
		return str;
	}
	
	@Override
	public String toLine() {
		String line =  name + "|" + surname + "|" + sex.toString() + "|" + phoneNumber + "|" + address + 
					   "|" + username + "|" + password + "|" + Integer.toString(proEduLvl) + "|" +
					   Integer.toString(serviceYears) + "|" + Double.toString(bonus) + "|" +
					   Double.toString(basePay) + "|";
		
		if (!trainedFor.isEmpty()) {
			line += trainedFor.get(0);
			
			for(int i = 1; i < trainedFor.size(); i++)
				line += "," + trainedFor.get(i).toString();
		}
		
		return line;
	}
	
	public void addSkill(ServiceType st) {
		this.trainedFor.add(st);
	}

	public static Beautician findBeauticianByUsername(String username) {
		BeauticianReader br = new BeauticianReader("src/data/beauticians3");
		ArrayList<Beautician> beauticians = br.loadBeauticians();
		
		for(int i = 0; i < beauticians.size(); i++) {
			if(beauticians.get(i).username.equals(username)) {
				return beauticians.get(i);
			}
		}
		
		return null;
	}
	
	public void viewSchedule() {
		AppointmentReader ar = new AppointmentReader("src/data/appointments2");
		ArrayList<Appointment> appointments = ar.loadAppointments();
		
		for(Appointment a: appointments) {
			if(a.getBeautician().getUsername().equals(this.username)) {
				System.out.println(a);
			}
		}
	}
	
	public void viewAppointment(int id) {
		AppointmentReader ar = new AppointmentReader("src/data/appointments2");
		ArrayList<Appointment> appointments = ar.loadAppointments();
		
		for(Appointment a: appointments) {
			if(a.getId() == id) {
				System.out.println(a);
				break;
			}
		}
	}

}





















