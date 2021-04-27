package com.pechmod.connection;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Locale;

import javax.swing.*;

import com.pechmod.file.Record;
import com.pechmod.file.StatusRecord;


public class ClientTest extends JFrame {

	Client client;
	JTable table;
	public ClientTest()
	{
		super("Client Test");
		setLayout(null);
		JLabel label=new JLabel();
		JButton button=new JButton("Connect");
		JTextField field=new JTextField();
		JPanel panel=new JPanel();
		panel.setLocation(0, 0);
		panel.setSize(600,100);
		
		label.setLocation(200,30);
		label.setSize(200, 500);
		label.setText("");
		
		button.setLocation(350, 200);
		button.setSize(120,30);
		
		field.setLocation(200, 200);
		field.setSize(150, 20);
		
		panel.add(field);
		panel.add(button);
		panel.add(label);
		add(panel,BorderLayout.NORTH);
		
		setSize(600,200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    client = new Client(new StatusRecord());

		try {
			
			client.connectTo("localhost",4444);
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Record word = null;
		try {
			word = (Record)client.getInput().readObject();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    table=word.getTable();
    table.setLocation(0,100);
    table.setSize(600, 100);
  
  add(table,BorderLayout.CENTER);
		revalidate();
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ClientTest test=new ClientTest();
		
		
	}

}
