package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import citanje.BeautySalonReader;

public class BeautySalonStorage {
	private final String SEPARATOR = File.separator;
	private final String STORAGE_PATH = "src" + SEPARATOR + "data" + SEPARATOR + "beautySalonInfo";
	
	public ArrayList<Object> load() {
		
		BeautySalonReader beautySalonReader = new BeautySalonReader(STORAGE_PATH);
		return beautySalonReader.load();
	}
	
	public void save(ArrayList<Object> beautySalonInfo) {
		String infoString = "";
		try {
			BufferedWriter bw = new BufferedWriter(
									new OutputStreamWriter(
											new FileOutputStream(STORAGE_PATH), "utf-8"));
			
			for(Object token : beautySalonInfo) {
				infoString += token.toString() + "|";
			}
			infoString = infoString.substring(0, infoString.length()-1);
			
			bw.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
