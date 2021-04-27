package com.pechmod.gui.receiveBroadcast;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.pechmod.connection.Client;
import com.pechmod.file.Record;
import com.pechmod.file.StatusRecord;
import com.pechmod.file.fileSelection.DropTargetHandler;
import com.pechmod.gui.events.Home_Action;
import com.pechmod.utils.User;

public class ReceiveBroadcast extends JPanel implements ActionListener{
	JButton back,receive;
	JLabel label;
	Record record;
	User user;
	
	JScrollPane scroll;
	
	JPanel bottomPanel;
	
	JPanel panel;
	String to;
	
   Home_Action homeAction;
	
	public ReceiveBroadcast(JPanel panel,String to)
	{
		
		setSize(350,550);
		setLayout(new BorderLayout());
		this.panel=panel;
		this.to=to;
		
		
		homeAction=new Home_Action(panel);
		
		
		
		back=new JButton("Back");
		receive=new JButton("Receive");
		
		
		
		
		label=new JLabel("Connection: Off");
		user=new User();

	
		
		
		bottomPanel=new JPanel(new BorderLayout());
		
		
		bottomPanel.add(back,BorderLayout.WEST);
		bottomPanel.add(label,BorderLayout.NORTH);
		bottomPanel.add(receive,BorderLayout.EAST);
		
		
		add(bottomPanel,BorderLayout.PAGE_END);

		
		back.setAction(homeAction);
		receive.addActionListener(this);
	
		
	}	
		
		

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Client client=new Client(new StatusRecord());
		
		
		client.connectTo("localhost",4444);
		try {
			record=(Record) client.getInput().readObject();
			
			
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		CardLayout c=(CardLayout)panel.getLayout();
		c.show(panel, to);
		
		
		
	}
}