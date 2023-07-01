package otherEntities;

import pisanje.ISerializable;

public class ServiceType implements ISerializable {
	String type;
	
	public ServiceType(String type) {
		this.type=type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "ServiceType [type=" + type + "]";
	}

	@Override
	public String toLine() {
		return type;
	}	
}
