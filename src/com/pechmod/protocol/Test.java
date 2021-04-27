package com.pechmod.protocol;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Logger;

public class Test {
	

	public static void main(String[] args) throws IOException {
		
		File video=new File("C:/Users/USER/Documents/Downloads/Desktop/Gods.Not.Dead.2.2016.HDRip.XviD.AC3-EVO.avi");
  FileInputStream fis=new FileInputStream(video);
  
  ByteArrayOutputStream bos=new ByteArrayOutputStream();
  
  byte buf[]=new byte[1024];
  
  try
  {
	  for(int num; (num=fis.read(buf))!=-1;)
	  {
		  bos.write(buf,0, num);
		  System.out.println("read "+num+" bytes");
		  
	  }
	  
  }
  catch(IOException io)
  {
	 io.printStackTrace();
  }
  byte bytes[]=bos.toByteArray();
  
  File someFile = new File("java.avi");
  FileOutputStream fos = new FileOutputStream(someFile);
  fos.write(bytes);
  fos.flush();
  fos.close();
  
  }
	  
	
  
	}



