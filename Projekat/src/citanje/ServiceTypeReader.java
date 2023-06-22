package citanje;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import otherEntities.ServiceType;

public class ServiceTypeReader {
String fileName;
	
	public ServiceTypeReader(String fileName) {
		this.fileName = fileName;
	}
	
	public ArrayList<ServiceType> loadServiceTypes() {
		ArrayList<ServiceType> serviceTypes = new ArrayList<ServiceType>();
		
		try {
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											new FileInputStream(this.fileName), "utf-8"));
			String line;
			
			while((line = br.readLine()) != null) {
				ServiceType u = new ServiceType(line);
				serviceTypes.add(u);
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return serviceTypes;
	}
}