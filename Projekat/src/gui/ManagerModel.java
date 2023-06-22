package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import humanEntities.Manager;

public class ManagerModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 2691262425559951118L;
	
	private String[] columns = {"Name", "Surname", "Sex", "Phone Number", "Address", "Username", "Education Lvl", "Service Years", "Base Pay", "Bonus"};
	
	private ArrayList<Manager> managers;
	
	

	public ManagerModel(ArrayList<Manager> managers)
	{
		this.managers = managers;
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
		return this.managers.size();
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
		Manager m = managers.get(rowIndex);
		return m.toCell(columnIndex);
	}
	
	

}
