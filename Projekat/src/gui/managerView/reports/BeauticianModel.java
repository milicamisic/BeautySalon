package gui.managerView.reports;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import service.ManagerService;

public class BeauticianModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 7480224552092106397L;
	
	private String[] columns = {"Beautician", "Completed Appointments", "Money Made"};
	
	private ManagerService managerService;
	
	ArrayList<Object[]> tableContents;

	public BeauticianModel(LocalDate startDate, LocalDate endDate)
	{
		managerService = new ManagerService();
		tableContents = managerService.getBeauticianPerformanceReport(startDate, endDate);
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
		return this.tableContents.size();
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableContents.get(rowIndex)[columnIndex];
    }
}

