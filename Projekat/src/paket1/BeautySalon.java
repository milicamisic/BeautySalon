package paket1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

import humanEntities.Beautician;
import humanEntities.Client;
import humanEntities.Manager;
import humanEntities.Receptionist;
import humanEntities.User;
import otherEntities.Appointment;
import otherEntities.Expense;
import otherEntities.Revenue;
import otherEntities.Service;
import otherEntities.ServiceType;
import otherEntities.Timeslot;
import service.AppointmentService;
import service.BeauticianService;
import service.ClientService;
import storage.AppointmentStorage;
import storage.BeauticianStorage;
import storage.BeautySalonStorage;
import storage.ClientStorage;
import storage.ExpenseStorage;
import storage.ManagerStorage;
import storage.ReceptionistStorage;
import storage.RevenueStorage;
import storage.ServiceStorage;
import storage.ServiceTypeStorage;
import storage.UserStorage;

public class BeautySalon { //Singleton
	
	private static BeautySalon beautySalon = null;
	
	public static BeautySalon getBeautySalon() {
		if(beautySalon == null) {
			beautySalon = new BeautySalon("Moj salon", LocalTime.of(8, 0), LocalTime.of(20, 0));
			beautySalon.initialize();
		}
		return beautySalon;
	}
	
	private String name;
	private LocalTime workingHoursStart;
	private LocalTime workingHoursEnd;
	private double loyaltyCardPrecondition;
	private double balance;
	private int completedAppointmentsForBonus;
	private User currentUser;
	
	private ArrayList<User> users;
	private ArrayList<Manager> managers;
	private ArrayList<Client> clients;
	private ArrayList<Beautician> beauticians;
	private ArrayList<Receptionist> receptionists;
	private ArrayList<ServiceType> serviceTypes;
	private ArrayList<Service> services;
	private ArrayList<Appointment> appointments;
	private ArrayList<Revenue> revenues;
	private ArrayList<Expense> expenses;
	
	private BeautySalon(String name, LocalTime workingHoursStart, LocalTime workingHoursEnd) {
		this.name = name;
		this.workingHoursStart = workingHoursStart;
		this.workingHoursEnd = workingHoursEnd;
		
		users = new ArrayList<User>();
		managers = new ArrayList<Manager>();
		clients = new ArrayList<Client>();
		beauticians = new ArrayList<Beautician>();
		receptionists = new ArrayList<Receptionist>();
		serviceTypes = new ArrayList<ServiceType>();
		services = new ArrayList<Service>();
		appointments = new ArrayList<Appointment>();
		revenues = new ArrayList<Revenue>();
		expenses = new ArrayList<Expense>();
	}
	
	private void loadInfo() {
		BeautySalonStorage storage = new BeautySalonStorage();
		ArrayList<Object> info = storage.load();
		this.balance = (Double) info.get(0);
		this.loyaltyCardPrecondition = (Double) info.get(1);
		this.completedAppointmentsForBonus = (Integer) info.get(2);
		LocalDate today = LocalDate.now();
		if(today.getDayOfMonth() == 1)
			payWorkers();
	}

	private void initialize() {
		UserStorage userStorage = new UserStorage();
		ManagerStorage managerStorage = new ManagerStorage();
		ClientStorage clientStorage = new ClientStorage();
		BeauticianStorage beauticianStorage = new BeauticianStorage();
		ReceptionistStorage receptionistStorage = new ReceptionistStorage();
		ServiceTypeStorage serviceTypeStorage = new ServiceTypeStorage();
		ServiceStorage serviceStorage = new ServiceStorage();
		AppointmentStorage appointmentStorage = new AppointmentStorage();
		RevenueStorage revenueStorage = new RevenueStorage();
		ExpenseStorage expenseStorage = new ExpenseStorage();
		
		this.users = userStorage.load();
		this.managers = managerStorage.load();
		this.clients = clientStorage.load();
		this.beauticians = beauticianStorage.load();
		this.receptionists = receptionistStorage.load();
		this.serviceTypes = serviceTypeStorage.load();
		this.services = serviceStorage.load();
		this.appointments = appointmentStorage.load();
		this.revenues = revenueStorage.load();
		this.expenses = expenseStorage.load();
		loadInfo();
	}
	
