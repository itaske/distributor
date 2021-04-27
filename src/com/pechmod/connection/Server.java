package com.pechmod.connection;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import com.pechmod.file.Record;
import com.pechmod.protocol.*;
import com.pechmod.utils.FileUtils;

import javax.swing.*;


public class Server extends SwingWorker {

	private ServerSocket server;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Record record;
	private boolean running;
	private ClientReply reply;
	public static final int DEFAULT_PACKET_READ = 1000;
	public static final int CONSUMER_NUMBERS = Runtime.getRuntime().availableProcessors();
	public static ExecutorService executorService = Executors.newFixedThreadPool(CONSUMER_NUMBERS);
	private BlockingQueue<Packet> packetsQueue;
	long totalBytes =0;

	public Server(Record record)
	{
		this.record=record;
		try
		{

			server=new ServerSocket(4444);
			packetsQueue = new LinkedBlockingQueue<>(DEFAULT_PACKET_READ);
		}
		catch(IOException io)
		{
			io.printStackTrace();
		}
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public ClientReply getClientReply() {
		return reply;
	}

	public void setClientReply(ClientReply reply) {
		this.reply = reply;
	}

	/**
	 * @return ClientReply after connection
	 */
	public ClientReply acceptConnection(){
		System.out.println("Looking for connection");
		ClientReply reply=null;

		try {
			socket = server.accept();
			setUpStreams();
			System.out.println("Done setting streams");
			running = true;
		}
		catch(IOException e){
			e.printStackTrace();
			running = false;
			return null;
		}

		do
		{
			try {
				reply=(ClientReply)ois.readObject(); // to get destination information and others details
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		while(reply==null);

		return reply;
	}

	public void sendFiles(){
		setProgress(0);

		//Create PacketWriters to Write Packets to Queue
		for(int i=0; i<record.getSize(); i++) {
			System.out.println(record.getFile(i).getName());
			executorService.execute(new PacketWriter(record.getFile(i), packetsQueue, ""));
		}

		long bytesProcessed = 0;
		//Create Packet Consumers to Write Packets to Socket outputPort
		executorService.execute(new PacketConsumer(packetsQueue, oos, this, bytesProcessed));

	}


	public void setUpStreams()throws IOException
	{

		ois=new ObjectInputStream(socket.getInputStream());
		oos=new ObjectOutputStream(socket.getOutputStream());
		oos.flush();


	}

	public void setProgressValue(int value){
		this.setProgress(value);
	}

	public void setRunning(boolean running){
		this.running = running;
	}
	public boolean isRunning() {
		return running;
	}

	public long getTotalBytes() {
		return totalBytes;
	}

	public void setTotalBytes(long totalBytes) {
		this.totalBytes = totalBytes;
	}

	public ObjectOutputStream getOutput()
	{
		return oos;
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
		start();
		return null;
	}

	public void start() {
		reply = acceptConnection();
		try {
			totalBytes = record.getFiles().stream().mapToLong(file -> FileUtils.fileLength(file)).sum();
			getOutput().writeLong(totalBytes); // send total bytes to send
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendFiles();
	}


	public void stopServer()
	{
		try {
			server.close();
			running = false;
			closeConnections();
		} catch (IOException e) {
			closeConnections();
			e.printStackTrace();
		}


	}


}
