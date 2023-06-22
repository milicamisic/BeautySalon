package citanje;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import humanEntities.Beautician;
import humanEntities.Sex;
import otherEntities.ServiceType;

public class BeauticianReader {
	
	String fileName;
	
	public BeauticianReader(String fileName) {
		this.fileName = fileName;
	}
	
	public ArrayList<Beautician> loadBeauticians() {
		ArrayList<Beautician> beauticians = new ArrayList<Beautician>();
		
		try {
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											new FileInputStream(this.fileName), "utf-8"));
			String line;
			
			while((line = br.readLine()) != null) {
				String[] tokens = line.split("\\|");
				
				Sex sex = Sex.valueOf(tokens[2]);
				int proEduLvl = Integer.parseInt(tokens[7]);
				int serviceYears = Integer.parseInt(tokens[8]);
				double bonus = Double.parseDouble(tokens[9]);
				double basePay = Double.parseDouble(tokens[10]);
				ArrayList<ServiceType> trainedFor = new ArrayList<ServiceType>();
				
				if(tokens.length == 12) { // because it will not count the last token if it's empty
					for(String s : tokens[11].split(",")) {
						trainedFor.add(new ServiceType(s));
					}
				}
				
				Beautician b = new Beautician(tokens[0], tokens[1], sex, tokens[3], tokens[4], tokens[5], tokens[6], proEduLvl, serviceYears, bonus, basePay, trainedFor);
				beauticians.add(b);
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return beauticians;
	}
}
