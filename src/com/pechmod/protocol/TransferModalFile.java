package com.pechmod.protocol;
import com.pechmod.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


public class TransferModalFile {
	
	
	
	
	
		public static void makeModalList(LinkedList<ModalFile>list,File source,String destination)
		{
			
			ModalFile mode;
			
			if(source.isDirectory())
			{
				String files[]=source.list();
				for(int i=0; i<files.length; i++)
				{
					
					File sou=new File(source,files[i]);
					
					if(sou.isDirectory())
					makeModalList(list,sou,destination+File.separator+source.getName()+File.separator);
					else
						makeModalList(list,sou,destination+File.separator+source.getName()+File.separator);
				}
			}
			else
			{
				
				
				mode=buildModalFile(source,destination+File.separator+source.getName());
				
				list.add(mode);
				
			}
						
		}
		
		
		
		
		public static ModalFile buildModalFile(File  sourceFile,String destinationFile)
		{
			ModalFile modalFile=new ModalFile();
			setModalFile(modalFile,sourceFile,destinationFile);
			
			
			
			
			return modalFile;
			
			} 
		
		
		public static void setModalFile(ModalFile modalFile,File  sourceFile,String destinationFile)
		{
			
			
			
			/*if(remainder==0)
			{
			modalFile.setStatus("Finished");	
				return;
			}*/
			
			
			
			modalFile.setSource(sourceFile);
			modalFile.setName(sourceFile.getName());
			
			modalFile.setDestination(destinationFile);
			
			//modalFile.setRemainder(remainder);
			
			modalFile.setLengthOfSourceFile(FileUtils.fileLength(sourceFile));
			
			try{
			modalFile.setFileData(ChuckingFiles.splitFile(sourceFile));
			}
			catch(IOException io)
			{
				io.printStackTrace();
			}
			
			modalFile.setStatus("Sending");
			
		
		
	}
		
		
		
	/*	public static void main(String[] args) {
			
			
			
			File file=new File("C:/Users/USER/Documents/Downloads/Desktop/new/CURRICULUM VITAE.docx");
			String file1="C:/Users/USER/Documents/Downloads/Desktop/Distributor/Test/Distributor Test/src/";
			
			LinkedList<ModalFile>list=new LinkedList<ModalFile>();
			makeModalList(list,file,file1);
			
			for(int i=0; i<list.size();i++)
			{
				System.out.println(list.get(i).getDestination());
			}
			
			
  setupFile(list);
		}*/


		public static void setupFile(LinkedList<ModalFile>modal)
		{
			
			for(int i=0; i<modal.size(); i++)
				
		   {
				
			File into=new File(modal.get(i).getDestination());
		System.out.println(modal.get(i).getDestination());
			
	
				try{
					if(into.isFile() && into.exists())
					{
						
						into.renameTo(new File(into.getParent()+File.separator+"1 "+into.getName()));
					}
				ChuckingFiles.mergeFiles(modal.get(i).getFileData(),into);
				
				}
				catch(IOException io)
		
				{
					io.printStackTrace();
				}
			
			
				
			
		   }
		}
			
		
		
}