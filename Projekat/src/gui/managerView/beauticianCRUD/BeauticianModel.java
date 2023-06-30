package gui.managerView.beauticianCRUD;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import humanEntities.Beautician;
import paket1.BeautySalon;
import service.ManagerService;

public class BeauticianModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 2691262425559951118L;
	
	private String[] columns = {"Name", "Surname", "Sex", "Phone Number", "Address", "Username", "Education Lvl", "Service Years", "Base Pay", "Bonus"};
	
	private BeautySalon beautySalon;
	
	private ArrayList<Beautician> beauticians;
	
	

	public BeauticianModel()
	{
		this.beautySalon = BeautySalon.getBeautySalon();
		this.beauticians = beautySalon.getBeauticians();
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
		return this.beauticians.size();
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
		Beautician m = beauticians.get(rowIndex);
		return m.toCell(columnIndex);
	}

	public void removeRow(int rowIndex) {
		Beautician m = beauticians.get(rowIndex);
		
		ManagerService managerService = new ManagerService();
		managerService.removeBeautician(m.getUsername());
		
		fireTableDataChanged();
	}
	
	

}
