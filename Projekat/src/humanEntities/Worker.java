package humanEntities;

public class Worker extends User{
	
	int proEduLvl;
	int serviceYears;
	double bonus;
	double basePay;
	double pay;

	public Worker(String name, String surname, Sex sex, String phoneNumber, String address, String username,
			String password, int proEduLvl, int serviceYears, double bonus, double basePay) {
		super(name, surname, sex, phoneNumber, address, username, password);
		
		this.proEduLvl = proEduLvl;
		this.serviceYears = serviceYears;
		this.bonus = bonus;
		this.basePay = basePay;
		this.pay = (proEduLvl+serviceYears) * 0.2 * basePay + bonus;
	}
	

	public int getProEduLvl() {
		return proEduLvl;
	}


	public void setProEduLvl(int proEduLvl) {
		this.proEduLvl = proEduLvl;
	}


	public int getServiceYears() {
		return serviceYears;
	}


	public void setServiceYears(int serviceYears) {
		this.serviceYears = serviceYears;
	}


	public double getBonus() {
		return bonus;
	}


	public void setBonus(double bonus) {
		this.bonus = bonus;
	}


	public double getBasePay() {
		return basePay;
	}


	public void setBasePay(double basePay) {
		this.basePay = basePay;
	}


	public double getPay() {
		return pay;
	}


	public void setPay(double pay) {
		this.pay = pay;
	}


	@Override
	public String toString() {
		return "Worker [name=" + name + ", surname=" + surname + ", sex=" + sex
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", username=" + username + ", password="
				+ password + ", proEduLvl=" + proEduLvl + ", serviceYears=" + serviceYears + ", bonus=" + bonus + ", basePay="
						+ basePay + ", pay=" + pay + "]";
	}



	@Override
	public String toLine() {
		return name + "|" + surname + "|" + sex.toString() + "|" + phoneNumber + "|" + address + 
				"|" + username + "|" + password + "|" + Integer.toString(proEduLvl) + "|" +
				Integer.toString(serviceYears) + "|" + Double.toString(bonus) + "|" +
				Double.toString(basePay);
	}
}
