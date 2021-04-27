package com.pechmod.file;
import java.nio.*;
import java.nio.channels.*;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.*;
import java.io.*;

public class FileTransfer {

	public static void main(String[] args) throws IOException {
		
		
		
		
		int len = 0;
				
		
			File file=new File("C:/Users/USER/Documents/Downloads/Desktop/Apress - Beginning JSF 2APIs and JBoss Seam.pdf");
			
			FileInputStream fis=new FileInputStream(file);
			
			File file1=new File("C:/Users/USER/Documents/Downloads/Desktop/Distributor/copy.pdf");
			RandomAccessFile fos=new RandomAccessFile(file1,"rw");
			len=(int)file.length();
		
			System.out.println("Starting");

		
		FileChannel in=fis.getChannel();
		FileChannel out=fos.getChannel();
		
		MappedByteBuffer bufferIn=in.map(FileChannel.MapMode.READ_ONLY, 0, fis.available());		
		MappedByteBuffer bufferOut=out.map(FileChannel.MapMode.READ_WRITE, 0, fis.available());
		
		
		bufferIn.load();
		
		for(int i=0; i<bufferIn.limit(); i++)
		{
			
			bufferOut.put(bufferIn.get());
		}
			
	bufferIn.clear();
	bufferOut.clear();
	fos.close();
	fis.close();
	
	System.out.println("finished writing");
	}
}
