package controller;

import java.util.ArrayList;

import dao.ServiceTypeDAO;
import observer.IObserver;
import otherEntities.ServiceType;

public class ServiceTypeController {
	
	private ServiceTypeDAO _serviceTypes;
	
	public ServiceTypeController() {
		
		_serviceTypes = new ServiceTypeDAO();
	}
	
	public ArrayList<ServiceType> getAllServiceTypes() {
		
		return _serviceTypes.getAll();
	}
	
	public void create(ServiceType serviceType) {
		
		_serviceTypes.add(serviceType);
	}
	
	public void delete(ServiceType serviceType) {
		
		_serviceTypes.remove(serviceType);
	}
	
	public void subscribe(IObserver observer) {
		
		_serviceTypes.subscribe(observer);
	}
}
