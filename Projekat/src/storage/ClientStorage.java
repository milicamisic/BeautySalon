package storage;

import java.io.File;
import java.util.ArrayList;

import citanje.ClientReader;
import humanEntities.Client;
import pisanje.MyWriter;

public class ClientStorage {
	private final String SEPARATOR = File.separator;
	private final String STORAGE_PATH = "src" + SEPARATOR + "data" + SEPARATOR + "clients3";
	
	public ArrayList<Client> load() {
		
		ClientReader clientReader = new ClientReader(STORAGE_PATH);
		return clientReader.loadClients();
	}
	
	public void save(ArrayList<Client> clients) {
		MyWriter writer = new MyWriter(STORAGE_PATH);
		writer.write(clients);
	}
}
