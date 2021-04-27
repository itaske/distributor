package com.pechmod.connection;
import java.net.*;
import java.util.Locale;
import java.io.*;

import javax.swing.*;

import com.pechmod.file.Record;

import java.awt.event.*;
import java.awt.*;


public class ServerTest extends JFrame{


	public ServerTest()
	{
		super("Server Test");
		setLayout(null);
		JButton send=new JButton("Send");
		JLabel label=new JLabel();
	 Record record=new Record(Locale.US,3);
		
		send.setSize(70, 30);
		send.setLocation(300, 300);
		setSize(610,610);
		send.setEnabled(false);
		
		label.setLocation(100,500);
		label.setSize(200,30);
		label.setText("Server: Off   Connection: Off");
		add(send);
		add(label);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Server server=new Server(record);
		setVisible(true);
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try {
					System.out.println("Sent"); 
					server.getOutput().writeObject("Thanks for Connecting");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		
		
	}
	public static void main(String[] args) {
		ServerTest te=new ServerTest();
		

	}

}
