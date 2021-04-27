package com.pechmod.file;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.Toolkit;
import java.io.*;

public class Record implements Serializable {

	private LinkedList<File> files;

	private Locale locale;
	private DefaultTableModel tableModel;
	private JTable table;
	private int num;




	public Record(Locale locale,int num)
	{
		this.locale=locale;
		this.num=num;
		files=new LinkedList<File>();

		//	ResourceBundle resources=ResourceBundle.getBundle("StringsAndLabels",locale);


		tableModel=new MyTableModel();
		tableModel.addColumn("File Name");
		tableModel.addColumn("File Length");

		if(this.num==3)
			tableModel.addColumn("Choose File to Receive");


		table=new JTable(tableModel);



	}

	public void addFile(File file)
	{
		if (file!=null) {
			files.add(file);
			addRow(file);
		}
	}


	public void removeFile(int number)
	{
		if(number>=0 && number<files.size())
		{
			files.remove(number);
		}
	}

	public File getFile(int number)
	{
		if(number>=0 && number<files.size())
			return files.get(number);
		return null;
	}


	public LinkedList<File> getFiles()
	{
		return files;
	}

	public int getSize()
	{
		return files.size();
	}



	public void addRow(File file)
	{

		Object object[]={file.getName(), file.length()/Math.pow(10,6)+" MB",false};

		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				tableModel.addRow(object);

			}
		});


	}

	public void removeRow()
	{

		SwingUtilities.invokeLater(new Runnable(){
			public void run(){

				try{
					int row=table.getSelectedRow();

					tableModel.removeRow(row);
					removeFile(row);
				}
				catch(ArrayIndexOutOfBoundsException ai){
					JOptionPane.showMessageDialog(null,"Please Select a Row","Error Selection",JOptionPane.ERROR_MESSAGE);

				}
			}
		});

	}


	public boolean isSelected(int row,int col)
	{
		if((Boolean)tableModel.getValueAt(row, col)==true)
			return true;
		return false;

	}
	public LinkedList<File> getSelectedFiles(){
		LinkedList<File> list=new LinkedList<File>();

		int totalCol=table.getColumnCount();
		int totalRow=table.getRowCount();

		for(int row=1; row<totalRow; row++)
		{
			if(isSelected(row,totalCol-1))
			{
				list.add(getFile(row));
			}
		}

		return list;
	}
	public JTable getTable()
	{
		return table;
	}


	public void clear()
	{
		for(int i=0; i<table.getRowCount(); ) // no need for incrementing i cos it table.getRowCount updates
		{
			tableModel.removeRow(i);
			removeFile(i);
		}
	}

	public void setColumnCount(int num)
	{
		if(num==3)
			tableModel.addColumn("Choose File to Receive");

		this.num=num;
	}
}
