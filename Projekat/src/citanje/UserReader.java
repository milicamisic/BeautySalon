package citanje;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import humanEntities.Sex;
import humanEntities.User;

public class UserReader {
	
	String fileName;
	
	public UserReader(String fileName) {
		this.fileName = fileName;
	}
	
	public ArrayList<User> loadUsers() {
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											new FileInputStream(this.fileName), "utf-8"));
			String line;
			
			while((line = br.readLine()) != null) {
				String[] tokens = line.split("\\|");
				
				Sex sex = Sex.valueOf(tokens[2]);
				
				User u = new User(tokens[0], tokens[1], sex, tokens[3], tokens[4], tokens[5], tokens[6]);
				users.add(u);
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return users;
	}
}
