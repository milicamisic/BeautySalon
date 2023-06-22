package controller;

import java.util.ArrayList;

import dao.UserDAO;
import humanEntities.User;
import observer.IObserver;

public class UserController {
private UserDAO _users;
	
	public UserController() {
		
		_users = new UserDAO();
	}
	
	public ArrayList<User> getAllUsers() {
		
		return _users.getAll();
	}
	
	public User getUserByUsername(String userUsername) {
		
		return _users.getByUsername(userUsername);
	}
	
	public void create(User user) {
		
		_users.add(user);
	}
	
	public void delete(User user) {
		
		_users.remove(user);
	}
	
	public void subscribe(IObserver observer) {
		
		_users.subscribe(observer);
	}
}
