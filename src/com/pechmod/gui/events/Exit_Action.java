package com.pechmod.gui.events;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import com.pechmod.gui.Frame;
import com.pechmod.main.Test;

public class Exit_Action extends AbstractAction{

	
	public Exit_Action()
	{
	
	
	this.putValue(Action.NAME, "Exit");
	this.putValue(Action.SHORT_DESCRIPTION,"Close Application");
	this.putValue(Action.MNEMONIC_KEY,new Integer('S'));
	this.putValue(Action.SMALL_ICON, new ImageIcon(Frame.class.getResource("events/Images/close.png")));
	
}


public void actionPerformed(ActionEvent e)
{
	System.exit(0);
	}

}