package gui.managerView.serviceTypeCRUD;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import otherEntities.ServiceType;
import paket1.BeautySalon;
import service.ManagerService;

public class ServiceTypeModel extends AbstractTableModel{

	private static final long serialVersionUID = 8815387428147999174L;
	
	private String[] columns =  {"Service Type"};
	private BeautySalon beautySalon;
	
	private ArrayList<ServiceType> serviceTypes;
	
	public ServiceTypeModel() {
		this.beautySalon = BeautySalon.getBeautySalon();
		this.serviceTypes = beautySalon.getServiceTypes();
	}
	
	@Override
	public int getRowCount() {
		
		return this.serviceTypes.size();
	}

	@Override
	public int getColumnCount() {
		return this.columns.length;
	}
	
	@Override
	public String getColumnName(int columnIndex)
	{
		return this.columns[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ServiceType st = serviceTypes.get(rowIndex);
		return st.toCell(columnIndex);
	}
	
	public void removeRow(int rowIndex) {
		ServiceType st = serviceTypes.get(rowIndex);
		
		ManagerService managerService = new ManagerService();
		managerService.removeServiceType(st.getType());
		
		fireTableDataChanged();
	}
}
