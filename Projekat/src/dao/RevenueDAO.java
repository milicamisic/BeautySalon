package dao;

import java.util.ArrayList;

import observer.IObserver;
import observer.ISubject;
import otherEntities.Revenue;
import storage.RevenueStorage;

public class RevenueDAO implements ISubject{
	private ArrayList<IObserver> _observers;
	private RevenueStorage _storage;
	private ArrayList<Revenue> _revenues;
	
	public RevenueDAO() {
		
		_observers = new ArrayList<IObserver>();
		_storage = new RevenueStorage();
		_revenues = _storage.load();
	}
	
	public void add(Revenue revenue) {
		
		_revenues.add(revenue);
		_storage.save(_revenues);
		notifyObservers();
	}
	
	public void remove(Revenue revenue) {
		
		_revenues.remove(revenue);
		_storage.save(_revenues);
		notifyObservers();
	}
	
	public ArrayList<Revenue> getAll() {
		
		return _revenues;
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
