package storage;

import java.io.File;
import java.util.ArrayList;

import citanje.BeauticianReader;
import humanEntities.Beautician;
import pisanje.MyWriter;

public class BeauticianStorage {
	private final String SEPARATOR = File.separator;
	private final String STORAGE_PATH = "src" + SEPARATOR + "data" + SEPARATOR + "beauticians3";
	
	public ArrayList<Beautician> load() {
		
		BeauticianReader beauticianReader = new BeauticianReader(STORAGE_PATH);
		return beauticianReader.loadBeauticians();
	}
	
	public void save(ArrayList<Beautician> beauticians) {
		MyWriter writer = new MyWriter(STORAGE_PATH);
		writer.write(beauticians);
	}
}
