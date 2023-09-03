package gui.managerView.reports;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import otherEntities.Service;
import service.ManagerService;

public class ServiceModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 7480224552092106397L;
	
	private String[] columns = {"Name", "Type", "Duration (min)", "Price", "Number Of Appointments", "Money Made"};
	
	private ManagerService managerService;
	
	ArrayList<Object[]> tableContents;

	public ServiceModel(LocalDate startDate, LocalDate endDate)
	{
		managerService = new ManagerService();
		tableContents = managerService.getServiceReport(startDate, endDate);
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
        Object[] item = tableContents.get(rowIndex);
        Service s = (Service) item[0];
        int appNum = (int) item[1];
        double money = (double) item[2];
        
        switch(columnIndex) {
        case 0: case 1: case 2: case 3:
        	return s.toCell(columnIndex);
        case 4:
        	return appNum;
        case 5:
        	return money;
        default:
        	return "";
        }
    }
}

