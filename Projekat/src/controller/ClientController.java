package controller;

import java.util.ArrayList;

import dao.ClientDAO;
import humanEntities.Client;
import observer.IObserver;

public class ClientController {
	
	private ClientDAO _clients;
	
	public ClientController() {
		
		_clients = new ClientDAO();
	}
	
	public ArrayList<Client> getAllClients() {
		
		return _clients.getAll();
	}
	
	public Client getClientByUsername(String clientUsername) {
		
		return _clients.getByUsername(clientUsername);
	}
	
	public void create(Client client) {
		
		_clients.add(client);
	}
	
	public void delete(Client client) {
		
		_clients.remove(client);
	}
	
	public void subscribe(IObserver observer) {
		
		_clients.subscribe(observer);
	}
}
