package service;

import java.util.ArrayList;

import humanEntities.Beautician;
import otherEntities.Appointment;
import otherEntities.AppointmentStatus;
import otherEntities.Service;
import otherEntities.ServiceType;
import otherEntities.Timeslot;
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
	
	public ArrayList<Service> getBeauticianServices(Beautician beautician) 
	{
		ArrayList<Service> beauticianServices = new ArrayList<>();
		ArrayList<String> beauticianSkills = new ArrayList<String>();
		for(ServiceType st : beautician.getSkills())
		{
			beauticianSkills.add(st.getType());
		}
		
		for(Service s : beautySalon.getServices())
		{
			if(beauticianSkills.contains(s.getType().getType()))
				beauticianServices.add(s);
		}
		return beauticianServices;
	}
	
	public ArrayList<Appointment> getScheduledAppointments(Beautician beautician)
	{
		ArrayList<Appointment> scheduledAppointments = new ArrayList<Appointment>();
		for(Appointment a : beautySalon.getAppointments())
		{
			if(a.getBeautician().equals(beautician) && a.getStatus().equals(AppointmentStatus.SCHEDULED))
			{
				scheduledAppointments.add(a);
			}
		}
		return scheduledAppointments;
	}
	
	public boolean isAvailable(Beautician beautician, Timeslot timeslot)
	{
		ArrayList<Appointment> scheduledAppointments = getScheduledAppointments(beautician);
		
		if(scheduledAppointments == null)
			return true;
		for(Appointment a : scheduledAppointments)
		{
			if(TimeslotService.areOverlaping(a.getTimeslot(), timeslot))
			{
				return false;
			}
		}
		return true;
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

	public boolean canPerform(Beautician b, Service service) {
		ArrayList<Service> services = getBeauticianServices(b);
		for(Service s : services)
		{
			if(s.equals(service))
			{
				return true;
			}
		}
		return false;
	}
}
