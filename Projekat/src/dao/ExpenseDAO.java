package dao;

import java.util.ArrayList;

import observer.IObserver;
import otherEntities.Expense;
import storage.ExpenseStorage;

public class ExpenseDAO {
	private ArrayList<IObserver> _observers;
	private ExpenseStorage _storage;
	private ArrayList<Expense> _expenses;
	
	public ExpenseDAO() {
		
		_observers = new ArrayList<IObserver>();
		_storage = new ExpenseStorage();
		_expenses = _storage.load();
	}
	
	public void add(Expense expense) {
		
		_expenses.add(expense);
		_storage.save(_expenses);
		notifyObservers();
	}
	
	public void remove(Expense expense) {
		
		_expenses.remove(expense);
		_storage.save(_expenses);
		notifyObservers();
	}
	
	public ArrayList<Expense> getAll() {
		
		return _expenses;
	}
	
	public void subscribe(IObserver observer) {
		
		_observers.add(observer);
	}
	
	public void unsubscribe(IObserver observer) {
		
		_observers.remove(observer);
	}
	
	public void notifyObservers() {
		
		for(IObserver observer : _observers) {
			
			observer.update();
		}
	}
}
