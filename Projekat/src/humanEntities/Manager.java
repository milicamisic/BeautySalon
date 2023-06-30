package humanEntities;

public class Manager extends Worker{
	
	public Manager(String name, String surname, Sex sex, String phoneNumber, String address, String username,
			String password, int proEduLvl, int serviceYears, double bonus, double basePay) {
		super(name, surname, sex, phoneNumber, address, username, password, proEduLvl, serviceYears, bonus, basePay);
	}
}