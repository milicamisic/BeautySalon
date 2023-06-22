package humanEntities;

public class Manager extends Worker{
	
	public Manager(String name, String surname, Sex sex, String phoneNumber, String address, String username,
			String password, int proEduLvl, int serviceYears, double bonus, double basePay) {
		super(name, surname, sex, phoneNumber, address, username, password, proEduLvl, serviceYears, bonus, basePay);
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
			return password;
		case 7:
			return proEduLvl;
		case 8:
			return serviceYears;
		case 9:
			return basePay;
		case 10:
			return bonus;
		default:
			return "";
		}
	}
}