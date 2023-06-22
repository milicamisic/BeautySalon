package citanje;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import humanEntities.Client;
import humanEntities.Sex;

public class ClientReader {
	
	String fileName;
	
	public ClientReader(String fileName) {
		this.fileName = fileName;
	}
	
	public ArrayList<Client> loadClients() {
		ArrayList<Client> clients = new ArrayList<Client>();
		
		try {
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											new FileInputStream(this.fileName), "utf-8"));
			String line;
			
			while((line = br.readLine()) != null) {
				String[] tokens = line.split("\\|");
				
				Sex sex = Sex.valueOf(tokens[2]);
				double moneySpent = Double.parseDouble(tokens[7]);
				boolean hasLoyaltyCard = Boolean.parseBoolean(tokens[8]);
				
				Client c = new Client(tokens[0], tokens[1], sex, tokens[3], tokens[4], tokens[5], tokens[6], moneySpent, hasLoyaltyCard);
				clients.add(c);
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return clients;
	}
}
