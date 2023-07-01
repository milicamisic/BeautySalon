package otherEntities;

import pisanje.ISerializable;

public class Service implements ISerializable{
	
	private String name;
	private ServiceType type;
	private int durationInMinutes;
	private double price;
	
	public Service(String name, ServiceType type, int durationInMinutes, double price) {
		super();

		this.name = name;
		this.type = type;
		this.durationInMinutes = durationInMinutes;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ServiceType getType() {
		return type;
	}

	public void setType(ServiceType type) {
		this.type = type;
	}
	
	public int getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean equals(Service other)
	{
		if(this.getName().equals(other.getName()))
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "Service [name=" + name + ", type=" + type.getType() + ", durationInMinutes=" + Integer.toString(durationInMinutes) + ", price="
				+ Double.toString(price) + "]";
	}

	@Override
	public String toLine() {
		return name + "|" + type.getType() + "|" + Integer.toString(durationInMinutes) + "|" + Double.toString(price);
	}
}
