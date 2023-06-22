package storage;

import java.io.File;
import java.util.ArrayList;

import citanje.ServiceReader;
import otherEntities.Service;
import pisanje.MyWriter;

public class ServiceStorage {
	private final String SEPARATOR = File.separator;
	private final String STORAGE_PATH = "src" + SEPARATOR + "data" + SEPARATOR + "services3";
	
	public ArrayList<Service> load() {
		
		ServiceReader serviceReader = new ServiceReader(STORAGE_PATH);
		return serviceReader.loadServices();
	}
	
	public void save(ArrayList<Service> services) {
		MyWriter writer = new MyWriter(STORAGE_PATH);
		writer.write(services);
	}
}
