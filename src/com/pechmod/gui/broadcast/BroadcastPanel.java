package com.pechmod.gui.broadcast;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.pechmod.connection.BroadcastServer;
import com.pechmod.file.Record;
import com.pechmod.file.fileSelection.DropTargetHandler;
import com.pechmod.file.fileSelection.FileSearcher;
import com.pechmod.gui.events.Home_Action;
import com.pechmod.utils.User;



public class BroadcastPanel  extends JPanel implements ActionListener{

	JButton addFile,removeFile,back,startBroadcast;
	JLabel label;
	Record record;
	User user;
	
	JScrollPane scroll;
	
	JPanel topPanel,bottomPanel;
	
	JPanel panel;
	String to;
	
    DropTargetHandler dropHandler;
	
	
	JTable table;
	public BroadcastPanel(Record record,JPanel panel,String to)
	{
		
		setSize(350,550);
		setLayout(new BorderLayout());
		this.panel=panel;
		this.to=to;
		this.record=record;
		
		
		
		addFile=new JButton("Add File");
		removeFile=new JButton("Remove File");
		back=new JButton("Back");
		startBroadcast=new JButton("Broadcast");
		
		
		 table=record.getTable();
		scroll=new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		table.setShowGrid(false);
		table.setShowHorizontalLines(true);
		table.setRowHeight(50);
		label=new JLabel("Connection: Off");
		user=new User();

	
		
		
		topPanel=new JPanel();
		bottomPanel=new JPanel(new BorderLayout());
		
		topPanel.add(addFile);
		topPanel.add(removeFile);
		
		bottomPanel.add(back,BorderLayout.WEST);
		bottomPanel.add(label,BorderLayout.NORTH);
		bottomPanel.add(startBroadcast,BorderLayout.EAST);
		
		add(topPanel,BorderLayout.PAGE_START);
		add(bottomPanel,BorderLayout.PAGE_END);
		add(scroll,BorderLayout.CENTER);
		
	dropHandler=new DropTargetHandler(this,this.record);
		back.addActionListener(this);
		startBroadcast.addActionListener(this);
		addFile.addActionListener(this);
		removeFile.addActionListener(this);
		
		this.setToolTipText("Drag and drop files");
		
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		CardLayout c =(CardLayout)panel.getLayout();
		
		
		
		 if(e.getSource()==startBroadcast)
		{
			 
			 if(record.getSize()>0)
			c.show(panel, to);
			 else
				 JOptionPane.showMessageDialog(null,"Please Choose files to Broadcast","File Selection Error",JOptionPane.ERROR_MESSAGE);
			 
		}
		 else if(e.getSource()==addFile)
		 {
			 FileSearcher searcher=new FileSearcher();
			 File file=searcher.getFileOrDirectory();
			 record.addFile(file);
			 
		 }
		 else if(e.getSource()==removeFile)
		 {
			 record.removeRow();
			 
		 }
		 
		 
		
		
	}
	
	public void setRecord(Record record)
	{
		this.record=record;
	}
	
	public Record getRecord()
	{
		return record;
	}
	
	
}
