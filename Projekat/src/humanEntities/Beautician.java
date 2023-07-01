package humanEntities;

import java.util.ArrayList;

import citanje.BeauticianReader;
import otherEntities.ServiceType;

public class Beautician extends Worker{

	private ArrayList<ServiceType> trainedFor;
	
	public Beautician(String name, String surname, Sex sex, String phoneNumber, String address, String username,
			String password, int proEduLvl, int serviceYears, double bonus, double basePay, ArrayList<ServiceType> trainedFor) {
		super(name, surname, sex, phoneNumber, address, username, password, proEduLvl, serviceYears, bonus, basePay);
		
		this.trainedFor = trainedFor;
	}
	
	public ArrayList<ServiceType> getSkills() {
		return trainedFor;
	}

	public void setSkills(ArrayList<ServiceType> trainedFor) {
		this.trainedFor = trainedFor;
	}
	
	public void addSkill(ServiceType st) {
		this.trainedFor.add(st);
	}

	public static Beautician findBeauticianByUsername(String username) {
		BeauticianReader br = new BeauticianReader("src/data/beauticians3");
		ArrayList<Beautician> beauticians = br.loadBeauticians();
		
		for(int i = 0; i < beauticians.size(); i++) {
			if(beauticians.get(i).username.equals(username)) {
				return beauticians.get(i);
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		String str =  "Beautician [name=" + name + ", surname=" + surname + ", sex=" + sex
						+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", username=" + username + ", password="
						+ password + ", proEduLvl=" + proEduLvl + ", serviceYears=" + serviceYears + ", bonus=" + bonus + ", basePay="
								+ basePay + ", pay=" + pay + ", trainedFor= [";
		
		if (!trainedFor.isEmpty()) {
			str += trainedFor.get(0);
			
			for(int i = 1; i < trainedFor.size(); i++)
				str += ", " + trainedFor.get(i).toString();
		}
		str += "]]";
		return str;
	}
	
	@Override
	public String toLine() {
		String line =  name + "|" + surname + "|" + sex.toString() + "|" + phoneNumber + "|" + address + 
					   "|" + username + "|" + password + "|" + Integer.toString(proEduLvl) + "|" +
					   Integer.toString(serviceYears) + "|" + Double.toString(bonus) + "|" +
					   Double.toString(basePay) + "|";
		
		if (!trainedFor.isEmpty()) {
			line += trainedFor.get(0);
			
			for(int i = 1; i < trainedFor.size(); i++)
				line += "," + trainedFor.get(i).getType();
		}
		
		return line;
	}

	@Override
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
			return proEduLvl;
		case 7:
			return serviceYears;
		case 8:
			return basePay;
		case 9:
			return bonus;
		default:
			return "";
		}
	}

}






















