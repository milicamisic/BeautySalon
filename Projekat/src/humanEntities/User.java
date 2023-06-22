package humanEntities;

import pisanje.ISerializable;

public class User implements ISerializable{
	
	String name;
	String surname;
	Sex sex;
	String phoneNumber;
	String address;
	String username;
	String password;
	
	public User(String name, String surname, Sex sex, String phoneNumber, String address, String username, String password) {
		
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + ", sex=" + sex.toString() + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", username=" + username + ", password=" + password + "]";
	}
	
	public String toLine() {
		return name + "|" + surname + "|" + sex.toString() + "|" + phoneNumber + "|" + address + "|" + username + "|" + password;
	}
	
    public boolean equals(User other) {
		if(this.username.equals(other.getUsername()))
			return true;
		return false;
	}
	
	
	
	
}
