package humanEntities;

import java.util.ArrayList;

import citanje.AppointmentReader;
import otherEntities.Appointment;
import paket1.BeautySalon;

public class Client extends User{

	double moneySpent;
	boolean hasLoyaltyCard; // loyalty card is something that the person has or doesn't have, it contains no information
	
	public Client(String name, String surname, Sex sex, String phoneNumber, String address, String username,
			String password, double moneySpent, boolean hasLoyaltyCard) {
		super(name, surname, sex, phoneNumber, address, username, password);
		
		this.moneySpent = moneySpent;
		this.hasLoyaltyCard = hasLoyaltyCard;
	}

	public double getMoneySpent() {
		return moneySpent;
	}

	public void setMoneySpent(double moneySpent) {
		this.moneySpent = moneySpent;
		BeautySalon beautySalon = BeautySalon.getBeautySalon();
		if(moneySpent >= beautySalon.getLoyaltyCardPrecondition()) {
			this.hasLoyaltyCard = true;
		} else {
			this.hasLoyaltyCard = false;
		}
	}

	public boolean hasLoyaltyCard() {
		return hasLoyaltyCard;
	}

	public void setLoyaltyCard(boolean hasLoyaltyCard) {
		this.hasLoyaltyCard = hasLoyaltyCard;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", surname=" + surname + ", sex=" + sex
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", username=" + username + ", password="
				+ password + ", Money Spent=" + moneySpent + ", Loyalty Card=" + hasLoyaltyCard + "]";
	}

	@Override
	public String toLine() {
		return name + "|" + surname + "|" + sex.toString() + "|" + phoneNumber + "|" + address + 
			   "|" + username + "|" + password + "|" + moneySpent + "|" + hasLoyaltyCard;
	}
	
	public Object toCell(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return name;
		case 1:
			return surname;
		case 2:
			return sex.toString();
		case 3:
			return phoneNumber;
		case 4:
			return address;
		case 5:
			return username;
		case 6:
			return moneySpent;
		case 7:
			return hasLoyaltyCard;
		default:
			return "";
		}
	}
	
	public void viewMyAppointments() {
		AppointmentReader ar = new AppointmentReader("src/data/appointments2");
		ArrayList<Appointment> appointments = ar.loadAppointments();
		
		for(Appointment a : appointments) {
			if(a.getClient().username.equals(this.username))
				System.out.println(a);
		}
	}
}
