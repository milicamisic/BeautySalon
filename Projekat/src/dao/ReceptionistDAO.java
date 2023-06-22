package dao;

import java.util.ArrayList;

import humanEntities.Receptionist;
import observer.IObserver;
import storage.ReceptionistStorage;

public class ReceptionistDAO {
	private ArrayList<IObserver> _observers;
	private ReceptionistStorage _storage;
	private ArrayList<Receptionist> _receptionists;
	
	public ReceptionistDAO() {
		
		_observers = new ArrayList<IObserver>();
		_storage = new ReceptionistStorage();
		_receptionists = _storage.load();
	}
	
	public void add(Receptionist receptionist) {
		
		_receptionists.add(receptionist);
		_storage.save(_receptionists);
		notifyObservers();
	}
	
	public void remove(Receptionist receptionist) {
		
		_receptionists.remove(receptionist);
		_storage.save(_receptionists);
		notifyObservers();
	}
	
	public ArrayList<Receptionist> getAll() {
		
		return _receptionists;
	}
	
	public Receptionist getByUsername(String receptionistUsername) {
		
		for(Receptionist receptionist : _receptionists) {
			
			if(receptionist.getUsername().equals(receptionistUsername)) {
				return receptionist;
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
