package com.pechmod.protocol;
import java.io.Serializable;
import java.util.LinkedList;

public class ClientReply implements Serializable{
	
	private LinkedList<Integer>fileNumbers;
	private String destination;
	
	public void setFileNumbers(LinkedList<Integer>list)
	{
		fileNumbers=list;
	}
	
	public LinkedList<Integer> getFileNumbers()
	{
		return fileNumbers;
	}
	
	public void setDestination (String fileDestination)
	{
		destination= fileDestination;
	}
	
	public String getDestination()
	{
		return destination;
	}
	

}
