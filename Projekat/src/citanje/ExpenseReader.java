package citanje;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;

import otherEntities.Expense;

public class ExpenseReader {
	String fileName;
	
	public ExpenseReader(String fileName) {
		this.fileName = fileName;
	}
	
	public ArrayList<Expense> loadExpenses() {
		ArrayList<Expense> expenses = new ArrayList<Expense>();
		
		try {
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											new FileInputStream(this.fileName), "utf-8"));
			String line;
			
			while((line = br.readLine()) != null) {
				String[] tokens = line.split("\\|");
				
				String description = tokens[0];
				double moneyPaid = Double.parseDouble(tokens[1]);
				LocalDate date = LocalDate.parse(tokens[2]);
				
				Expense e = new Expense(description, moneyPaid, date);
				expenses.add(e);
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return expenses;
	}
}
