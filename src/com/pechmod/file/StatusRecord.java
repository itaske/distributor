package com.pechmod.file;

import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class StatusRecord implements Serializable {

	private LinkedList<String>collection;
	

	private DefaultTableModel tableModel;
	private JTable table;
	
	
	
	

	public StatusRecord()
	{
		
		collection=new LinkedList<String>();
		
		
		
		tableModel=new DefaultTableModel();
		tableModel.addColumn("File Name");
		tableModel.addColumn("Location");
		tableModel.addColumn("Status");
		
		
		table=new JTable(tableModel);
		
		
		
	}
	
	public void addFile(String file,String location)
	{
		collection.add(location+File.separator+file);
		addRow(file,location);
	}
	
	
	public String getFile(int number)
	{
		if(number>=0 && number<collection.size())
			return collection.get(number);
		return null;
	}
	
	public LinkedList<String> getFiles()
	{
		return collection;
	}
	
	public int getSize()
	{
		return collection.size();
	}
	

	
	public void addRow(String file,String location)
	{
		
	
			Object object[] = {file,location,"Received" };
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					tableModel.addRow(object);

				}
			});
		
		
		
	}
	
	public JTable getTable()
	{
		return table;
	}
		
	
	public void clear()
	{
		for(int i=0; i<table.getRowCount(); i++)
		tableModel.removeRow(i);
	}
	
	
}