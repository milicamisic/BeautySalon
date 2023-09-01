package storage;

import java.io.File;
import java.util.ArrayList;

import citanje.UserReader;
import humanEntities.User;
import pisanje.MyWriter;

public class UserStorage {
	private final String SEPARATOR = File.separator;
	private final String STORAGE_PATH = "src" + SEPARATOR + "data" + SEPARATOR + "users";
	
	public ArrayList<User> load() {
		
		UserReader userReader = new UserReader(STORAGE_PATH);
		return userReader.loadUsers();
	}
	
	public void save(ArrayList<User> users) {
		MyWriter writer = new MyWriter(STORAGE_PATH);
		writer.write(users);
	}
}
