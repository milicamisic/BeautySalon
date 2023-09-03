package gui.managerView.reports;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import humanEntities.Client;
import paket1.BeautySalon;

public class ClientModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 5638950489016023557L;

	private String[] columns = {"Username", "Money Spent", "Has Loyalty Card"};
	
	private BeautySalon beautySalon;
	
	private ArrayList<Client> clients;

	public ClientModel()
	{
		this.beautySalon = BeautySalon.getBeautySalon();
		clients = new ArrayList<Client>();
		ArrayList<Client> clientUnfilterd = beautySalon.getClients();
		for(Client c : clientUnfilterd) {
			clients.add(c);
		}
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
		Client c = clients.get(rowIndex);
		switch(columnIndex) {
		case 0 : return c.getUsername();
		case 1 : return c.getMoneySpent();
		case 2 : return c.hasLoyaltyCard();
		default: return "";
		}
	}
	
	

}
