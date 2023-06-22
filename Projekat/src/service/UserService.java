package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import humanEntities.Client;
import humanEntities.Role;
import humanEntities.Sex;
import humanEntities.User;
import paket1.BeautySalon;

public class UserService {
	
	private static BeautySalon beautySalon = BeautySalon.getBeautySalon();
	
	public UserService() {}
	
	public static Role login(String username, String pass) {
		Role role = null;
		try {
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											new FileInputStream("src/data/login_info"), "utf-8"));
			String line;
			
			while((line = br.readLine()) != null) {
				String[] tokens = line.split("\\|");
				
				if(tokens[0].equals(username) && tokens[1].equals(pass)) {
					role = Role.valueOf(tokens[2]);
				}
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
	
	public static void registerClient(String name, String surname, Sex sex, String phoneNumber, String address, String username, String password) 
	{
		User u = new User(name, surname, sex, phoneNumber, address, username, password);
		beautySalon.addUser(u);
		
		Client c = new Client(name, surname, sex, phoneNumber, address, username, password, 0, false);
		beautySalon.addClient(c);
		
		try {
			BufferedWriter bw = new BufferedWriter(
									new OutputStreamWriter(
											new FileOutputStream("src/data/login_info", true), "utf-8"));
			
			bw.write(username + "|" + password + "|" + "Client\n");
			
			bw.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
