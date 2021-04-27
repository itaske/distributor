package com.pechmod.protocol;
import java.util.*;
import java.net.*;
import java.io.*;
import java.util.stream.Collectors;

import com.pechmod.file.Record;

public class BroadcastHandler implements Runnable {

	private Socket socket;
	private Record record;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public BroadcastHandler(Socket socket, Record record)
	{
		this.socket=socket;
		this.record=record;
		try {
			oos=new ObjectOutputStream(this.socket.getOutputStream());
			oos.flush();
			ois=new ObjectInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void run()
	{
		
		//write file names for client to make decisions
		try {
			System.out.println("Writing record");
      oos.writeObject(record.getFiles().stream().map(file->file.getName()).collect(Collectors.toList()));

			System.out.println("Done writing record");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// receive decision by client
		ClientReply reply=null;
		do{
			try {
				reply=(ClientReply)ois.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		while(reply==null);
		
		
		LinkedList<Integer>fileToSend=reply.getFileNumbers();
		
		LinkedList<LinkedList<ModalFile>> transferList=new LinkedList<LinkedList<ModalFile>>();
		
		//send Files selected by client
		for(int i=0; i<fileToSend.size(); i++)
		{
			LinkedList<ModalFile>prepareFile=new LinkedList<ModalFile>();
			TransferModalFile.makeModalList(prepareFile, record.getFile(fileToSend.get(i)), reply.getDestination());
			
			transferList.add(prepareFile);
			
		}
		
		try {
			oos.writeObject(transferList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//send 
		
		
	}
		
		public void closeHandler()
		{
			try {
				oos.close();
				ois.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

