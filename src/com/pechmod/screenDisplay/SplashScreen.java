package com.pechmod.screenDisplay;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JWindow;
import javax.swing.Timer;

public class SplashScreen {

	
	private JWindow window;
	private Timer timer;
	
	public SplashScreen(Component component)
	{
		window=new JWindow();
		
		window.getContentPane().add(component);
		
		window.addMouseListener(
				
				 new MouseAdapter() {
				
				 // when user presses mouse in SplashScreen,
				 // hide and dispose JWindow
				 public void mousePressed( MouseEvent event ) {
				 window.setVisible( false );
				 window.dispose();
				 }
				 }
				
				 ); // end call to addMouseListener
				
		// size JWindow for given Component
		 window.pack();
		 // get user's screen size
		 Dimension screenDimension =
		 Toolkit.getDefaultToolkit().getScreenSize();
		
		 // calculate x and y coordinates to center splash screen
		 int width = window.getSize().width;
		 int height = window.getSize().height;
		 int x = ( screenDimension.width - width ) / 2 ;
				
		 
		 int y = ( screenDimension.height - height ) / 2 ;
		 
		  // set the bounds of the window to center it on screen
		  window.setBounds( x, y, width, height );
		 
		  } // end SplashScreen constructor
		 
		  // show splash screen for given delay
		  public void showSplash( int delay ) {
		 
		  // display the window
		  window.setVisible( true );
		 
		  // crate and start a new Timer to remove SplashScreen
		  // after specified delay
		  timer = new Timer( delay,
		  new ActionListener() {
		 
		  public void actionPerformed( ActionEvent event )
		  {
		  // hide and dispose of window
		  window.setVisible( false );
		  window.dispose();
		  timer.stop();
		  }
		  }
		 );
		 		  timer.start();
		 
		  } // end method showSplash
		 
		  // return true if SplashScreen window is visible
		  public boolean isVisible()
		  {
		  return window.isVisible();
		  }
		 
		
	
}
