package controller;

import java.util.ArrayList;

import dao.ExpenseDAO;
import observer.IObserver;
import otherEntities.Expense;

public class ExpenseController {
	private ExpenseDAO _expenses;
	
	public ExpenseController() {
		
		_expenses = new ExpenseDAO();
	}
	
	public ArrayList<Expense> getAllExpenses() {
		
		return _expenses.getAll();
	}
	
	public void create(Expense expense) {
		
		_expenses.add(expense);
	}
	
	public void delete(Expense expense) {
		
		_expenses.remove(expense);
	}
	
	public void subscribe(IObserver observer) {
		
		_expenses.subscribe(observer);
	}
}
