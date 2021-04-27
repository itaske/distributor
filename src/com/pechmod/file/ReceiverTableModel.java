package com.pechmod.file;

import javax.swing.table.DefaultTableModel;

public class ReceiverTableModel  extends DefaultTableModel{

	
	
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		switch(columnIndex)
		{
		case 0:
			return String.class;
		case 1:
			return
					Boolean.class;
		}
		return Object.class;
	}

}
