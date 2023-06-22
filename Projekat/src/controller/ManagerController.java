package controller;

import java.util.ArrayList;

import dao.ManagerDAO;
import humanEntities.Manager;
import observer.IObserver;

public class ManagerController {
	
	private ManagerDAO _managers;
	
	public ManagerController() {
		
		_managers = new ManagerDAO();
	}
	
	public ArrayList<Manager> getAllManagers() {
		
		return _managers.getAll();
	}
	
	public Manager getManagerByUsername(String managerUsername) {
		
		return _managers.getByUsername(managerUsername);
	}
	
	public void create(Manager manager) {
		
		_managers.add(manager);
	}
	
	public void delete(Manager manager) {
		
		_managers.remove(manager);
	}
	
	public void subscribe(IObserver observer) {
		
		_managers.subscribe(observer);
	}
	
}
