package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import citanje.BeautySalonReader;

public class BeautySalonStorage {
	private final String SEPARATOR = File.separator;
	private final String STORAGE_PATH = "src" + SEPARATOR + "data" + SEPARATOR + "beautySalonInfo";
	
	public ArrayList<Object> load() {
		
		BeautySalonReader beautySalonReader = new BeautySalonReader(STORAGE_PATH);
		return beautySalonReader.load();
	}
	
	public void save(ArrayList<Object> info) {
		String infoString = "";
		
		double balance = (Double) info.get(0);
		double loyaltyCardPrecondition = (Double) info.get(1);
		int completedAppointmentsForBonus = (Integer) info.get(2);
		LocalTime workingHoursStart = (LocalTime) info.get(3);
		LocalTime workingHoursEnd = (LocalTime) info.get(4);
		
		infoString += Double.toString(balance) + "|" +
					  Double.toString(loyaltyCardPrecondition) + "|" + 
					  Integer.toString(completedAppointmentsForBonus) + "|" + 
					  workingHoursStart.format(DateTimeFormatter.ofPattern("H:m")) + "|" +
					  workingHoursEnd.format(DateTimeFormatter.ofPattern("H:m"));
		
		try {
			BufferedWriter bw = new BufferedWriter(
									new OutputStreamWriter(
											new FileOutputStream(STORAGE_PATH), "utf-8"));
			
			bw.write(infoString);
			
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
