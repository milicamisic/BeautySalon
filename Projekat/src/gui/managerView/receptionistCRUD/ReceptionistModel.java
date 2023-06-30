package gui.managerView.receptionistCRUD;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import humanEntities.Receptionist;
import paket1.BeautySalon;
import service.ManagerService;
import service.ReceptionistService;

public class ReceptionistModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 2691262425559951118L;
	
	private String[] columns = {"Name", "Surname", "Sex", "Phone Number", "Address", "Username", "Education Lvl", "Service Years", "Base Pay", "Bonus"};
	
	private BeautySalon beautySalon;
	
	private ArrayList<Receptionist> receptionists;
	
	

	public ReceptionistModel()
	{
		this.beautySalon = BeautySalon.getBeautySalon();
		this.receptionists = beautySalon.getReceptionists();
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
		return this.receptionists.size();
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
		Receptionist m = receptionists.get(rowIndex);
		return m.toCell(columnIndex);
	}

	public void removeRow(int rowIndex) {
		Receptionist m = receptionists.get(rowIndex);
		
		ManagerService managerService = new ManagerService();
		managerService.removeReceptionist(m.getUsername());
		
		fireTableDataChanged();
	}
	
	

}
