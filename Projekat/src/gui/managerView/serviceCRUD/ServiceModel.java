package gui.managerView.serviceCRUD;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import otherEntities.Service;
import paket1.BeautySalon;
import service.ManagerService;

public class ServiceModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 8810102847949772279L;
	
	private String[] columns = {"Name", "Type", "Duration (min)", "Price"};
	private BeautySalon beautySalon;
	
	private ArrayList<Service> services;
	
	

	public ServiceModel()
	{
		this.beautySalon = BeautySalon.getBeautySalon();
		this.services = beautySalon.getServices();
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
		return this.services.size();
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
		Service s = services.get(rowIndex);
		return s.toCell(columnIndex);
	}

	public void removeRow(int rowIndex) {
		Service s = services.get(rowIndex);
		
		ManagerService managerService = new ManagerService();
		managerService.removeService(s.getName());
		
		fireTableDataChanged();
	}
}
