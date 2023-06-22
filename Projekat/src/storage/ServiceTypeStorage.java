package storage;

import java.io.File;
import java.util.ArrayList;

import citanje.ServiceTypeReader;
import otherEntities.ServiceType;
import pisanje.MyWriter;

public class ServiceTypeStorage {
	private final String SEPARATOR = File.separator;
	private final String STORAGE_PATH = "src" + SEPARATOR + "data" + SEPARATOR + "service_types3";
	
	public ArrayList<ServiceType> load() {
		
		ServiceTypeReader serviceTypeReader = new ServiceTypeReader(STORAGE_PATH);
		return serviceTypeReader.loadServiceTypes();
	}
	
	public void save(ArrayList<ServiceType> serviceTypes) {
		MyWriter writer = new MyWriter(STORAGE_PATH);
		writer.write(serviceTypes);
	}
}
