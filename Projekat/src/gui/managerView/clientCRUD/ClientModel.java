package gui.managerView.clientCRUD;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import humanEntities.Client;
import paket1.BeautySalon;
import service.ManagerService;

public class ClientModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 2691262425559951118L;
	
	private String[] columns = {"Name", "Surname", "Sex", "Phone Number", "Address", "Username", "Money Spent", "Has Loyalty Card"};
	
	private BeautySalon beautySalon;
	
	private ArrayList<Client> clients;
	
	

	public ClientModel()
	{
		this.beautySalon = BeautySalon.getBeautySalon();
		this.clients = beautySalon.getClients();
	}

	@Override
	/*
	 * Ako se ova metoda ne redefinise, koristi se default renderer/editor za 
	 * celiju. To znaci da, ako je kolona tipa boolean, onda ce se u tabeli 
	 * prikazati true/false, a ovako ce se za takav tip kolone pojaviti
	 * checkbox.
	 */
	public Class<?> getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}

	@Override
	public int getRowCount()
	{
		return this.clients.size();
	}

	@Override
	public int getColumnCount()
	{
		return this.columns.length;
	}
	
	@Override
	public String getColumnName(int columnIndex)
	{
		return this.columns[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Client m = clients.get(rowIndex);
		return m.toCell(columnIndex);
	}

	public void removeRow(int rowIndex) {
		Client m = clients.get(rowIndex);
		
		ManagerService managerService = new ManagerService();
		managerService.removeClient(m.getUsername());
		
		fireTableDataChanged();
	}
	
	

}
