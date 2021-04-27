package com.pechmod.gui.events;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import com.pechmod.gui.Frame;
import com.pechmod.main.Test;

public class Setting_Action extends AbstractAction{

	private JPanel frame;
	public Setting_Action(JPanel frame)
	{
		this.frame=frame;
		this.putValue(Action.NAME, "Setting");
		this.putValue(Action.SHORT_DESCRIPTION,"Apply Setting to Distributor");
		this.putValue(Action.MNEMONIC_KEY,new Integer('S'));
		this.putValue(Action.SMALL_ICON, new ImageIcon(Frame.class.getResource("events/Images/setting.png")));
		
		
		
	}
	public void actionPerformed(ActionEvent e) {
CardLayout c=(CardLayout)frame.getLayout();
		
		c.show(frame, "Setting");
		
	}

	
	
}
