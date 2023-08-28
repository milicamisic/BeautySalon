package service;

import java.util.ArrayList;

import humanEntities.Beautician;
import humanEntities.Client;
import humanEntities.Manager;
import humanEntities.Receptionist;
import humanEntities.Role;
import humanEntities.Sex;
import humanEntities.User;
import paket1.BeautySalon;

public class UserService {
	
	private static BeautySalon beautySalon = BeautySalon.getBeautySalon();
	
	public UserService() {}
	
	public static Role login(String username, char[] pass) {
		Role role = null;
		String password = new String(pass);
		
		for(Client c : beautySalon.getClients()) {
			if(c.getUsername().equals(username) && c.getPassword().equals(password)) {
				role = Role.CLIENT;
			}
		}
		
		for(Beautician b : beautySalon.getBeauticians()) {
			if(b.getUsername().equals(username) && b.getPassword().equals(password)) {
				role = Role.BEAUTICIAN;
			}
		}
		
		for(Receptionist r : beautySalon.getReceptionists()) {
			if(r.getUsername().equals(username) && r.getPassword().equals(password)) {
				role = Role.RECEPTIONIST;
			}
		}
		
		for(Manager m : beautySalon.getManagers()) {
			if(m.getUsername().equals(username) && m.getPassword().equals(password)) {
				role = Role.MANAGER;
			}
		}
		
		if(role != null) {
			User currentUser = getUserByUsername(username);
			beautySalon.setCurrentUser(currentUser);
		}
		
		return role;
	}
	
	public static boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	
	public static boolean isNumber(String name) {
	    return name.matches("[0-9]+");
	}
	
	public static boolean usernameTaken(String username) {
		ArrayList<User> users = beautySalon.getUsers();
		
		for(User u : users) {
			if(username.equals(u.getUsername()))
				return true;
		}
		return false;
	}
	
	public void registerClient(String name, String surname, Sex sex, String phoneNumber, String address, String username, char[] passwordChars) 
	{
		String password = String.valueOf(passwordChars);
		
		Client c = new Client(name, surname, sex, phoneNumber, address, username, password, 0, false);
		beautySalon.addClient(c);
	}
	
	public static User getUserByUsername(String username)
	{
		for(User u : beautySalon.getUsers())
		{
			if(u.getUsername().equals(username))
			{
				return u;
			}
		}
		return null;
	}
}
