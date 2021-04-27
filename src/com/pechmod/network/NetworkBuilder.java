package com.pechmod.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;

public class NetworkBuilder {

	static Process process;
	
	String hostName;
	public static String createNetwork()
	{ StringBuilder reply=new StringBuilder();
		try {
			process=Runtime.getRuntime().exec("netsh wlan start hostednetwork");
			
			BufferedReader reader=new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String newLine;
			
			while((newLine=reader.readLine())!=null)
			{
				reply.append(newLine);
				reply.append("\n");
				
				
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return reply.toString();
	
	}
	
	
	public static String getHostName()
	{
		String newLine = null;
		try {
			process=Runtime.getRuntime().exec("whoami");
			
            BufferedReader reader=new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			
			newLine=reader.readLine();
			int slashPosition=newLine.lastIndexOf('\\');
			newLine=newLine.substring(0,slashPosition);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return newLine;
	}
	
	public String stopNetwork()
	{
		StringBuilder reply=new StringBuilder();
		try {
			process=Runtime.getRuntime().exec("netsh wlan stop hostednetwork");
			
			BufferedReader reader=new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String newLine;
			
			while((newLine=reader.readLine())!=null)
			{
				reply.append(newLine);
				reply.append("\n");
				
				
			}
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return reply.toString();
	
	}
	
	public static void  main (String args[])
	{
		createNetwork();
		System.out.println(getHostName());
	}
}
