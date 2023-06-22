package citanje;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import otherEntities.ServiceType;
import otherEntities.Service;

public class ServiceReader {
	String fileName;
	
	public ServiceReader(String fileName) {
		this.fileName = fileName;
	}
	
	public ArrayList<Service> loadServices() {
		ArrayList<Service> services = new ArrayList<Service>();
		
		try {
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											new FileInputStream(this.fileName), "utf-8"));
			String line;
			
			while((line = br.readLine()) != null) {
				String[] tokens = line.split("\\|");
				
				ServiceType type = new ServiceType(tokens[1]);
				int duration = Integer.parseInt(tokens[2]);
				double price = Double.parseDouble(tokens[3]);
				
				Service s = new Service(tokens[0], type, duration, price);
				services.add(s);
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return services;
	}

}
