package otherEntities;

import humanEntities.Beautician;
import humanEntities.Client;
import pisanje.ISerializable;

public class Appointment implements ISerializable{
	
	private int id;
	private Beautician beautician;
	private Client client;
	private Timeslot timeslot;
	private Service service;
	private AppointmentStatus status;
	
	public Appointment(int id, Beautician beautician, Client client, Timeslot timeslot, Service service,
			AppointmentStatus status) {
		super();
		this.id = id;
		this.beautician = beautician;
		this.client = client;
		this.timeslot = timeslot;
		this.service = service;
		this.status = status;
	}
	
	public Appointment(Client client, Timeslot timeslot, Service service, AppointmentStatus status)
	{
		super();
		this.client = client;
		this.timeslot = timeslot;
		this.service = service;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Beautician getBeautician() {
		return beautician;
	}

	public void setBeautician(Beautician beautician) {
		this.beautician = beautician;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Timeslot getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(Timeslot timeslot) {
		this.timeslot = timeslot;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + Integer.toString(id) + ", beautician=" + beautician.getUsername() + ", client=" + client.getUsername() + ", timeslot=" + timeslot + ", service="
				+ service + ", status=" + status + "]";
	}



	public String toLine() {
		return Integer.toString(id) + "|" + beautician.getUsername() + "|" + client.getUsername() + "|" + timeslot.toLine() + "|" + service.getName() + "|" + service.getType().getType() + "|" + Integer.toString(service.getDurationInMinutes()) + "|" + Double.toString(service.getPrice()) + "|" + status.toString();
	}
	
	public Object toCell(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return id;
		case 1:
			return beautician.getUsername();
		case 2:
			return client.getUsername();
		case 3:
			return timeslot.toLine().split(";")[0].replace("T", " ");
		case 4:
			return service.getDurationInMinutes();
		case 5:
			return service.getName();
		case 6:
			return service.getType().getType();
		case 7:
			return service.getPrice();
		case 8:
			return status.toString();
		default:
			return "";
		}
	}
}
