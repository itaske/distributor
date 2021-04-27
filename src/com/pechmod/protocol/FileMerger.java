package com.pechmod.protocol;

import com.pechmod.connection.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.SwingWorker;

public class FileMerger{

	private LinkedList<ModalFile>modal;
	static int counter;

	public FileMerger(LinkedList<ModalFile>modal)
	{
		this.modal=modal;
		counter=0;
	}

	public Object execute(Client client) {
		int currentBytes = 0;
		for(int i=0; i<modal.size(); i++) {
			File into = new File(modal.get(i).getDestination());
			System.out.println(modal.get(i).getDestination());
			try {
				if (into.isFile() && into.exists()) {

					into.renameTo(new File(into.getParent() + File.separator + "1 " + into.getName()));
				}
				ChuckingFiles.mergeFiles(modal.get(i).getFileData(), into);
				currentBytes += modal.get(i).getLengthOfSourceFile();
				double percentReceived = ((double)currentBytes/client.getTotalBytesToReceive())*100;
				int percentInt = (int)percentReceived;
				client.setProgressValue(percentInt);
			} catch (IOException io) {
				io.printStackTrace();
			}
		}

		// TODO Auto-generated method stub
		return null;
	}

}
		

