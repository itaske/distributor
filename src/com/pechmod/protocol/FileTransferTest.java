package com.pechmod.protocol;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.LinkedList;

import com.pechmod.protocol.ModalFile;

public class FileTransferTest {

	public static void main(String[] args) {
		
		FileTransfer f=new FileTransfer();
		
		File file=new File("C:/Users/USER/Documents/Downloads/Desktop/new videos/Gods.Not.Dead.2.2016.HDRip.XviD.AC3-EVO.avi");
		File file1 =new File("C:/Users/USER/Documents/Downloads/Desktop/Distributor/Test/Distributor Test/src/new1.avi");
		
	
		LinkedList<ModalFile>list=f.makeModalList(file,file1);
		
		for(int i=0; i<list.size();i++)
		{		System.out.println(list.get(i).getDestination());
		
		
		}

	}

}
