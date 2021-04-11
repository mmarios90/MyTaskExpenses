package com.mytask.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.mytask.entity.Activity;

public class ActivityTableModel implements TableModel {

	private final List<Activity> activityList;

	private final String[] columnNames = new String[] {"Activity Id", "Title", "Item Code", "Total Coast" };

	private final Class[] columnClass = new Class[] { BigInteger.class, String.class, String.class, BigDecimal.class };

	public ActivityTableModel(List<Activity> activityList) {
		this.activityList = activityList;
	}

	
	public String getColumnName(int column) {
		return columnNames[column];
	}

	
	public Class<?> getColumnClass(int columnIndex) {
		return columnClass[columnIndex];
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return activityList.size();
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Activity row = activityList.get(rowIndex);
		if (0 == columnIndex) {
			return row.getActivityId();
		} else if (1 == columnIndex) {
			return row.getTitle();
		} else if (2 == columnIndex) {
			return row.getItemCode();
		} else if (3 == columnIndex) {
			return row.getTotalCost();
		}
		return null;
	}

	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Activity row = activityList.get(rowIndex);
		if (1 == columnIndex) {
			row.setTitle((String) aValue);
		} else if (2 == columnIndex) {
			row.setItemCode((String) aValue);
		}
		else if (3 == columnIndex) {
			row.setTotalCost((BigDecimal) aValue);
		}
	}

    
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }

	public void addTableModelListener(TableModelListener l) {

	}

	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}
}
