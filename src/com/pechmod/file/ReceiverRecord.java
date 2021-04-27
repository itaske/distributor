package com.pechmod.file;

import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public class ReceiverRecord implements Serializable {

	private LinkedList<String>collection;
	
	private Locale locale;
	private DefaultTableModel tableModel;
	private JTable table;
	
	
	
	

	public ReceiverRecord(Locale locale)
	{
		this.locale=locale;
		collection=new LinkedList<String>();
		
	//	ResourceBundle resources=ResourceBundle.getBundle("StringsAndLabels",locale);
		
		
		tableModel=new ReceiverTableModel();
		tableModel.addColumn("File Name");
		tableModel.addColumn("Choose File to Receive");
		
		
		table=new JTable(tableModel);
		
		
		
	}
	
	public void addFiles(LinkedList<String> files)
	{
		collection=files;
		addRow(files);
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
	

	
	public void addRow(LinkedList<String> files)
	{
		
		for (int i = 0; i < files.size(); i++) {
			Object object[] = {files.get(i), new Boolean(false) };
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					tableModel.addRow(object);

				}
			});
		}
		
		
	}
	
	
	
	public boolean isSelected(int row,int col)
	{
		if((Boolean)tableModel.getValueAt(row, col)==true)
			return true;
		return false;
			
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
