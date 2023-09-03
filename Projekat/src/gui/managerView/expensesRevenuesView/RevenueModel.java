package gui.managerView.expensesRevenuesView;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import otherEntities.Revenue;
import paket1.BeautySalon;

public class RevenueModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 8810102847949772279L;
	
	private String[] columns = {"Description", "Money Made", "Date"};
	private BeautySalon beautySalon;
	
	private ArrayList<Revenue> revenues;
	
	

	public RevenueModel()
	{
		this.beautySalon = BeautySalon.getBeautySalon();
		this.revenues = beautySalon.getRevenues();
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
		return this.revenues.size();
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
		Revenue r = revenues.get(rowIndex);
		return r.toCell(columnIndex);
	}
}
