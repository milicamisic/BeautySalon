package otherEntities;

import java.time.LocalDate;

import pisanje.ISerializable;

public class Expense implements ISerializable{
	String description;
	double moneyPaid;
	LocalDate date;
	
	public Expense(String description, double moneyPaid, LocalDate date) {
		super();
		this.description = description;
		this.moneyPaid = moneyPaid;
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getMoneyPaid() {
		return moneyPaid;
	}

	public void setMoneyPaid(double moneyPaid) {
		this.moneyPaid = moneyPaid;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Expense [description=" + description + ", moneyPaid=" + moneyPaid + ", date=" + date + "]";
	}
	
	public String toLine() {
		return description + "|" + moneyPaid + "|" + date;
	}
	
	public Object toCell(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return description;
		case 1:
			return moneyPaid;
		case 2:
			return date.toString();
		default:
			return "";
		}
	}
}
