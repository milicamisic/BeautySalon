package storage;

import java.io.File;
import java.util.ArrayList;

import citanje.RevenueReader;
import otherEntities.Revenue;
import pisanje.MyWriter;

public class RevenueStorage {
	private final String SEPARATOR = File.separator;
	private final String STORAGE_PATH = "src" + SEPARATOR + "data" + SEPARATOR + "revenues";
	
	public ArrayList<Revenue> load() {
		
		RevenueReader revenueReader = new RevenueReader(STORAGE_PATH);
		return revenueReader.loadRevenues();
	}
	
	public void save(ArrayList<Revenue> revenues) {
		MyWriter writer = new MyWriter(STORAGE_PATH);
		writer.write(revenues);
	}
}
