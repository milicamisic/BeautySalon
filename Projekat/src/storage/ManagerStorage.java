package storage;

import java.io.File;
import java.util.ArrayList;

import citanje.ManagerReader;
import humanEntities.Manager;
import pisanje.MyWriter;

public class ManagerStorage {
	
	private final String SEPARATOR = File.separator;
	private final String STORAGE_PATH = "src" + SEPARATOR + "data" + SEPARATOR + "managers";
	
	public ArrayList<Manager> load() {
		
		ManagerReader managerReader = new ManagerReader(STORAGE_PATH);
		return managerReader.loadManagers();
	}
	
	public void save(ArrayList<Manager> managers) {
		MyWriter writer = new MyWriter(STORAGE_PATH);
		writer.write(managers);
	}
}
