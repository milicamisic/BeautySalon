package service;

import java.util.ArrayList;

import otherEntities.Service;
import otherEntities.ServiceType;
import paket1.BeautySalon;

public class ServiceService {

private BeautySalon beautySalon;
	
	public ServiceService() {
		beautySalon = BeautySalon.getBeautySalon();
	}
	
	public Service getServiceByName(String name) {
		for(Service s : beautySalon.getServices()) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
	
	public ServiceType getServiceTypeByName(String name) {
		for(ServiceType st : beautySalon.getServiceTypes()) {
			if(st.getType().equals(name)) {
				return st;
			}
		}
		return null;
	}
	
	public ArrayList<Service> getAppropriateServices(int fromDuration, int toDuration, double fromPrice, double toPrice, ServiceType serviceType) {
		ArrayList<Service> appropriateServices = new ArrayList<Service>();
		for(Service service : beautySalon.getServices()) {
			int duration = service.getDurationInMinutes();
			double price = service.getPrice();
			ServiceType type = service.getType();
			if((fromDuration <= duration && duration <= toDuration) && (fromPrice <= price && price <= toPrice) && type.getType().equals(serviceType.getType())) {
				appropriateServices.add(service);
			}
		}
		return appropriateServices;
	}
	
	public ArrayList<Service> getAppropriateServices(ServiceType serviceType) {
		ArrayList<Service> appropriateServices = new ArrayList<Service>();
		for(Service service : beautySalon.getServices()) {
			ServiceType type = service.getType();
			if(type.getType().equals(serviceType.getType())) {
				appropriateServices.add(service);
			}
		}
		return appropriateServices;
	}
	
	
}
