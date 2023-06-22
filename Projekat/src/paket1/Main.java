package paket1;

import humanEntities.Manager;
import humanEntities.Sex;
import pisanje.MyWriter;

public class Main {

	public static void main(String[] args) {
		// na pocetku sve ucitas u beutysalon i onda to prosledjujes svima u gui, tu brises menjas itd.
		BeautySalon beautySalon = BeautySalon.getBeautySalon();
		
		Manager manager = new Manager("Nikola", "Nikolić", Sex.MALE, "1112223330", "Vranjevacka 12", "milica",
				  "123", 4, 3, 0, 20000);
		
		MyWriter wr = new MyWriter("src/data/managers3");
		wr.write(manager);
		
		/* TEST ZA KT2
		// Podesiti naziv salona na “Moj salon”. Podesiti radno vreme salona.
		// uradjeno u konstruktoru jer je BeautySalon Singleton klasa
		
		// Kreirati 1 menadžera (Nikola Nikolić)
		Manager manager = new Manager("Nikola", "Nikolić", Sex.MALE, "1112223330", "Vranjevacka 12", "nikolan12",
									  "123", 4, 3, 0, 20000);
		bs.addManager(manager);
		
		//  1 recepcionera (Pera Perić)
		Receptionist receptionist = new Receptionist("Pera", "Perić", Sex.MALE, "2223334440", "Sarajevska 13", "perap13",
													 "123", 4, 3, 0, 15000);
		manager.addReceptionist(receptionist);
		
		// 3 kozmetičara (Sima Simić, Žika Žikić, Jadranka Jovanović)
		Beautician beautician1 = new Beautician("Sima", "Simić", Sex.MALE, "3334445550", "Partizanska 12", "simas12",
												"123", 4, 3, 0, 10000, new ArrayList<ServiceType>());
		manager.addBeautician(beautician1);
		
		Beautician beautician2 = new Beautician("Žika", "Žikić", Sex.MALE, "4445556660", "Partizanska 34", "zikaz34",
												"123", 4, 3, 0, 10000, new ArrayList<ServiceType>());
		manager.addBeautician(beautician2);
		
		Beautician beautician3 = new Beautician("Jadranka", "Jovanović", Sex.FEMALE, "5556667770", "Mackova 132", "jadrankaj32",
												"123", 4, 3, 0, 10000, new ArrayList<ServiceType>());
		manager.addBeautician(beautician3);
		
		// 2 klijenta (Milica Milić, Mika Mikić)
		Client client1 = new Client("Milica", "Milić", Sex.FEMALE, "6667778880", "Junaka Milana Tepica 12", "milicam12", "123", false);
		manager.addClient(client1);
		
		Client client2 = new Client("Mika", "Mikić", Sex.MALE, "7778889990", "Lenjinova 12", "mikam12", "123", false);
		manager.addClient(client2);
		
		// Izmeniti ime kozmetičara Jadranke Jovanović na Jovana.
		Beautician beautician4 = new Beautician("Jovana", "Jovanović", Sex.FEMALE, "5556667770", "Mackova 132", "jadrankaj32",
				"123", 4, 3, 0, 10000, new ArrayList<ServiceType>());
		manager.modifyBeautician(beautician4);
		
		// Prikazati sve korisnike (može i po kategorijama).
		bs.viewUsers();
		
		// Obrisati kozmetičara Žika Žikić.
		manager.removeBeautician("zikaz34");
		
		// Prikazati sve korisnike (može i po kategorijama).
		bs.viewUsers();
		
		// Kreirati 3 tipa tretmana (masaža, manikir, pedikir).
		manager.addServiceType(new ServiceType("masaža"));
		manager.addServiceType(new ServiceType("manikir"));
		manager.addServiceType(new ServiceType("pedikir"));
		
		// Kreirati sledeće tretmane-usluge:
		// 1. Relaks masaža. Trajanje 45 minuta. Tip tretmana: masaža. Cena: 2000
		Service service1 = new Service("Relaks masaža", new ServiceType("masaža"), 45, 2000.0);
		manager.addService(service1);
		
		// 2. Sportska masaža. Trajanje 75 minuta. Tip tretmana: masaža. Cena: 2500
		Service service2 = new Service("Sportska masaža", new ServiceType("masaža"), 75, 2500.0);
		manager.addService(service2);
		
		// 3. Francuski manikir. Trajanje 50 minuta. Tip tretmana: manikir. Cena: 1500
		Service service3 = new Service("Francuski manikir", new ServiceType("manikir"), 50, 1500.0);
		manager.addService(service3);
		
		// 4. Gel lak. Trajanje 80 minuta. Tip tretmana: manikir. Cena: 1600
		Service service4 = new Service("Gel lak", new ServiceType("manikir"), 80, 1600.0);
		manager.addService(service4);
		
		// 5. Spa manikir. Trajanje 90 minuta. Tip tretmana: manikir. Cena: 2000
		Service service5 = new Service("Spa manikir", new ServiceType("masaža"), 90, 2000.0);
		manager.addService(service5);
		
		// 6. Spa pedikir. Trajanje 45 minuta. Tip tretmana: masaža. Cena: 1600
		Service service6 = new Service("Spa pedikir", new ServiceType("masaža"), 45, 1600.0);
		manager.addService(service6);
		
		// Prikazati sve tretmane-usluge. 
		System.out.println();
		manager.viewServices();
		System.out.println();
		
		// Izmeniti trajanje francuskog manikira na 55 minuta. 
		Service service7 = new Service("Francuski manikir", new ServiceType("manikir"), 55, 1500.0);
		manager.modifyService(service7);
		
		// Izmeniti tip tretmana za spa pedikir na pedikir.
		Service service8 = new Service("Spa manikir", new ServiceType("pedikir"), 90, 2000.0);
		manager.modifyService(service8);
		
		// Prikazati sve tretmane-usluge. 
		System.out.println();
		manager.viewServices();
		System.out.println();
		
		// Obrisati pedikir kao tip tretmana.
		manager.removeServiceType("pedikir");
		
		// Prikazati sve tretmane-usluge. 
		System.out.println();
		manager.viewServices();
		System.out.println();
		
		// (opciono) Kreirati cenovnik sa stavkama za svaki tretman-uslugu.
		
		// Kozmetičar Sima Simić je obučen za masažu i manikir.
		beautician1.addService(new ServiceType("masaža"));
		beautician1.addService(new ServiceType("manikir"));
		
		// Kozmetičar Jovana Jovanović je obučen za manikir.
		beautician4.addService(new ServiceType("manikir"));
		
		// Kreirati konkretan tretman t1 za uslugu relaks masaža kod kozmetičara Sime Simića za klijenta Milicu Milić.
		Timeslot timeslot1 = new Timeslot(LocalDateTime.parse("2023-05-12T12:00"), LocalDateTime.parse("2023-05-12T12:45"));
		Appointment t1 = new Appointment(0, beautician1, client1, timeslot1 , service1, AppointmentStatus.SCHEDULED);
		manager.addAppointment(t1);
		
		// Kreirati konkretan tretman t2 za uslugu gel lak kod kozmetičara Sime Simića za klijenta Miku Mikića.
		Timeslot timeslot2 = new Timeslot(LocalDateTime.parse("2023-05-11T12:00"), LocalDateTime.parse("2023-05-11T12:45"));
		Appointment t2 = new Appointment(1, beautician1, client2, timeslot2 , service4, AppointmentStatus.SCHEDULED);
		manager.addAppointment(t2);
		
		// Kreirati konkretan tretman t3 za uslugu spa manikir kod kozmetičara Jovane Jovanović za klijenta Miku Mikića.
		Timeslot timeslot3 = new Timeslot(LocalDateTime.parse("2023-05-11T12:00"), LocalDateTime.parse("2023-05-11T12:45"));
		Appointment t3 = new Appointment(2, beautician4, client2, timeslot3 , service8, AppointmentStatus.SCHEDULED);
		manager.addAppointment(t3);
		
		// Prikazati sve zakazane tretmane. 
		System.out.println();
		manager.viewAppointments();
		System.out.println();
		
		// Promeniti uslugu za t2 na francuski manikir.
		Appointment t4 = new Appointment(1, beautician1, client2, timeslot2 , service7, AppointmentStatus.SCHEDULED);
		manager.modifyAppointment(t4);
		
		// Prikazati sve zakazane tretmane. 
		System.out.println();
		manager.viewAppointments();
		System.out.println();
		
		// Prikazati cene svih zakazanih tretmana (ne moraju samo cene).
		// cene su prikazane u sklopu tretmana malopre
		
		// Promeniti cenu relaks masaže na 1700.
		Service service1_edit = new Service("Relaks masaža", new ServiceType("masaža"), 45, 1700.0);
		manager.modifyService(service1_edit);
		
		// Prikazati cene svih zakazanih tretmana (ne moraju samo cene).
		System.out.println();
		manager.viewAppointments();
		System.out.println();
		
		/*
		UserReader ur = new UserReader("src/data/users2");
		ArrayList<User> users = ur.loadUsers();
		
		ManagerReader mr = new ManagerReader("src/data/managers2");
		ArrayList<Manager> managers = mr.loadManagers();
		
		BeauticianReader br = new BeauticianReader("src/data/beauticians2");
		ArrayList<Beautician> beauticians = br.loadBeauticians();
		
		ReceptionistReader rr = new ReceptionistReader("src/data/receptionists2");
		ArrayList<Receptionist> receptionists = rr.loadReceptionists();
		
		ClientReader cr = new ClientReader("src/data/clients2");
		ArrayList<Client> clients = cr.loadClients();
		
		for(User u : users)
			System.out.println(u);
		System.out.println("\n");
		
		for(Manager m : managers)
			System.out.println(m);
		System.out.println("\n");
		
		for(Receptionist r : receptionists)
			System.out.println(r);
		System.out.println("\n");
		
		for(Beautician b : beauticians)
			System.out.println(b);
		System.out.println("\n");
		
		for(Client c : clients)
			System.out.println(c);
		
		MyWriter mw1 = new MyWriter("src/data/managers2");
		mw1.write(managers);
		
		MyWriter mw2 = new MyWriter("src/data/beauticians2");
		mw2.write(beauticians);
		
		MyWriter mw3 = new MyWriter("src/data/receptionists2");
		mw3.write(receptionists);
		
		MyWriter mw4 = new MyWriter("src/data/clients2");
		mw4.write(clients);
		*/
	}
	
	

}
