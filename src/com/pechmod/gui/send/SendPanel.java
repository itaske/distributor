package com.pechmod.gui.send;
import java.awt.*;

import javax.swing.*;

import com.pechmod.connection.Server;
import com.pechmod.file.Record;
import com.pechmod.file.fileSelection.DropTargetHandler;
import com.pechmod.file.fileSelection.FileSearcher;
import com.pechmod.gui.events.Home_Action;
import com.pechmod.main.Test;
import com.pechmod.network.NetworkBuilder;
import com.pechmod.utils.User;
import com.pechmod.gui.Frame;

import java.awt.event.*;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SendPanel extends JPanel implements ActionListener{

	JButton addFile,removeFile,back,send;
	JTable table;
	JLabel label;
	Record record;
	User user;

	JScrollPane scroll;

	JPanel topPanel,bottomPanel;

	JPanel panel;
	String to;

	DropTargetHandler dropHandler;

	Server server;

	ProgressMonitor progressMonitor;
	public SendPanel(JPanel panel,Record record)
	{

		setSize(350,550);
		setLayout(new BorderLayout());
		this.panel=panel;
		this.record=record;





		addFile=new JButton("Add File");
		removeFile=new JButton("Remove File");

		back=new JButton("Back",new ImageIcon(Frame.class.getResource("events/Images/back.gif")));
		back.setHorizontalTextPosition(SwingConstants.RIGHT);
		back.setIconTextGap(5);

		send=new JButton("Send");


		table= record.getTable();

		scroll=new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		table.setShowGrid(false);
		table.setShowHorizontalLines(true);
		table.setRowHeight(50);
		label=new JLabel("Connection: OFF");
		user=new User();




		topPanel=new JPanel();
		bottomPanel=new JPanel(new BorderLayout());

		topPanel.add(addFile);
		topPanel.add(removeFile);

		bottomPanel.add(back,BorderLayout.WEST);
		bottomPanel.add(label,BorderLayout.NORTH);
		bottomPanel.add(send,BorderLayout.EAST);

		add(topPanel,BorderLayout.PAGE_START);
		add(bottomPanel,BorderLayout.PAGE_END);
		add(scroll,BorderLayout.CENTER);

		dropHandler=new DropTargetHandler(this,this.record);

		back.addActionListener(this);
		send.addActionListener(this);
		addFile.addActionListener(this);
		removeFile.addActionListener(this);

		progressMonitor = new ProgressMonitor(SendPanel.this, "Sending Files", "Sending.....", 0, 100);


	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==back)
		{
			if(server!=null)
				server.stopServer();
			if (record.getSize() > 0) {
				int option = JOptionPane.showOptionDialog(null,
						"Do you want to clear Files Selected?",
						"Clear Cached File", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if (option == JOptionPane.YES_NO_OPTION)
					record.clear();
			}
			label.setText("Connection: OFF");
			if(!addFile.isEnabled())
			{
				addFile.setEnabled(true);
				removeFile.setEnabled(true);
			}

			CardLayout c = (CardLayout) panel.getLayout();
			c.show(panel,"Home");
		}


		else if(e.getSource()==send)
		{
			if(record.getSize()<1)
			{
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null,"Choose File or Files to Send", "No Files Selected",JOptionPane.ERROR_MESSAGE);
				return ;
			}


			server=new Server(record);
			server.addPropertyChangeListener((listener)->{
				if(listener.getPropertyName().equals("progress"))
				{
					int newValue=(Integer)listener.getNewValue();
					System.out.println("Inside Receive panel "+newValue);
					progressMonitor.setProgress(newValue);
					progressMonitor.setNote("Operation is "+newValue+ "% complete");
				}
			});
			server.execute();
			addFile.setEnabled(false);
			removeFile.setEnabled(false);



			label.setText("Host Name: "+NetworkBuilder.getHostName()+"       Connection: ON  ");
			addFile.setEnabled(false);
			removeFile.setEnabled(false);
		}
		else if(e.getSource()==addFile)
		{
			FileSearcher searcher=new FileSearcher();
			File file=searcher.getFileOrDirectory();
			record.addFile(file);
		}
		else if(e.getSource()==removeFile)
		{
			record.removeRow();
		}




	}


}
