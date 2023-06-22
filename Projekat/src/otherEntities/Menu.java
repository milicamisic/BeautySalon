package otherEntities;

import java.util.HashMap;

public class Menu {
	HashMap<ServiceType, Double> itemMenu;
	
	public Menu() {
		itemMenu = new HashMap<ServiceType, Double>();
	}
	
	public void addItem(ServiceType service, double price) {
		itemMenu.put(service, price);
	}
	
	public void removeItem(ServiceType service) {
		itemMenu.remove(service);
	}
}
