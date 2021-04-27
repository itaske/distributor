package com.pechmod.file;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel{

	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		switch(columnIndex)
		{
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return Boolean.class;
		}
		return Object.class;
	}

}
