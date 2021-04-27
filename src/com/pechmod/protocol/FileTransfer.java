package com.pechmod.protocol;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;
import javax.swing.JOptionPane;


public class FileTransfer {

	private LinkedList<ModalFile> list;


	public FileTransfer()
	{
		list=new LinkedList<ModalFile>();
	}

	public LinkedList<ModalFile> makeModalList(File source,File destination)
	{
		ModalFile mode;

		if(source.isDirectory())
		{
			String files[]=source.list();
			for(int i=0; i<files.length; i++)
			{
				File sou=new File(source,files[i]);
				File des=new File(destination,sou.getName());
				makeModalList(sou,des);
			}
		}
		else
		{
			File sou=source;
			File des=new File(destination,sou.getName());
			mode=buildModalFile(source,des);
			list.add(mode);
		}

		return list;
	}




	public ModalFile buildModalFile(File  sourceFile,File destinationFile)
	{
		ModalFile modalFile=new ModalFile();
		setModalFile(modalFile,sourceFile,destinationFile);




		return modalFile;

	}


	public void setModalFile(ModalFile modalFile,File  sourceFile,File destinationFile)
	{
		
		
		
		/*if(remainder==0)
		{
		modalFile.setStatus("Finished");	
			return;
		}*/



		modalFile.setSource(sourceFile);

		if(!destinationFile.exists()&& destinationFile.isDirectory())
			if(!destinationFile.mkdirs())
			{
				JOptionPane.showMessageDialog(null, "Error with creating Directory","Error creating Folder",JOptionPane.ERROR_MESSAGE);
				return ;
			}
		modalFile.setDestination(destinationFile.getAbsolutePath());

		//modalFile.setRemainder(remainder);

		modalFile.setLengthOfSourceFile((int)sourceFile.length());

		//	modalFile.setFileData(getMappedByteBuffer(sourceFile));

		modalFile.setStatus("Sending");

	}





	public MappedByteBuffer getMappedByteBuffer(File mapFile)

	{
		FileInputStream fis=null;
		MappedByteBuffer buffer=null;
		try {
			fis=new FileInputStream(mapFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File not Found","Error Finding File",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}

		FileChannel inChannel=fis.getChannel();

		try {
			buffer=inChannel.map(FileChannel.MapMode.READ_ONLY,0,mapFile.length());

			buffer.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}

		return buffer;


	}


}
