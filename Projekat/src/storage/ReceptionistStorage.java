package storage;

import java.io.File;
import java.util.ArrayList;

import citanje.ReceptionistReader;
import humanEntities.Receptionist;
import pisanje.MyWriter;

public class ReceptionistStorage {
	private final String SEPARATOR = File.separator;
	private final String STORAGE_PATH = "src" + SEPARATOR + "data" + SEPARATOR + "receptionists";
	
	public ArrayList<Receptionist> load() {
		
		ReceptionistReader receptionistReader = new ReceptionistReader(STORAGE_PATH);
		return receptionistReader.loadReceptionists();
	}
	
	public void save(ArrayList<Receptionist> receptionists) {
		MyWriter writer = new MyWriter(STORAGE_PATH);
		writer.write(receptionists);
	}
}
