package com.pechmod.gui.receiveBroadcast;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ProgressMonitor;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.pechmod.connection.Client;
import com.pechmod.file.ReceiverRecord;
import com.pechmod.file.StatusRecord;
import com.pechmod.gui.Frame;
import com.pechmod.protocol.ClientReply;
import com.pechmod.protocol.ModalFile;
import com.pechmod.protocol.FileMerger;
import com.pechmod.utils.User;

public class ReceiveBroadcastPanel extends JPanel implements ActionListener{
	JButton back,receive,receiveFiles;
	JLabel label;
	User user;

	JScrollPane scroll;

	JPanel bottomPanel;

	JPanel panel;

	JTable table;


	LinkedList<String> recordList;

	ReceiverRecord record;
	StatusRecord statusRecord;


	Client client;


	public ReceiveBroadcastPanel(JPanel panel)
	{

		setSize(350,550);
		setLayout(new BorderLayout());
		this.panel=panel;

		statusRecord=new StatusRecord();
		client = new Client(statusRecord);



		back=new JButton("Back",new ImageIcon(Frame.class.getResource("events/Images/back.gif")));
		back.setHorizontalTextPosition(SwingConstants.RIGHT);
		back.setIconTextGap(5);


		receive=new JButton("Receive");
		receiveFiles=new JButton("Receive Files");
		receiveFiles.setEnabled(false);

		record=new ReceiverRecord(Locale.UK);
		table=record.getTable();
		scroll=new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		table.setShowGrid(false);
		table.setShowHorizontalLines(true);
		table.setRowHeight(50);
		label=new JLabel("Connection: Off");
		user=new User();




		bottomPanel=new JPanel(new BorderLayout());


		bottomPanel.add(back,BorderLayout.WEST);
		bottomPanel.add(label,BorderLayout.NORTH);
		bottomPanel.add(receive,BorderLayout.EAST);


		add(bottomPanel,BorderLayout.PAGE_END);
		add(scroll,BorderLayout.PAGE_START);
		add(receiveFiles, BorderLayout.CENTER);

		back.addActionListener(this);
		receive.addActionListener(this);
		receiveFiles.addActionListener(this);

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub


		if(e.getSource()==back)
		{
			client.closeConnections();
			label.setText("Connection: OFF");
			recordList=null;

			if(record.getSize()>0)
				record.clear();

			CardLayout c = (CardLayout) panel.getLayout();
			c.show(panel,"Home");


		}
		else  if (e.getSource()==receive) {


			String host=null;
			host=JOptionPane.showInputDialog(null, "Please name of the host");
			if(host==null)
				return ;

			client.connectTo(host,2222);
			try {
				System.out.println("Connected");

				label.setText("Connection: ON");

				recordList = (LinkedList<String>) client.getInput().readObject();

				record.addFiles(recordList);
				receiveFiles.setEnabled(true);


			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		else if(e.getSource()==receiveFiles)
		{
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			ProgressMonitor pbar=new ProgressMonitor(this,"Monitoring Progress ", "Initializing...",0,100);
			pbar.setMillisToDecideToPopup(10);
			pbar.setMillisToPopup(10);
			UIManager.put("ProgressMonitor.progressText", "Receiving");



			LinkedList <Integer> list=new LinkedList<Integer>();

			int col=1;
			int rows=record.getSize();

			for(int i=0; i<rows; i++)
			{
				if(record.isSelected(i, col))
				{
					list.add(i);

				}
			}


			if(list.size()<1)
			{
				JOptionPane.showMessageDialog(null,"Choose a File to Receive", "File to Receive not Selected",JOptionPane.ERROR_MESSAGE);
				this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return ;
			}


			try {
				ClientReply reply= new ClientReply();
				reply.setDestination(user.getFileDestination().getAbsolutePath()+File.separator+"Distributor"+File.separator);
				reply.setFileNumbers(list);

				client.getOutput().writeObject(reply);

			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			LinkedList<LinkedList<ModalFile>> filesReceive = null;
			do
			{
				try
				{
					filesReceive = (LinkedList<LinkedList<ModalFile>>)client.getInput().readObject();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			while(filesReceive==null);


			for(int i=0; i<filesReceive.size(); i++)
			{

				if(filesReceive!=null)
				{
					FileMerger fileMerger=new FileMerger(filesReceive.get(i));

//					progressMonitor.addPropertyChangeListener(new PropertyChangeListener()
//					{
//						public void propertyChange(PropertyChangeEvent e)
//						{
//							if(e.getPropertyName().equals("progress"))
//							{
//								int newValue=(Integer)e.getNewValue();
//								System.out.println(newValue);
//								pbar.setProgress(newValue);
//								pbar.setNote("Operation is "+newValue+ "% complete");
//							}
//
//						}
//					});


					fileMerger.execute(client);

					if(pbar.isCanceled())
					{
						pbar.close();
						return ;
					}
				}






			}//close for loop

			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		}//close else if for receive Files

	}//close action performed

}//close class