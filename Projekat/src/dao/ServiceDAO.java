package dao;

import java.util.ArrayList;

import observer.IObserver;
import observer.ISubject;
import otherEntities.Service;
import storage.ServiceStorage;

public class ServiceDAO implements ISubject{
	private ArrayList<IObserver> _observers;
	private ServiceStorage _storage;
	private ArrayList<Service> _services;
	
	public ServiceDAO() {
		
		_observers = new ArrayList<IObserver>();
		_storage = new ServiceStorage();
		_services = _storage.load();
	}
	
	public void add(Service service) {
		
		_services.add(service);
		_storage.save(_services);
		notifyObservers();
	}
	
	public void remove(Service service) {
		
		_services.remove(service);
		_storage.save(_services);
		notifyObservers();
	}
	
	public ArrayList<Service> getAll() {
		
		return _services;
	}
	
	public Service getByName(String serviceName) {
		
		for(Service service : _services) {
			
			if(service.getName().equals(serviceName)) {
				return service;
			}
		}
		
		return null;
	}
	
	public void subscribe(IObserver observer) {
		
		_observers.add(observer);
	}
	
	public void unsubscribe(IObserver observer) {
		
		_observers.remove(observer);
	}
	
	public void notifyObservers() {
		
		for(IObserver observer : _observers) {
			
			observer.update();
		}
	}
}