	public void payWorkers() {
		Expense e;
		for(Manager m : managers) {
			e = new Expense("Pay:" + m.getUsername(), m.getPay(),LocalDate.now());
			addExpense(e);
		}
		for(Beautician b : beauticians) {
			e = new Expense("Pay:" + b.getUsername(), b.getPay(),LocalDate.now());
			addExpense(e);
		}
		for(Receptionist r : receptionists) {
			e = new Expense("Pay:" + r.getUsername(), r.getPay(),LocalDate.now());
			addExpense(e);
		}
	}
	
	public boolean isUsernameAvailable(String username) {
		for(User u : this.users) {
			if(u.getUsername().equals(username)) {
				return false;
			}
		}
		return true;
	}
	
	public void removeAllServicesForServiceType(String type) {
		Iterator<Service> si = this.services.iterator(); 
		
		while (si.hasNext()) { 
			Service service = si.next(); 
			
			if (service.getType().getType().equals(type)) { 
				si.remove(); 
			} 
		}
	}
	
	public void modifyAllServicesForServiceType(String type, String newType) {
		for(int i = 0; i < this.services.size(); i++) {
			if(this.services.get(i).getType().getType().equals(type)) {
				this.services.get(i).setType(new ServiceType(newType));
			}
		}
	}
	
