package com.pechmod.gui;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;

import com.pechmod.file.fileSelection.FileSearcher;
import com.pechmod.utils.User;

import java.awt.event.*;
import java.io.File;
import java.util.Locale;

public class Setting extends JPanel implements ActionListener{
	
	private JButton save, back,uploadDirectory;
	private User user;
	private JLabel nameLabel,picDesLabel,localeLabel;
    private JTextField nameField,picDesField,fileDesField;
    private Locale locales[]={Locale.US,Locale.UK,Locale.FRANCE,Locale.GERMANY};
    private String locale[]={"US","UK","FRANCE","GERMANY"};
    private JPanel activePanel,dormantPanel;
    private String name;
    
    private File file;
    
    private JLabel userLabel;
    
    private JComboBox localeBox;
    
    private JPanel panel;
	
	
	public Setting(User user,JPanel panel)
	{
		
		this.user=user;
		this.panel=panel;
		
		name=user.getUserName();
		file=user.getFileDestination();
		
		
		
		setLayout(null);
		setSize(340,580);
		activePanel=new JPanel(null);
		
		dormantPanel=new JPanel(null);
		dormantPanel.setLocation(0,0);
		dormantPanel.setSize(340,200);
		dormantPanel.setBackground(Color.blue);
		
		userLabel=new JLabel();
		userLabel.setText(user.getUserName());
		userLabel.setSize(200,50);
		userLabel.setLocation(30,30);
		userLabel.setForeground(Color.white);
		userLabel.setBackground(Color.white);
		userLabel.setFont(new Font("Times new Roman", Font.PLAIN,30));
		
		
		activePanel.setLocation(0,200);
		activePanel.setSize(340,380);
		
		
		//JLabel
		nameLabel=new JLabel("User Name");
		nameLabel.setLocation(10,10);
		nameLabel.setSize(100,30);
		
		picDesLabel =new JLabel("Picture Directory");
		//fileDesLabel =new JLabel("File Destination Folder");
		localeLabel=new JLabel("Choose Locale");
		localeLabel.setLocation(10,80);
		localeLabel.setSize(100,30);
		
		//JTextField 
		nameField=new JTextField(100);
		nameField.setLocation(90,10);
		nameField.setSize(200, 30);
		nameField.setText(user.getUserName());
		
		
		picDesField=new JTextField(50);
		
		
		fileDesField=new JTextField(100);
		fileDesField.setEditable(false);
		fileDesField.setLocation(5,150);
		fileDesField.setSize(190,30);
		fileDesField.setText(file.getPath());
		fileDesField.setToolTipText(user.getFileDestination().getAbsolutePath());
		
		
		
		
		//JButton
		
		uploadDirectory=new JButton("Choose Folder");
		uploadDirectory.setToolTipText("Choose folder for files destination");
		uploadDirectory.setLocation(200,150);
		uploadDirectory.setSize(120,30);
		
		uploadDirectory.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				FileSearcher searcher=new FileSearcher();
				 file=searcher.getDirectory();
			    fileDesField.setText(file.getAbsolutePath());
			}
		});
		
		
		back=new JButton("Back",new ImageIcon("C:/Users/USER/Documents/Downloads/Desktop/Distributor/Test/Distributor Test/src/com/pechmod/gui/events/Images/back.gif"));
		 back.setHorizontalTextPosition(SwingConstants.RIGHT);
		 back.setIconTextGap(5);
		back.setLocation(10,250);
		back.setSize(100,30);
		
		
		save=new JButton("Save Changes");
		save.setLocation(200, 250);
		save.setSize(120,30);
		
		//ComboBox
		localeBox=new JComboBox(locales);
		localeBox.setLocation(120,80);
		localeBox.setSize(200,30);
		localeBox.setToolTipText("Select Language of Your Choice");
		
		back.addActionListener(this);
		save.addActionListener(this);
		
	
		
		activePanel.add(nameLabel);
		activePanel.add(nameField);
		activePanel.add(localeLabel);
		activePanel.add(localeBox);
		activePanel.add(fileDesField);
		activePanel.add(uploadDirectory);
		activePanel.add(save);
		activePanel.add(back);
		
		
		dormantPanel.add(userLabel);
		
		add(dormantPanel);
		add(activePanel);
	}
	

	
	public void actionPerformed(ActionEvent e )
	{
		if(e.getSource()==save)
		{
			user.saveFile(nameField.getText(), picDesField.getText(), file, locales[localeBox.getSelectedIndex()]);
			userLabel.setText(user.getUserName());
			fileDesField.setText(file.getAbsolutePath());
			
		}
		else if(e.getSource()==back)
		{
			CardLayout card=(CardLayout)panel.getLayout();
			
			card.show(panel,"Home");
		}
		
	}

	
	
}
