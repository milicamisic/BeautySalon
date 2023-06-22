package citanje;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import humanEntities.Manager;
import humanEntities.Sex;

public class ManagerReader {
	
	String fileName;
	
	public ManagerReader(String fileName) {
		this.fileName = fileName;
	}
	
	public ArrayList<Manager> loadManagers() {
		ArrayList<Manager> managers = new ArrayList<Manager>();
		
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
				
				Manager a = new Manager(tokens[0], tokens[1], sex, tokens[3], tokens[4], tokens[5], tokens[6], proEduLvl, serviceYears, bonus, basePay);
				managers.add(a);
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return managers;
	}
}
