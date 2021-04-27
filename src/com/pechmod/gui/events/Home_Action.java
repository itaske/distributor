package com.pechmod.gui.events;
import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import com.pechmod.gui.*;
import com.pechmod.gui.Frame;
import com.pechmod.connection.Server;
import com.pechmod.file.Record;
import com.pechmod.main.Test;

public class Home_Action extends AbstractAction{

	private JPanel frame;
	private Record record;
	JLabel label;
	public Home_Action(JPanel frame,Record record,JLabel label)
	{
		this.frame=frame;
		this.record=record;
		this.label=label;
		this.putValue(Action.NAME, "Home");
		this.putValue(Action.SHORT_DESCRIPTION,"Go to Home");
		this.putValue(Action.MNEMONIC_KEY,new Integer('H'));
		this.putValue(Action.SMALL_ICON, new ImageIcon(Frame.class.getResource("events/Images/post-office-icon(1).png")));	
	}
	
	
	
	public Home_Action(JPanel frame,JLabel label)
	{
		this.frame=frame;
		this.label=label;
		this.putValue(Action.NAME, "Home");
		this.putValue(Action.SHORT_DESCRIPTION,"Go to Home");
		this.putValue(Action.MNEMONIC_KEY,new Integer('H'));
		this.putValue(Action.SMALL_ICON, new ImageIcon(Frame.class.getResource("events/Images/post-office-icon(1).png")));	
	}
	
	
	public Home_Action(JPanel frame)
	{
		this.frame=frame;
		
		this.putValue(Action.NAME, "Home");
		this.putValue(Action.SHORT_DESCRIPTION,"Go to Home");
		this.putValue(Action.MNEMONIC_KEY,new Integer('H'));
		this.putValue(Action.SMALL_ICON, new ImageIcon(Frame.class.getResource("events/Images/post-office-icon(1).png")));	
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		CardLayout c=(CardLayout)frame.getLayout();
		
		c.show(frame, "Home");
		
		if(record!=null && record.getSize()>0 )
			record.clear();
		
		if(label!=null)
			label.setText("Connection: OFF");
		}

	
}
