package com.pechmod.file.fileSelection;

import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

import javax.swing.event.*;
import javax.swing.*;

import com.pechmod.file.Record;

public class DropTargetHandler implements DropTargetListener{
	
	
	Record record;
	public DropTargetHandler(JPanel panel,Record record)
	{
		setRecord(record);
		
		panel.setDropTarget(new DropTarget(panel,DnDConstants.ACTION_COPY,this));
	}

	@Override
	public void dragEnter(DropTargetDragEvent e) {
		
		if(e.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
			e.acceptDrag(DnDConstants.ACTION_COPY);
		else
			e.rejectDrag();
	}

	@Override
	public void dragExit(DropTargetEvent e) {
		
		
	}

	@Override
	public void dragOver(DropTargetDragEvent e) {
		
		
	}

	@Override
	public void drop(DropTargetDropEvent e) {
		Transferable transferable=e.getTransferable();
		
		if(transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
		{
			e.acceptDrop(DnDConstants.ACTION_COPY);
			
			try{
				
				List fileList=(List)transferable.getTransferData(DataFlavor.javaFileListFlavor);
				
				Iterator iterator=fileList.iterator();
				
				while(iterator.hasNext())
				{
					File file1=(File)iterator.next();
					if(file1==null)
						return ;
					record.addFile(file1);
				}
				
				e.dropComplete(true);
			}
			
			catch(UnsupportedFlavorException flavorException)
			{
				flavorException.printStackTrace();
				e.dropComplete(false);
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
				e.dropComplete(false);
			}
		}
		
		else
			e.rejectDrop();
		
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent e) {
		
		
	}
	
	public void setRecord(Record record)
	{
		this.record=record;
	}
	
	public Record getRecord()
	{
		return record;
	}

}
