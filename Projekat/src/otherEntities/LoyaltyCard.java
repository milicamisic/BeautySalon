package otherEntities;

public class LoyaltyCard {
	double moneySpent;

	public LoyaltyCard(double moneySpent) {
		super();
		this.moneySpent = moneySpent;
	}

	public double getMoneySpent() {
		return moneySpent;
	}

	public void setMoneySpent(double moneySpent) {
		this.moneySpent = moneySpent;
	}
	
	public void increase(double amount) {
		this.moneySpent += amount;
	}
	
	public void decrease(double amount) {
		this.moneySpent -= amount;
	}

	@Override
	public String toString() {
		return "LoyaltyCard [moneySpent=" + moneySpent + "]";
	}
}
