package com.pechmod.file;

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RecordTest extends JFrame {
private JTable table;
 Record record;



public RecordTest()
{
	super("Jtable testing");
	table=new JTable();
	 record=new Record(Locale.UK,3);
	
	
	
	
	
	JButton addButton=new JButton("Add File");
	addButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			File file2=new File("C:/Users/USER/Documents/Downloads/Desktop/Distributor/Test/Distributor Test/src/com/pechmod/file/GP Calc.exe");
			record.addFile(file2);
		}
	});

	

	
JButton removeButton=new JButton("Remove Button");

removeButton.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{
		record.removeRow();
	}
});


JPanel panel=new JPanel();
panel.add(addButton);
panel.add(removeButton);


Container container=getContentPane();
container.add(new JScrollPane(table), BorderLayout.CENTER);
container.add(panel,BorderLayout.NORTH);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setSize(400,300);
setVisible(true);

}
	public static void main(String[] args) {
		
		
		
		RecordTest ne=new RecordTest();
		
		
			
		

	}

}
