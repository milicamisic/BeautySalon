package service;

import otherEntities.Service;
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
}
