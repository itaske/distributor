package com.pechmod.connection;
import java.net.*;
import java.util.LinkedList;
import java.io.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.*;

import com.pechmod.file.StatusRecord;
import com.pechmod.protocol.ModalFile;
import com.pechmod.protocol.FileMerger;
import com.pechmod.protocol.Packet;
import com.pechmod.protocol.PacketMerger;

public class Client extends SwingWorker {

	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private boolean connected=false;
	private long totalBytesToReceive;
    private AtomicLong currentBytesRead;
	StatusRecord record;


	public Client(StatusRecord record)
	{
		this.record=record;
	}

	public void connectTo(String hostName,int port)
	{
		do{
			try {

				socket=new Socket(hostName,port);
				System.out.println("Connected");
				setUpStreams();
				System.out.println("Done setting streams");
				connected=true;
			}catch(UnknownHostException u){

				System.out.println("Could not connect to the server with name given");

				u.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("IO Error");
				e.printStackTrace();

			}

		}
		while(connected==false);
	}

	public void setUpStreams()throws IOException
	{
		oos=new ObjectOutputStream(socket.getOutputStream());
		oos.flush();
		ois=new ObjectInputStream(socket.getInputStream());

	}

	public ObjectOutputStream getOutput()
	{
		return oos;
	}

	public ObjectInputStream getInput()
	{
		return ois;
	}

	public void closeConnections()
	{
		try {
			if (socket!=null) {
				oos.close();
				ois.close();
				socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected Object doInBackground() throws Exception {
		setProgress(0);
		currentBytesRead = new AtomicLong();
		PacketMerger packerMerger = new PacketMerger();
		setProgress(0);
		while(currentBytesRead.get() < totalBytesToReceive){
			Packet packet = (Packet)ois.readObject();
			packerMerger.addPacketToPool(packet);
			currentBytesRead.set(currentBytesRead.get()+packet.getSize());
			double percentReceived = ((double)currentBytesRead.get()/getTotalBytesToReceive())*100;
			int percentInt = (int)percentReceived;
			setProgressValue(percentInt);
		}
		setProgress(100);
		return null;
	}

	public long getTotalBytesToReceive() {
		return totalBytesToReceive;
	}

	public void setTotalBytesToReceive(long totalBytesToReceive) {
		this.totalBytesToReceive = totalBytesToReceive;
	}

	public void setProgressValue(int value){
		setProgress(value);
	}

}
