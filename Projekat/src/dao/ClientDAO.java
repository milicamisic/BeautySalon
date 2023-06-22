package dao;

import java.util.ArrayList;

import humanEntities.Client;
import observer.IObserver;
import storage.ClientStorage;

public class ClientDAO {
	private ArrayList<IObserver> _observers;
	private ClientStorage _storage;
	private ArrayList<Client> _clients;
	
	public ClientDAO() {
		
		_observers = new ArrayList<IObserver>();
		_storage = new ClientStorage();
		_clients = _storage.load();
	}
	
	public void add(Client client) {
		
		_clients.add(client);
		_storage.save(_clients);
		notifyObservers();
	}
	
	public void remove(Client client) {
		
		_clients.remove(client);
		_storage.save(_clients);
		notifyObservers();
	}
	
	public ArrayList<Client> getAll() {
		
		return _clients;
	}
	
	public Client getByUsername(String clientUsername) {
		
		for(Client client : _clients) {
			
			if(client.getUsername().equals(clientUsername)) {
				return client;
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
