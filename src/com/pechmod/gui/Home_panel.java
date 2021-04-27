package com.pechmod.gui;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;

import com.pechmod.connection.Server;
import com.pechmod.main.Test;
import com.pechmod.utils.User;
import com.pechmod.gui.Frame;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;


public class Home_panel  extends JPanel implements Observer,ActionListener{

	private JPanel panel; String to;
	private JPanel panel1 ;
	private JPanel panel2;
	
	private String username;
	
	private ButtonGroup group;
	
	private JRadioButton send;
	private JRadioButton receive;
	private JRadioButton broadcast;
	private JRadioButton receiveBroadcast;
	
	private JButton ok;
	
	private JLabel label,pechmod,welcome;
	private JLabel sendLabel,receiveLabel,broadcastLabel,receiveBroadcastLabel;
	
	private User user;
	
	public Home_panel(User user,JPanel panel,String to) throws NullPointerException
	{
		setSize(350,580);
		setLayout(null);
		
		if(user==null)
			throw new NullPointerException();
		
		this.panel=panel;
		this.to=to;
		
		this.user=user;
		
		this.user.addObserver(this);
		
		username=this.user.getUserName();
		panel1=new JPanel(null);
		
		
		
		
		welcome=new JLabel();
		welcome.setText("Welcome");
		welcome.setForeground(Color.WHITE);
		welcome.setFont(new Font("Times new Roman",Font.PLAIN,30));
		welcome.setLocation(10,10);
		welcome.setSize(300,60);
		

		label=new JLabel();
		label.setText(username);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Times new Roman",Font.PLAIN+Font.BOLD,30));
		label.setLocation(40,50);
		label.setSize(300,60);
		
		pechmod=new JLabel();
		pechmod.setText("PECHMOD");
		pechmod.setForeground(Color.WHITE);
		pechmod.setFont(new Font("Times new Roman",Font.PLAIN+Font.BOLD,10));
		pechmod.setLocation(250,150);
		pechmod.setSize(70,90);
			
		
		
		panel1.setBackground(Color.blue);
		panel1.setForeground(Color.white);
		panel1.setLocation(0,0);
		panel1.setSize(350,200);
		panel1.add(label);
		panel1.add(pechmod);
		panel1.add(welcome);
		
		panel2=new JPanel(null);
		panel2.setBackground(Color.white);
		panel2.setSize(350,380);
		panel2.setLocation(0,200);
		
		sendLabel=new JLabel("Send");
		sendLabel.setForeground(Color.black);
		sendLabel.setFont(new Font("Times new Roman",Font.PLAIN,20));
		sendLabel.setLocation(160,45);
		sendLabel.setSize(300,30);
		
		receiveLabel=new JLabel("Receive");
		receiveLabel.setForeground(Color.black);
		receiveLabel.setFont(new Font("Times new Roman",Font.PLAIN,20));
		receiveLabel.setLocation(160,75);
		receiveLabel.setSize(300,30);
		
		broadcastLabel=new JLabel("Broadcast");
		broadcastLabel.setForeground(Color.black);
		broadcastLabel.setFont(new Font("Times new Roman",Font.PLAIN,20));
		broadcastLabel.setLocation(160,105);
		broadcastLabel.setSize(300,30);
		
		receiveBroadcastLabel=new JLabel("Receive Broadcast");
		receiveBroadcastLabel.setForeground(Color.black);
		receiveBroadcastLabel.setFont(new Font("Times new Roman",Font.PLAIN,20));
		receiveBroadcastLabel.setLocation(160,135);
		receiveBroadcastLabel.setSize(300,30);
		
		group=new ButtonGroup();
		
		send=new JRadioButton("Send",true);
		receive=new JRadioButton("Receive",false);
		broadcast=new JRadioButton("Broadcast",false);
		receiveBroadcast=new JRadioButton("Receive Broadcast",false);
		
		send.setLocation(130, 50);
		receive.setLocation(130,80);
		broadcast.setLocation(130,110);
		receiveBroadcast.setLocation(130,140);
		
		send.setSize(20,20);
		receive.setSize(20,20);
		broadcast.setSize(20,20);
		receiveBroadcast.setSize(20,20);
		
		
		ok=new JButton("Ok",new ImageIcon(Frame.class.getResource("events/Images/ok.png")));
		ok.setSize(70,30);
		ok.setLocation(250,250);
		ok.setHorizontalTextPosition(SwingConstants.RIGHT);
		ok.setIconTextGap(5);
		
	
		
		panel2.add(send);
		panel2.add(receive);
		panel2.add(broadcast);
		panel2.add(receiveBroadcast);
		
		panel2.add(sendLabel);
		panel2.add(receiveLabel);
		panel2.add(broadcastLabel);
		panel2.add(receiveBroadcastLabel);
		
		panel2.add(ok);
		
		
		group.add(send);
		group.add(receive);
		group.add(broadcast);
		group.add(receiveBroadcast);
		
		
		
		
		add(panel1);
		add(panel2);
		
		
		
		ok.addActionListener(this);
		
		setVisible(true);
		
	}


	
	public void update(Observable arg0, Object arg1) {
		label.setText(user.getUserName());
		
	}

	public void actionPerformed(ActionEvent e) {
	String to;
	CardLayout c = (CardLayout) panel.getLayout();
		if(e.getSource()==ok)
		{
			
			
			if (send.isSelected()) {
				to="Send";
				c.show(panel, to);
			
			}
			
			else if(receive.isSelected())
			{
				to="Receive";
				c.show(panel,to);
			}
			
			else if(broadcast.isSelected())
			{
				to="Broadcasting";
				c.show(panel, to);
			}
		
			else if(receiveBroadcast.isSelected())
			{
				to="Receive Broadcast";
				c.show(panel, to);
				
			}
			
		}
		
	}
	

}
