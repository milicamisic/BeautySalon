package storage;

import java.io.File;
import java.util.ArrayList;

import citanje.ExpenseReader;
import otherEntities.Expense;
import pisanje.MyWriter;

public class ExpenseStorage {
	private final String SEPARATOR = File.separator;
	private final String STORAGE_PATH = "src" + SEPARATOR + "data" + SEPARATOR + "expenses";
	
	public ArrayList<Expense> load() {
		
		ExpenseReader expenseReader = new ExpenseReader(STORAGE_PATH);
		return expenseReader.loadExpenses();
	}
	
	public void save(ArrayList<Expense> expenses) {
		MyWriter writer = new MyWriter(STORAGE_PATH);
		writer.write(expenses);
	}
}
