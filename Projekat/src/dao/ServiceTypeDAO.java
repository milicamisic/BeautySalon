package dao;

import java.util.ArrayList;

import observer.IObserver;
import otherEntities.ServiceType;
import storage.ServiceTypeStorage;

public class ServiceTypeDAO {
	private ArrayList<IObserver> _observers;
	private ServiceTypeStorage _storage;
	private ArrayList<ServiceType> _serviceTypes;
	
	public ServiceTypeDAO() {
		
		_observers = new ArrayList<IObserver>();
		_storage = new ServiceTypeStorage();
		_serviceTypes = _storage.load();
	}
	
	public void add(ServiceType serviceType) {
		
		_serviceTypes.add(serviceType);
		_storage.save(_serviceTypes);
		notifyObservers();
	}
	
	public void remove(ServiceType serviceType) {
		
		_serviceTypes.remove(serviceType);
		_storage.save(_serviceTypes);
		notifyObservers();
	}
	
	public ArrayList<ServiceType> getAll() {
		
		return _serviceTypes;
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
