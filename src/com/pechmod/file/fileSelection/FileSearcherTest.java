package com.pechmod.file.fileSelection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import com.pechmod.connection.Server;
import com.pechmod.file.Record;

public class FileSearcherTest extends JFrame {
	
	private JTable table;
	 Record record;



	
		public FileSearcherTest()
		{

		super("Jtable testing");
		
		 record=new Record(Locale.UK,3);
		 
		 table=record.getTable();
		table.setShowGrid(false);
		table.setRowHeight(40);
		table.setBorder(new TitledBorder(""));
		
		System.out.println("Server Started");
		Server server=new Server(record);

		
		JButton addButton=new JButton("Add File");
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				FileSearcher search =new FileSearcher();
				File file2=search.getFileOrDirectory();
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
	JPanel panel1=new JPanel();
	panel.add(addButton);
	panel.add(removeButton);
	panel.add(panel1);
	
	
	DropTargetHandler handler=new DropTargetHandler(panel1,record);


	Container container=getContentPane();
	container.add(new JScrollPane(table), BorderLayout.CENTER);
	container.add(panel,BorderLayout.NORTH);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(400,300);
	setVisible(true);

	}
		public static void main(String[] args) {
			
			
			
			FileSearcherTest ne=new FileSearcherTest();
			
		}	
				
			

		

		}
