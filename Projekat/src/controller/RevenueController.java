package controller;

import java.util.ArrayList;

import dao.RevenueDAO;
import observer.IObserver;
import otherEntities.Revenue;

public class RevenueController {
	private RevenueDAO _revenues;
	
	public RevenueController() {
		
		_revenues = new RevenueDAO();
	}
	
	public ArrayList<Revenue> getAllRevenues() {
		
		return _revenues.getAll();
	}
	
	public void create(Revenue revenue) {
		
		_revenues.add(revenue);
	}
	
	public void delete(Revenue revenue) {
		
		_revenues.remove(revenue);
	}
	
	public void subscribe(IObserver observer) {
		
		_revenues.subscribe(observer);
	}
}
