package controller;

import java.util.ArrayList;

import dao.ServiceDAO;
import observer.IObserver;
import otherEntities.Service;

public class ServiceController {
	
	private ServiceDAO _services;
	
	public ServiceController() {
		
		_services = new ServiceDAO();
	}
	
	public ArrayList<Service> getAllServices() {
		
		return _services.getAll();
	}
	
	public void create(Service service) {
		
		_services.add(service);
	}
	
	public void delete(Service service) {
		
		_services.remove(service);
	}
	
	public void subscribe(IObserver observer) {
		
		_services.subscribe(observer);
	}
}
