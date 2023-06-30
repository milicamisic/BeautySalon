package paket1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import humanEntities.Beautician;
import humanEntities.Client;
import humanEntities.Manager;
import humanEntities.Receptionist;
import humanEntities.Sex;
import otherEntities.Appointment;
import otherEntities.Service;
import otherEntities.ServiceType;
import pisanje.MyWriter;
import service.AppointmentService;
import service.BeauticianService;
import service.ClientService;
import service.ManagerService;
import service.ReceptionistService;

public class KT3 {

	public static void main(String[] args) {
		
		// TEST ZA KT3
		ManagerService managerService = new ManagerService();
		ClientService clientService = new ClientService();
		ReceptionistService receptionistService = new ReceptionistService();
		BeauticianService beauticianService = new BeauticianService();
		AppointmentService appointmentService = new AppointmentService();
		
		BeautySalon beautySalon = BeautySalon.getBeautySalon();
		
		// Kreirati 1 menadžera (Nikola Nikolić)
		Manager manager1 = new Manager("Nikola", "Nikolić", Sex.MALE, "1112223330", "Vranjevacka 12", "nikola",
				  					   "123", 4, 3, 0, 20000);
		beautySalon.addManager(manager1);
		
		// 1 recepcionera (Pera Perić)
		Receptionist receptionist1 = new Receptionist("Pera", "Perić", Sex.MALE, "2223334440", "Sarajevska 13", "pera",
				 									  "123", 4, 3, 0, 15000);
		managerService.addReceptionist(receptionist1);
		
		// 3 kozmetičara (Sima Simić, Žika Žikić, Jovana Jovanović)
		Beautician beautician1 = new Beautician("Sima", "Simić", Sex.MALE, "3334445550", "Partizanska 12", "sima",
												"123", 4, 3, 0, 10000, new ArrayList<ServiceType>());
		managerService.addBeautician(beautician1);
		
		Beautician beautician2 = new Beautician("Žika", "Žikić", Sex.MALE, "4445556660", "Partizanska 34", "zika",
												"123", 4, 3, 0, 10000, new ArrayList<ServiceType>());
		managerService.addBeautician(beautician2);
		
		Beautician beautician3 = new Beautician("Jovana", "Jovanović", Sex.FEMALE, "5556667770", "Mackova 132", "jovana",
												"123", 4, 3, 0, 10000, new ArrayList<ServiceType>());
		managerService.addBeautician(beautician3);
		
		// 2 klijenta (Milica Milić i Mika Mikić)
		Client client1 = new Client("Milica", "Milić", Sex.FEMALE, "6667778880", "Junaka Milana Tepica 12", "milica", "123", 5000.0, true);
		managerService.addClient(client1);
		
		Client client2 = new Client("Mika", "Mikić", Sex.MALE, "7778889990", "Lenjinova 12", "mika", "123", 5000.0, true);
		managerService.addClient(client2);
		
		// Kreirati 3 tipa tretmana (masaža, manikir, pedikir).
		managerService.addServiceType(new ServiceType("masaža"));
		managerService.addServiceType(new ServiceType("manikir"));
		managerService.addServiceType(new ServiceType("pedikir"));
		
		// Kozmetičar Sima Simić je obučen za masažu i manikir.
		beautician1.addSkill(new ServiceType("masaža"));
		beautician1.addSkill(new ServiceType("manikir"));
		
		// Kozmetičar Žika Žikić je obučen za masažu, manikir i pedikir.
		beautician2.addSkill(new ServiceType("masaža"));
		beautician2.addSkill(new ServiceType("manikir"));
		beautician2.addSkill(new ServiceType("pedikir"));
		
		// Kozmetičar Jovana Jovanović je obučena za manikir.
		beautician3.addSkill(new ServiceType("manikir"));
		
		// Kreirati sledeće tretmane-usluge:
		// 1. Relaks masaža. Trajanje 45 minuta. Tip tretmana: masaža. Cena: 2000
		Service service1 = new Service("Relaks masaža", new ServiceType("masaža"), 45, 2000.0);
		managerService.addService(service1);
		
		// 2. Sportska masaža. Trajanje 75 minuta. Tip tretmana: masaža. Cena: 2500
		Service service2 = new Service("Sportska masaža", new ServiceType("masaža"), 75, 2500.0);
		managerService.addService(service2);
		
		// 3. Francuski manikir. Trajanje 50 minuta. Tip tretmana: manikir. Cena: 1500
		Service service3 = new Service("Francuski manikir", new ServiceType("manikir"), 50, 1500.0);
		managerService.addService(service3);
		
		// 4. Gel lak. Trajanje 80 minuta. Tip tretmana: manikir. Cena: 1600
		Service service4 = new Service("Gel lak", new ServiceType("manikir"), 80, 1600.0);
		managerService.addService(service4);
		
		// 5. Spa manikir. Trajanje 90 minuta. Tip tretmana: manikir. Cena: 2000
		Service service5 = new Service("Spa manikir", new ServiceType("manikir"), 90, 2000.0);
		managerService.addService(service5);
		
		// 6. Spa pedikir. Trajanje 45 minuta. Tip tretmana: pedikir. Cena: 1600
		Service service6 = new Service("Spa pedikir", new ServiceType("pedikir"), 45, 1600.0);
		managerService.addService(service6);
		// Milica Milić vrši online zakazivanje francuskog manikira i bira kozmetičara 3 u terminu 10.6.2023, 18:00h.
		clientService.makeAppointment(client1, beautician3, service3, LocalDateTime.of(2023, Month.JUNE, 10, 18, 0));
		
		// Milica Milić vrši zakazivanje spa pedikira preko recepcionera i bira kozmetičara 2 u terminu 11.06.2023, 09:00h. 
		receptionistService.makeAppointment(client1, beautician2, service6, LocalDateTime.of(2023, Month.JUNE, 11, 9, 0));
		
		// Mika Mikić vrši online zakazivanje sportske masaže i bira kozmetičara 1 u terminu 12.6.2023, 08:00h.
		clientService.makeAppointment(client2, beautician1, service2, LocalDateTime.of(2023, Month.JUNE, 12, 8, 0));
		
		// Mika vrši zakazivanje relaks masaže preko recepcionera i bira kozmetičara 2 u terminu 13.06.2023, 19:00h.
		receptionistService.makeAppointment(client2, beautician2, service1, LocalDateTime.of(2023, Month.JUNE, 13, 19, 0));
		
		// Mika pokušava da izvrši zakazivanje francuskog manikira i bira kozmetičara 3 u terminu 10.6.2023, 18:00h, ali neuspešno jer je taj termin 
		// i kozmetičar nisu dostupni. Prikazati poruku da je kreiranje termina neuspešno.
		// ovo ce biti neki message box kad bude bio gui
		boolean isSuccessful = clientService.makeAppointment(client2, beautician3, service3, LocalDateTime.of(2023, Month.JUNE, 10, 18, 0));
		if(!isSuccessful) {
			System.out.println("Appointment creation unsuccessful!");
		} else {
			System.out.println("Appointment creation successful!");
		}
		
		// Za kozmetičara 2 prikazati uvid u kozmetičke tretmane koji su mu dodeljeni i raspored.
		beauticianService.viewSchedule(beautician2);
		
		// Menadžer postavlja vrednost za karticu lojalnosti: 3500.
		managerService.setLoyaltyCardPrecondition(3500);
		
		// Prikazati da je prvi zakazan tretman od strane klijenta 1 uspešno izvršen.
		System.out.println("Salon balance: " + beautySalon.getBalance());
		ArrayList<Appointment> client1Appointments = clientService.getAppointments(client1);
		
		Appointment appointment1 = client1Appointments.get(0);
		appointmentService.endAppointment(appointment1);
		System.out.println();
		System.out.println("Status appointment1: " + appointment1.getStatus().toString() + ".");
		System.out.println("Client1 amount of money spent: " + client1.getMoneySpent());
		System.out.println("Salon balance: " + beautySalon.getBalance());
		System.out.println();
		
		// Zatim prikazati da je drugi termin od strane klijenta 1 otkazao klijent.
		Appointment appointment2 = client1Appointments.get(1);
		clientService.cancelAppointment(appointment2);
		System.out.println();
		System.out.println("Status appointment2: " + appointment2.getStatus().toString() + ".");
		System.out.println("Client1 amount of money spent: " + client1.getMoneySpent());
		System.out.println("Salon balance: " + beautySalon.getBalance());
		System.out.println();
		
		// Prikazati da je prvi tretman od strane klijenta 2 otkazao salon.
		ArrayList<Appointment> client2Appointments = clientService.getAppointments(client2);
		
		Appointment appointment3 = client2Appointments.get(0);
		receptionistService.cancelAppointment(appointment3);
		System.out.println();
		System.out.println("Status appointment3: " + appointment3.getStatus().toString() + ".");
		System.out.println("Client2 amount of money spent: " + client2.getMoneySpent());
		System.out.println("Salon balance: " + beautySalon.getBalance());
		System.out.println();
		
		// Zatim prikazati da se na drugom tretmanu klijent 2 nije ni pojavio.
		Appointment appointment4 = client2Appointments.get(1);
		receptionistService.expireAppointment(appointment4);
		System.out.println();
		System.out.println("Status appointment4: " + appointment4.getStatus().toString() + ".");
		System.out.println("Client2 amount of money spent: " + client2.getMoneySpent());
		System.out.println("Salon balance: " + beautySalon.getBalance());
		System.out.println();
		
		// Posle svake promene stanja tretmana prikazati 3 stvari: da se stanje tretmana promenilo, 
		// stanje na kartici lojalnosti (koliko je klijent potršio novca ukupno) i bilans novca salona.
		
		// Milica Milić vrši zakazivanje gel laka preko online i bira kozmetičara 1 u terminu 14.06.2023, 09:00h
		clientService.makeAppointment(client1, beautician1, service4, LocalDateTime.of(2023,  Month.JUNE, 14, 9, 0));
		
		// Mika Mikić vrši online zakazivanje spa manikira i ne želi da bira kozmetičara u terminu 14.6.2023, 09:00h.
		clientService.makeAppointment(client2, service5, LocalDateTime.of(2023,  Month.JUNE, 14, 9, 0));
		
		// Prikazati da je tretman uspešno izvršen, stanje na kartici lojalnosti (koliko 
		// je klijent potršio novca ukupno) i bilans na računu salona.
		client2Appointments = clientService.getAppointments(client2);
		
		Appointment appointment5 = client2Appointments.get(2);
		appointmentService.endAppointment(appointment5);
		
		System.out.println();
		System.out.println("Status appointment5: " + appointment5.getStatus().toString() + ".");
		System.out.println("Client2 amount of money spent: " + client2.getMoneySpent());
		System.out.println("Salon balance: " + beautySalon.getBalance());
		System.out.println();
		
		// Za Mika Mikića prikazati sve prethodne kozmetičke tretmane i sve neophodne detalje.
		System.out.println();
		System.out.println(client2);
		clientService.viewPastAppointments(client2);
		System.out.println();
		
		// Prikazati prihode i rashode za 6. mesec 2023. Računati da se plate zaposlenima za taj mesec isplaćuju prvog u mesecu.
		beautySalon.payWorkers(); // da bi se videlo da radi, inace ce metoda biti privatna i automatski ce se zvati svakog prvog
		System.out.println();
		managerService.viewRevenues(LocalDate.of(2023, 6, 1));
		System.out.println();
		managerService.viewExpenses(LocalDate.of(2023, 6, 1));
		System.out.println();
		
		// Prikazati bonuse (na proizvoljan način realizujte tako da kozmetičar 2 dobije bonus).
		for(Beautician b : beautySalon.getBeauticians()) {
			if(beauticianService.getAppointmentNumber(b) > beautySalon.getCompletedAppointmentsForBonus())
				b.setBonus(5000);
		}
		
		for(Beautician b : beautySalon.getBeauticians()) {
			System.out.println("Username: " + b.getUsername() + ", Bonus: " + b.getBonus());
		}
		
		MyWriter appointmentWriter = new MyWriter("src/data/appointments3");
		//appointmentWriter.write(beautySalon.getAppointments());
		
		MyWriter clientWriter = new MyWriter("src/data/clients3");
		//clientWriter.write(beautySalon.getClients());
		
		MyWriter beauticianWriter = new MyWriter("src/data/beauticians3");
		//beauticianWriter.write(beautySalon.getBeauticians());
		
		MyWriter serviceTypeWriter = new MyWriter("src/data/service_types3");
		//serviceTypeWriter.write(beautySalon.getServiceTypes());
		
		MyWriter serviceWriter = new MyWriter("src/data/services3");
		//serviceWriter.write(beautySalon.getServices());
		
		//System.out.println(beautySalon.getServices());
	}

}















