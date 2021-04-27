package com.pechmod.main;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.util.Locale;



import com.pechmod.screenDisplay.SplashScreen;
import com.pechmod.file.Record;
import com.pechmod.gui.Home_panel;
import com.pechmod.gui.Setting;
import com.pechmod.gui.broadcast.BroadcastingPanel;
import com.pechmod.gui.events.*;
import com.pechmod.gui.receive.ReceivePanel;
import com.pechmod.gui.receiveBroadcast.ReceiveBroadcastPanel;
import com.pechmod.gui.send.SendPanel;
import com.pechmod.utils.User;

public class Test extends JFrame{


	private static final long serialVersionUID = 1L;

	private User user;
	private JPanel mainPanel;


	private JButton setting,home,history;
	private JMenuBar bar;
	private JMenu system;
	private JMenuItem homeMenu,settingMenu,exit;
	private JToolBar toolBar;
	private Setting_Action settingAction;
	private Home_Action homeAction;
	private Exit_Action exitAction;

	private Home_panel homePanel;
	private Setting settingPanel;
	private SendPanel sendPanel;
	private ReceivePanel receivePanel;


	private BroadcastingPanel broadcastingPanel;
	private ReceiveBroadcastPanel receiveBroadcastPanel;


	private Record broadcastRecord,sendRecord;

	private SplashScreen splashScreen;


	public Test()
	{
		super("JFrame");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		setLayout(new BorderLayout());
		mainPanel=new JPanel(new CardLayout());
		user=new User();



		setting=new JButton();
		home=new JButton();
		history=new JButton();
		toolBar=new JToolBar();

		bar=new JMenuBar();
		system=new JMenu("System");
		homeMenu=new JMenuItem();
		settingMenu=new JMenuItem();
		exit=new JMenuItem();


		setJMenuBar(bar);
		bar.add(system);
		system.add(homeMenu);
		system.add(settingMenu);
		system.addSeparator();
		system.add(exit);


		homeAction=new Home_Action(mainPanel);
		homeMenu.setAction(homeAction);
		home.setAction(homeAction);

		settingAction=new Setting_Action(mainPanel);
		settingMenu.setAction(settingAction);
		setting.setAction(settingAction);

		exitAction=new Exit_Action();
		exit.setAction(exitAction);


		toolBar.add(home);
		toolBar.add(setting);
		toolBar.add(history);

		homePanel=new Home_panel(user,mainPanel,"Send");
		settingPanel=new Setting(user,mainPanel);

		sendRecord=new Record(Locale.US,2);
		sendPanel=new SendPanel(mainPanel,sendRecord);


		receivePanel=new ReceivePanel(mainPanel);

		broadcastRecord=new Record(Locale.US,2);
		broadcastingPanel=new BroadcastingPanel(broadcastRecord,mainPanel,"Home");



		receiveBroadcastPanel =new ReceiveBroadcastPanel(mainPanel);



		mainPanel.add(homePanel,"Home");
		mainPanel.add(sendPanel,"Send");
		mainPanel.add(receivePanel,"Receive");
		mainPanel.add(settingPanel,"Setting");
		mainPanel.add(broadcastingPanel,"Broadcasting");
		mainPanel.add(receiveBroadcastPanel,"Receive Broadcast");


		getContentPane().add(toolBar,BorderLayout.PAGE_START);
		getContentPane().add(mainPanel,BorderLayout.CENTER);



		setSize(340,580);
		centerWindowOnScreen();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}


	private void centerWindowOnScreen()
	{
		Dimension screenDimension =
				Toolkit.getDefaultToolkit().getScreenSize();

		// use screen width and height and application width
		// and height to center application on user's screen
		int width = getSize().width;
		int height = getSize().height;
		int x = ( screenDimension.width - width ) / 2 ;
		int y = ( screenDimension.height - height ) / 2 ;

		// place application window at screen's center
		setBounds( x, y, width, height );
	}


	public static void main(String[] args) {

		SwingUtilities.invokeLater(()->{
			Test test=new Test();
			test.setResizable(false);
		});

	}

}
