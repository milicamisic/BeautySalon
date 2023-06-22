package humanEntities;

import java.util.ArrayList;

import citanje.AppointmentReader;
import citanje.ClientReader;
import otherEntities.Appointment;

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

	public static Client findClientByUsername(String username) {
		ClientReader cr = new ClientReader("src/data/clients3");
		ArrayList<Client> clients = cr.loadClients();
		
		for(int i = 0; i < clients.size(); i++) {
			if(clients.get(i).username.equals(username)) {
				return clients.get(i);
			}
		}
		
		return null;
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
