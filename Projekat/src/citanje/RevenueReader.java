package citanje;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;

import otherEntities.Revenue;

public class RevenueReader {
	String fileName;
	
	public RevenueReader(String fileName) {
		this.fileName = fileName;
	}
	
	public ArrayList<Revenue> loadRevenues() {
		ArrayList<Revenue> revenues = new ArrayList<Revenue>();
		
		try {
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											new FileInputStream(this.fileName), "utf-8"));
			String line;
			
			while((line = br.readLine()) != null) {
				String[] tokens = line.split("\\|");
				
				String description = tokens[0];
				double moneyMade = Double.parseDouble(tokens[1]);
				LocalDate date = LocalDate.parse(tokens[2]);
				
				Revenue r = new Revenue(description, moneyMade, date);
				revenues.add(r);
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return revenues;
	}
}
