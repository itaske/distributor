package com.pechmod.connection;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;

import com.pechmod.file.Record;
import com.pechmod.protocol.BroadcastHandler;



public class BroadcastServer implements Runnable{

	private ServerSocket server;
	private Socket socket;
	private Record record;
	
	ExecutorService es;
	
	public boolean openConnection;
	public boolean stop;
	public BroadcastServer(Record record)
	{
		this.record=record;
		openConnection=false;
		
		try
		{
		   server=new ServerSocket(2222);
		   System.out.println("Starting server");		}
		catch(IOException io)
		{
			io.printStackTrace();
		}
		
		
	}
	public void doInBackground() throws Exception {
		setIsOpen(true);
		while(isOpen())
		{
			System.out.println("Looking for connection");
		
						
		try{	
			socket = server.accept();
			System.out.println("Handler started");
			BroadcastHandler handler = new BroadcastHandler(socket, record);
			 es = Executors.newCachedThreadPool();
			es.execute(handler);
		}
		catch(SocketException se)
		{
			System.out.println("Stopped");
			setIsOpen(false);
		}
		
		
		
		}//end while
		
	}

		
	
	public void setIsOpen(boolean open)
	{
		openConnection=open;
	}
	
	public boolean isOpen()
	{
		return openConnection;
	}
	
	public void run()
	{
		try {
			doInBackground();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public void stopThread()
	{
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
