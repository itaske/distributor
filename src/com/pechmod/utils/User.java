package com.pechmod.utils;
import java.io.*;
import java.util.*;

public class User extends Observable {

	private File fileDestination, userDetails ;
	private String userName, userPicDestination;
	//private Icon userPic;
	private Properties properties;
    private Collection<Locale> locales;
    private Locale currentLocale;
	
	public User()
	{
		userDetails=new File(System.getProperty("user.home")+"/Distributor/User Settings","Details.dat");
		properties=new Properties();
		
		locales=new LinkedList<>();
		locales.add(Locale.US);
		locales.add(Locale.CHINA);
		locales.add(Locale.FRANCE);
		locales.add(Locale.UK);
		locales.add(Locale.JAPAN);
		
		
		
		if(!userDetails.exists())
		{
			setCurrentLocale("en","GB");
			setUserName("User");
			//setUserPicDestination(System.getProperty("user.home"));

			
			setFileDestination(new File(System.getProperty("user.home"),"Distributor"));
			
		}
		else
			loadFile(properties);
	}

	
	public void setUserName(String name)
	{
		if(name!=""&&name!=null)
		{
			userName=name;
			properties.setProperty("Name",userName);
		}
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public void setFileDestination(File fileDes)
	{
		
		if(fileDes.exists())
		{
			fileDestination=fileDes;
			properties.setProperty("File Destination",fileDestination.getAbsolutePath() );
		}
		else
		{
			if(fileDes.mkdirs())
			{
				fileDestination=fileDes;
				properties.setProperty("File Destination",fileDestination.getAbsolutePath() );
			}
		}
	}
	
	
	public File getFileDestination()
	{
		return fileDestination;
	}
	
	
	public void setUserDetails(File details)
	{
		if(details.exists())
			{
			  userDetails=details;
			  
			}
	}
	
	
	public File getUserDetails()
	{
		return userDetails;
	}
	
	
	public void setUserPicDestination(String userPicDir)
	{
		File file=new File(userPicDir);
		if(file.exists())
			{
			    userPicDestination=userPicDir;
			    properties.setProperty("Picture Destination", userPicDestination);
			}
	}
	
	
	public String getUserPicDestination()
	{
		return userPicDestination;
	}
	
	public Properties getProperties()
	{
		return properties;
	}
	
	public void loadFile(Properties prop)
	{
		
		try
		{
			FileInputStream fis=new FileInputStream(new File(System.getProperty("user.home")+"/Distributor/User Settings","Details.dat"));
			
			prop.load(fis);
			userName=(String)prop.getProperty("Name");
			//userPicDestination=(String )prop.getProperty("Picture Destination");
			//userPic=new ImageIcon(getClass().getResource(userPicDestination));
			fileDestination=new File(prop.getProperty("File Destination"));
			
			String language,country;
			language=prop.getProperty("Current Locale-Language");
			country=prop.getProperty("Current Locale-Country");
			
			currentLocale=new Locale(language,country);
		}
		catch(IOException io)
		{
			io.printStackTrace();
		}
		
		}
		
	public void saveFile(String name,String picDes,File fileDes,Locale locale)
	{
		boolean save=false;
		
	do{
		try
		{
			
	        	FileOutputStream fos=new FileOutputStream(System.getProperty("user.home")+"/Distributor/User Settings/Details.dat");
		        setUserName(name);
		        //setUserPicDestination(picDes);
		        setFileDestination(fileDes);
		        setCurrentLocale(locale.getLanguage(),locale.getCountry());
		        properties.store(fos, "Distributor User Details");
		        fos.close();
		        
		        setChanged();
		        notifyObservers();
			save=true;
			
		}
		catch(FileNotFoundException io)
		{
			File file=new File(System.getProperty("user.home")+"/Distributor/User Settings");
			if(file.mkdirs())
				System.out.println("path available");
		}
		catch(IOException io)
		{
			io.printStackTrace();
		}
	}
	while(save==false);
		
	}//end save File
	
	
	public Collection<Locale> getLocales()
	{
		return locales;
	}
	
	public void addLocale(Locale locale)
	{
		locales.add(locale);
	}
	
	
	public int getLocaleSize()
	{
		return locales.size();
	}
	
	public void setCurrentLocale(String language,String country)
	{
		Locale locale=new Locale(language,country);
		currentLocale=locale;
		properties.setProperty("Current Locale-Language",language);
		properties.setProperty("Current Locale-Country",country);
	}
	
	public Locale getCurrentLocale()
	{
		return currentLocale;
	}
	
	
}
