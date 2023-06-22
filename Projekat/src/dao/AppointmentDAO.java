package dao;

import java.util.ArrayList;

import observer.IObserver;
import observer.ISubject;
import otherEntities.Appointment;
import storage.AppointmentStorage;

public class AppointmentDAO implements ISubject{
	private ArrayList<IObserver> _observers;
	private AppointmentStorage _storage;
	private ArrayList<Appointment> _appointments;
	
	public AppointmentDAO() {
		
		_observers = new ArrayList<IObserver>();
		_storage = new AppointmentStorage();
		_appointments = _storage.load();
	}
	
	public int nextId() {
		
		if(_appointments.size() == 0) {
			return 0;
		}
		int maxId = 0;
		for(Appointment appointment : _appointments) {
			if(appointment.getId() > maxId) {
				maxId = appointment.getId(); 
			}
		}
		return maxId;
	}
	
	public void add(Appointment appointment) {
		
		_appointments.add(appointment);
		_storage.save(_appointments);
		notifyObservers();
	}
	
	public void updateIndices(int removedId) {
		for(; removedId < _appointments.size(); removedId++) {
			_appointments.get(removedId).setId(removedId);
		}
	}
	
	public void remove(Appointment appointment) {
		
		_appointments.remove(appointment);
		_storage.save(_appointments);
		notifyObservers();
	}
	
	public ArrayList<Appointment> getAll() {
		
		return _appointments;
	}
	
	public Appointment getById(int appointmentId) {
		
		for(Appointment appointment : _appointments) {
			
			if(appointment.getId() == appointmentId) {
				return appointment;
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
