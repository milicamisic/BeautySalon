package citanje;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class BeautySalonReader {
	
	String fileName;
	
	public BeautySalonReader(String fileName) {
		this.fileName = fileName;
	}
	
	public ArrayList<Object> load() {
		ArrayList<Object> info = new ArrayList<Object>();
		try {
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											new FileInputStream(this.fileName), "utf-8"));
			String line = br.readLine();
			String[] tokens = line.split("\\|");
			info.add(Double.parseDouble(tokens[0]));
			info.add(Double.parseDouble(tokens[1]));
			info.add(Integer.parseInt(tokens[2]));
			
			br.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return info;
	}
}

