package com.pechmod.protocol;
import java.io.*;
import java.util.*;


public class ChuckingFiles {

	
	
	public static  LinkedList splitFile(File f)throws IOException
	{
		
		
		LinkedList listB=new LinkedList();
		
		
		
		
		try
		
			(BufferedInputStream bis=new BufferedInputStream(new FileInputStream(f)))
			{
				
				int tmp=0;
				
				int size=1024*1024;
				
				if(bis.available()<size)
					size=bis.available();
				byte buffer[]=new byte[size];
				while((tmp=bis.read(buffer,0,size))>0)
				{
					
					
					
						listB.add(buffer);
						
						
						
						if(bis.available()<size)
							size=bis.available();
						buffer=getByte(size);
						
						
					}
					
					
					
				}
		return listB;
			
			
		
		
	}
	
	
	public static  void mergeFiles(LinkedList list,File into) throws IOException
	{
		BufferedOutputStream bos=null;
		
			
					
					
					try
					
					{
						bos=new BufferedOutputStream(new FileOutputStream(into,true));
						for(int i=0; i<list.size(); i++)
						{
						
					
					byte buffer[]=(byte[]) list.get(i);
						
								
							System.out.println("writing "+i);	
								bos.write(buffer);
							
								
								
								
							}
						}
					catch(FileNotFoundException e)
					{
						
							File  folder=new File(into.getParent());
							if(folder.mkdirs())
							{System.out.println("Successful creating folders");
							mergeFiles(list,into);}
						else
							e.printStackTrace();
					}
					catch(IOException io)
					{
						
						io.printStackTrace();
					}
				
				finally{		
				if(bos!=null)
					{bos.flush();
					bos.close();
					}
				}
		
				}
	
	
	public static byte[] getByte(int size)
	{
		byte bytes[]=new byte[size];
		return bytes;
	
	}
				
	
	
	
	
		}
		
	
	


