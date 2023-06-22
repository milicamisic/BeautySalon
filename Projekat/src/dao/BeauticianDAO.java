package dao;

import java.util.ArrayList;

import humanEntities.Beautician;
import observer.IObserver;
import storage.BeauticianStorage;

public class BeauticianDAO{
	private ArrayList<IObserver> _observers;
	private BeauticianStorage _storage;
	private ArrayList<Beautician> _beauticians;
	
	public BeauticianDAO() {
		
		_observers = new ArrayList<IObserver>();
		_storage = new BeauticianStorage();
		_beauticians = _storage.load();
	}
	
	public void add(Beautician beautician) {
		
		_beauticians.add(beautician);
		_storage.save(_beauticians);
		notifyObservers();
	}
	
	public void remove(Beautician beautician) {
		
		_beauticians.remove(beautician);
		_storage.save(_beauticians);
		notifyObservers();
	}
	
	public ArrayList<Beautician> getAll() {
		
		return _beauticians;
	}
	
	public Beautician getByUsername(String beauticianUsername) {
		
		for(Beautician beautician : _beauticians) {
			
			if(beautician.getUsername().equals(beauticianUsername)) {
				return beautician;
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
