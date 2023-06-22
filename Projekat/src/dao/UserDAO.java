package dao;

import java.util.ArrayList;

import humanEntities.User;
import observer.IObserver;
import storage.UserStorage;

public class UserDAO {
	private ArrayList<IObserver> _observers;
	private UserStorage _storage;
	private ArrayList<User> _users;
	
	public UserDAO() {
		
		_observers = new ArrayList<IObserver>();
		_storage = new UserStorage();
		_users = _storage.load();
	}
	
	public void add(User user) {
		
		_users.add(user);
		_storage.save(_users);
		notifyObservers();
	}
	
	public void remove(User user) {
		
		_users.remove(user);
		_storage.save(_users);
		notifyObservers();
	}
	
	public ArrayList<User> getAll() {
		
		return _users;
	}
	
	public User getByUsername(String userUsername) {
		
		for(User user : _users) {
			
			if(user.getUsername().equals(userUsername)) {
				return user;
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
