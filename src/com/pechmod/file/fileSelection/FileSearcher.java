package com.pechmod.file.fileSelection;

import java.io.*;

import javax.swing.*;

import java.awt.*;

public class FileSearcher extends JFrame {

	
	public FileSearcher()
	{
		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public File getFileOrDirectory()
	{
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		int result=fileChooser.showOpenDialog(this);
				
				if(result==fileChooser.CANCEL_OPTION)
					return null;
		File file=fileChooser.getSelectedFile();
		
		
		if(file.getName()==null || file.getName().equalsIgnoreCase(""))
			JOptionPane.showMessageDialog(this, "Invalid File Name", "Error in File Name",JOptionPane.ERROR_MESSAGE);
		
		return file;
	}
	
	
	
	public File getDirectory()
	{
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int result=fileChooser.showOpenDialog(this);
		
		if(result==fileChooser.CANCEL_OPTION)
			return null;
		
		File file=fileChooser.getSelectedFile();
		
		return file;
	}
	
	
}