	public int getNextAppointmentId() {
		ArrayList<Appointment> appointments = this.appointments;
		int size = appointments.size();
		
		if(appointments.size() == 0) {
			return 0;
		}
		return appointments.get(size - 1).getId() + 1;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getLoyaltyCardPrecondition() {
		return loyaltyCardPrecondition;
	}

	public void setLoyaltyCardPrecondition(int loyaltyCardPrecondition) {
		this.loyaltyCardPrecondition = loyaltyCardPrecondition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalTime getWorkingHoursStart() {
		return workingHoursStart;
	}

	public void setWorkingHoursStart(LocalTime workingHoursStart) {
		this.workingHoursStart = workingHoursStart;
	}

	public LocalTime getWorkingHoursEnd() {
		return workingHoursEnd;
	}

	public void setWorkingHoursEnd(LocalTime workingHoursEnd) {
		this.workingHoursEnd = workingHoursEnd;
	}
	
	public int getCompletedAppointmentsForBonus() {
		return completedAppointmentsForBonus;
	}

	public void setCompletedAppointmentsForBonus(int completedAppointmentsForBonus) {
		this.completedAppointmentsForBonus = completedAppointmentsForBonus;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
	public boolean removeUser(String username) {
		for(int i = 0; i < this.users.size(); i++) {
			if(this.users.get(i).getUsername().equals(username)) {
				this.users.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean modifyUser(User user) {
		for(int i = 0; i < this.users.size(); i++) {
			if(this.users.get(i).getUsername().equals(user.getUsername())) {
				this.users.set(i, user);
				return true;
			}
		}
		return false;
	}

	public ArrayList<Manager> getManagers() {
		return managers;
	}

	public void setManagers(ArrayList<Manager> managers) {
		this.managers = managers;
	}
	
	public boolean addManager(Manager manager) {
		if(isUsernameAvailable(manager.getUsername())) {
			this.managers.add(manager);
			addUser(manager);
			return true;
		}
		return false;
	}
	
	public boolean removeManager(String username) {
		for(int i = 0; i < this.managers.size(); i++) {
			if(this.managers.get(i).getUsername().equals(username)) {
				this.managers.remove(i);
				removeUser(username);
				return true;
			}
		}
		return false;
	}
	
	public void modifyManager(Manager manager) {
		for(int i = 0; i < this.managers.size(); i++) {
			if(this.managers.get(i).getUsername().equals(manager.getUsername())) {
				this.managers.set(i, manager);
				modifyUser(manager);
			}
		}
	}

	public ArrayList<Client> getClients() {
		return clients;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
	
	public boolean addClient(Client client) {
		if(isUsernameAvailable(client.getUsername())) {
			this.clients.add(client);
			addUser(client);
			return true;
		}
		return false;
	}
	
	public boolean removeClient(String username) {
		for(int i = 0; i < this.clients.size(); i++) {
			if(this.clients.get(i).getUsername().equals(username)) {
				this.clients.remove(i);
				removeUser(username);
				return true;
			}
		}
		return false;
	}
	
	public void modifyClient(Client client) {
		for(int i = 0; i < this.clients.size(); i++) {
			if(this.clients.get(i).getUsername().equals(client.getUsername())) {
				this.clients.set(i, client);
				modifyUser(client);
			}
		}
	}

	public ArrayList<Beautician> getBeauticians() {
		return beauticians;
	}

	public void setBeauticians(ArrayList<Beautician> beauticians) {
		this.beauticians = beauticians;
	}
	
	public boolean addBeautician(Beautician beautician) {
		if(isUsernameAvailable(beautician.getUsername())) {
			this.beauticians.add(beautician);
			addUser(beautician);
			return true;
		}
		return false;
	}
	
	public boolean removeBeautician(String username) {
		for(int i = 0; i < this.beauticians.size(); i++) {
			if(this.beauticians.get(i).getUsername().equals(username)) {
				this.beauticians.remove(i);
				removeUser(username);
				return true;
			}
		}
		return false;
	}
	
	public boolean modifyBeautician(Beautician beautician) {
		for(int i = 0; i < this.beauticians.size(); i++) {
			if(this.beauticians.get(i).getUsername().equals(beautician.getUsername())) {
				this.beauticians.set(i, beautician);
				modifyUser(beautician);
				return true;
			}
		}
		return false;
	}

	public ArrayList<Receptionist> getReceptionists() {
		return receptionists;
	}

	public void setReceptionists(ArrayList<Receptionist> receptionists) {
		this.receptionists = receptionists;
	}
	
	public boolean addReceptionist(Receptionist receptionist) {
		if(isUsernameAvailable(receptionist.getUsername())) {
			this.receptionists.add(receptionist);
			addUser(receptionist);
			return true;
		}
		return false;
	}
	
	public boolean removeReceptionist(String username) {
		for(int i = 0; i < this.receptionists.size(); i++) {
			if(this.receptionists.get(i).getUsername().equals(username)) {
				this.receptionists.remove(i);
				removeUser(username);
				return true;
			}
		}
		return false;
	}
	
	public boolean modifyReceptionist(Receptionist receptionist) {
		for(int i = 0; i < this.receptionists.size(); i++) {
			if(this.receptionists.get(i).getUsername().equals(receptionist.getUsername())) {
				this.receptionists.set(i, receptionist);
				modifyUser(receptionist);
				return true;
			}
		}
		return false;
	}

	public ArrayList<ServiceType> getServiceTypes() {
		return serviceTypes;
	}

	public void setServiceTypes(ArrayList<ServiceType> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}
	
	public boolean addServiceType(ServiceType serviceType) {
		for(ServiceType st: this.serviceTypes) {
			if(st.getType().equals(serviceType.getType()))
				return false;
		}
		this.serviceTypes.add(serviceType);
		return true;
	}
	
	public boolean removeServiceType(String type) {
		for(int i = 0; i < this.serviceTypes.size(); i++) {
			if(this.serviceTypes.get(i).getType().equals(type)) {
				this.serviceTypes.remove(i);
				removeAllServicesForServiceType(type);
				return true;
			}
		}
		return false;
	}
	
	public boolean modifyServiceType(String type, String newType) {
		for(int i = 0; i < this.serviceTypes.size(); i++) {
			if(this.serviceTypes.get(i).getType().equals(type)) {
				this.serviceTypes.set(i, new ServiceType(newType));
				modifyAllServicesForServiceType(type, newType);
				return true;
			}
		}
		return false;
	}

	public ArrayList<Service> getServices() {
		return services;
	}

	public void setServices(ArrayList<Service> services) {
		this.services = services;
	}
	
	public boolean addService(Service service) {
		for(Service s: this.services) {
			if(s.getName().equals(service.getName()))
				return false;
		}
		this.services.add(service);
		return true;
	}
	
	public boolean removeService(String name) {
		for(int i = 0; i < this.services.size(); i++) {
			if(this.services.get(i).getName().equals(name)) {
				this.services.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean modifyService(Service service) {
		for(int i = 0; i < this.services.size(); i++) {
			if(this.services.get(i).getName().equals(service.getName())) {
				this.services.set(i, service);
				return true;
			}
		}
		return false;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	public int addAppointment(Appointment appointment) {
		
		ClientService clientService = new ClientService();
		BeauticianService beauticianService = new BeauticianService();
		Client client = appointment.getClient();
		Beautician beautician = appointment.getBeautician();
		Timeslot timeslot = appointment.getTimeslot();
		
		if(!clientService.isAvailable(client, timeslot))
		{
			return -3;
		}
		else if(beautician == null)
		{
			beautician = beauticianService.getAvailableBeautician(appointment);
			if(beautician == null)
				return -2;
		} 
		else 
		{
			if(!beauticianService.isAvailable(beautician, timeslot))
				return -1;
		}
		appointment.setBeautician(beautician);
		
		if(appointment.getId() == -1) {
			appointment.setId(getNextAppointmentId());
		}
		
		if(client.hasLoyaltyCard())
			appointment.setPrice(appointment.getPrice()*0.9);
		
		this.appointments.add(appointment);
		
		Revenue revenue = new Revenue("Appointment " + appointment.getId() + " SCHEDULED", appointment.getPrice(), LocalDate.now());
		addRevenue(revenue);
		
		client.setMoneySpent(client.getMoneySpent()+appointment.getPrice());
		
		return 0;
	}
	
	// ovo se koristi samo za modifikaciju, nikad stvarno ne brisemo appointmente, samo menjamo statuse
	public boolean removeAppointment(Appointment appointment) {
		for(int i = 0; i < this.appointments.size(); i++) {
			if(this.appointments.get(i).getId() == appointment.getId()) {
				this.appointments.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public int modifyAppointment(Appointment appointment) {
		AppointmentService appointmentService = new AppointmentService();
		// ovaj modifikujemo
		Appointment oldAppointment = appointmentService.getAppointmentById(appointment.getId());
		// obrisemo ga onakvog kakav je bio
		removeAppointment(oldAppointment);
		
		Expense expense = new Expense("Appointment " + appointment.getId() + " REMOVED_FOR_MODIFICATION", oldAppointment.getPrice(), LocalDate.now());
		addExpense(expense);
		
		Client client = oldAppointment.getClient();
		client.setMoneySpent(client.getMoneySpent() - oldAppointment.getPrice());

		if(appointment.getClient().hasLoyaltyCard()) {
			appointment.setPrice(appointment.getService().getPrice()*0.9);
		} else {
			appointment.setPrice(appointment.getService().getPrice());
		}
		// upisemo ga izmenjenog
		int added = addAppointment(appointment);
		if(added != 0)
			addAppointment(oldAppointment); // ako ne moze takav novi da se doda samo upisemo stari
		
		return added;
	}

	public ArrayList<Revenue> getRevenues() {
		return revenues;
	}

	public void setRevenues(ArrayList<Revenue> revenues) {
		this.revenues = revenues;
	}
	
	public void addRevenue(Revenue revenue) {
		this.revenues.add(revenue);
		this.balance += revenue.getMoneyMade();
	}

	public ArrayList<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(ArrayList<Expense> expenses) {
		this.expenses = expenses;
	}
	
	public void addExpense(Expense expense) {
		this.expenses.add(expense);
		this.balance -= expense.getMoneyPaid();
	}
	
	public User getCurrentUser() {
		return this.currentUser;
	}
	
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}

	public void saveData() {
		UserStorage userStorage = new UserStorage();
		ManagerStorage managerStorage = new ManagerStorage();
		ClientStorage clientStorage = new ClientStorage();
		BeauticianStorage beauticianStorage = new BeauticianStorage();
		ReceptionistStorage receptionistStorage = new ReceptionistStorage();
		ServiceTypeStorage serviceTypeStorage = new ServiceTypeStorage();
		ServiceStorage serviceStorage = new ServiceStorage();
		AppointmentStorage appointmentStorage = new AppointmentStorage();
		RevenueStorage revenueStorage = new RevenueStorage();
		ExpenseStorage expenseStorage = new ExpenseStorage();
		
		userStorage.save(users);
		managerStorage.save(managers);
		clientStorage.save(clients);
		beauticianStorage.save(beauticians);
		receptionistStorage.save(receptionists);
		serviceTypeStorage.save(serviceTypes);
		serviceStorage.save(services);
		appointmentStorage.save(appointments);
		revenueStorage.save(revenues);
		expenseStorage.save(expenses);
		
	}
}
