package com.pechmod.gui.broadcast;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.pechmod.connection.BroadcastServer;
import com.pechmod.file.Record;
import com.pechmod.file.fileSelection.DropTargetHandler;
import com.pechmod.file.fileSelection.FileSearcher;
import com.pechmod.gui.Frame;
import com.pechmod.gui.events.Home_Action;
import com.pechmod.main.Test;
import com.pechmod.utils.User;



public class BroadcastingPanel  extends JPanel implements ActionListener{

	JButton addFile,removeFile,back,stopBroadcast,broadcast;
	JLabel label;
	Record record;
	User user;
	
	JScrollPane scroll;
	
	JPanel topPanel,bottomPanel;
	
	JPanel panel;
	String from;
	
    DropTargetHandler dropHandler;
	
	
	JTable table;
	
	BroadcastServer server;
	public BroadcastingPanel(Record record,JPanel panel,String from)
	{
		
		setSize(350,550);
		setLayout(new BorderLayout());
		this.panel=panel;
		this.from=from;
		this.record=record;
		
		
		
		
		addFile=new JButton("Add File");
		removeFile=new JButton("Remove File");
		
		
		back=new JButton("Back",new ImageIcon(Frame.class.getResource("events/Images/back.gif")));
		 back.setHorizontalTextPosition(SwingConstants.RIGHT);
		 back.setIconTextGap(5);
		
		
		
		stopBroadcast=new JButton("Stop",new ImageIcon(Frame.class.getResource("events/Images/stop.gif")));
		
		 stopBroadcast.setHorizontalTextPosition(SwingConstants.RIGHT);
		 stopBroadcast.setIconTextGap(5);
		stopBroadcast.setEnabled(false);
		
		
		broadcast=new JButton("Broadcast");
		
		if(this.record!=null)
		 table=this.record.getTable();
		else
			table=new JTable();
		scroll=new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		table.setShowGrid(false);
		table.setShowHorizontalLines(true);
		table.setRowHeight(50);
		label=new JLabel("Connection: OFF");
		user=new User();

	
		
		
		topPanel=new JPanel();
		bottomPanel=new JPanel(new BorderLayout());
		
		topPanel.add(addFile);
		topPanel.add(removeFile);
		
		bottomPanel.add(back,BorderLayout.WEST);
		bottomPanel.add(label,BorderLayout.NORTH);
		bottomPanel.add(stopBroadcast,BorderLayout.EAST);
		bottomPanel.add(broadcast,BorderLayout.SOUTH);
		
		
		add(topPanel,BorderLayout.PAGE_START);
		add(bottomPanel,BorderLayout.PAGE_END);
		add(scroll,BorderLayout.CENTER);
		
	dropHandler=new DropTargetHandler(this,this.record);
		back.addActionListener(this);
		stopBroadcast.addActionListener(this);
		addFile.addActionListener(this);
		removeFile.addActionListener(this);
		broadcast.addActionListener(this);
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		CardLayout c =(CardLayout)panel.getLayout();
		
		
		
		 if(e.getSource()==stopBroadcast)
		{
			 
			String message= "Are you sure you want to\n  stop the Connection";
			
			Toolkit.getDefaultToolkit().beep();
			int reply= JOptionPane.showConfirmDialog(null,message,"Cofirmation !!",JOptionPane.YES_NO_OPTION);
			 if(reply==JOptionPane.NO_OPTION)
				 return;
			server.stopThread();
			
			back.setEnabled(true);
		   
			
			broadcast.setEnabled(true);
			stopBroadcast.setEnabled(false);
			label.setText("Connection: OFF");
			
			
			
		}
		 else if(e.getSource()==addFile)
		 {
			 FileSearcher searcher=new FileSearcher();
			 File file=searcher.getFileOrDirectory();
			 if(file==null)
				 return;
			 
			 record.addFile(file);
			 
		 }
		 else if(e.getSource()==removeFile)
		 {
			 
			 record.removeRow();
			 
		 }
		 
		 else if(e.getSource()==back)
		 {
			
			 broadcast.setEnabled(true);
			 stopBroadcast.setEnabled(false);
			 revalidate();
			 if(record.getSize()>0)
				 record.clear();
			 c.show(panel, from);
		 }
		 else if(e.getSource()==broadcast)
		 {
			 if(record.getSize()<1)
			 {
				 Toolkit.getDefaultToolkit().beep();
				 JOptionPane.showMessageDialog(null,"Choose Files to Broadcast", "No Files Present",JOptionPane.ERROR_MESSAGE);
				 return ;
			 }
			 server=new BroadcastServer(record);
			ExecutorService es=Executors.newCachedThreadPool();
	
			es.execute(server);
			
			es.shutdown();
			 broadcast.setEnabled(!server.isOpen());
			 stopBroadcast.setEnabled(true);
			 back.setEnabled(false);
			
			 
			 label.setText("Connection: ON");
			 revalidate();
			 repaint();
		 }
		
	}
	
	public Record getRecord()
	{
		return record;
	}
	
}
