package controller;

import java.util.ArrayList;

import dao.BeauticianDAO;
import humanEntities.Beautician;
import observer.IObserver;

public class BeauticianController {
	
	private BeauticianDAO _beauticians;
	
	public BeauticianController() {
		
		_beauticians = new BeauticianDAO();
	}
	
	public ArrayList<Beautician> getAllBeauticians() {
		
		return _beauticians.getAll();
	}
	
	public Beautician getBeauticianByUsername(String beauticianUsername) {
		
		return _beauticians.getByUsername(beauticianUsername);
	}
	
	public void create(Beautician beautician) {
		
		_beauticians.add(beautician);
	}
	
	public void delete(Beautician beautician) {
		
		_beauticians.remove(beautician);
	}
	
	public void subscribe(IObserver observer) {
		
		_beauticians.subscribe(observer);
	}
	
}
