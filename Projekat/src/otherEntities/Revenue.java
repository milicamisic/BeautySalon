package otherEntities;

import java.time.LocalDate;

import pisanje.ISerializable;

public class Revenue implements ISerializable{
	String description;
	double moneyMade;
	LocalDate date;
	
	public Revenue(String description, double moneyMade, LocalDate date) {
		super();
		this.description = description;
		this.moneyMade = moneyMade;
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getMoneyMade() {
		return moneyMade;
	}
	
	public void setMoneyMade(double moneyMade) {
		this.moneyMade = moneyMade;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Revenue [description=" + description + ", moneyMade=" + moneyMade + ", date=" + date + "]";
	}

	public String toLine() {
		return description + "|" + moneyMade + "|" + date;
	}
	
}
