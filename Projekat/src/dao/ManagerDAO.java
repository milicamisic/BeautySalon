package dao;

import java.util.ArrayList;

import humanEntities.Manager;
import observer.IObserver;
import storage.ManagerStorage;

public class ManagerDAO {
	
	private ArrayList<IObserver> _observers;
	private ManagerStorage _storage;
	private ArrayList<Manager> _managers;
	
	public ManagerDAO() {
		
		_observers = new ArrayList<IObserver>();
		_storage = new ManagerStorage();
		_managers = _storage.load();
	}
	
	public void add(Manager manager) {
		
		_managers.add(manager);
		_storage.save(_managers);
		notifyObservers();
	}
	
	public void remove(Manager manager) {
		
		_managers.remove(manager);
		_storage.save(_managers);
		notifyObservers();
	}
	
	public ArrayList<Manager> getAll() {
		
		return _managers;
	}
	
	public Manager getByUsername(String managerUsername) {
		
		for(Manager manager : _managers) {
			
			if(manager.getUsername().equals(managerUsername)) {
				return manager;
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

















