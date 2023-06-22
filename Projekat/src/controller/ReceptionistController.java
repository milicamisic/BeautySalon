package controller;

import java.util.ArrayList;

import dao.ReceptionistDAO;
import humanEntities.Receptionist;
import observer.IObserver;

public class ReceptionistController {
	
	private ReceptionistDAO _receptionists;
	
	public ReceptionistController() {
		
		_receptionists = new ReceptionistDAO();
	}
	
	public ArrayList<Receptionist> getAllReceptionists() {
		
		return _receptionists.getAll();
	}
	
	public Receptionist getReceptionistByUsername(String receptionistUsername) {
		
		return _receptionists.getByUsername(receptionistUsername);
	}
	
	public void create(Receptionist receptionist) {
		
		_receptionists.add(receptionist);
	}
	
	public void delete(Receptionist receptionist) {
		
		_receptionists.remove(receptionist);
	}
	
	public void subscribe(IObserver observer) {
		
		_receptionists.subscribe(observer);
	}
}
