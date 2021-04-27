package com.pechmod.protocol;

import java.io.*;
import java.nio.*;
import java.util.LinkedList;


public class ModalFile implements Serializable{

private long lengthOfSourceFile;
private String destination;
private File source;
private int remainder;
private String status;
private LinkedList data;
private String name;


public String getName()
{
	return name;
}

public void setName(String name)
{
	this.name=name;
	
}

public long getLengthOfSourceFile()
{
return lengthOfSourceFile;	
}

public void setLengthOfSourceFile(long length)
{
lengthOfSourceFile=length;	
}

public String getDestination()
{
	return destination;
	
}

public void setDestination(String des)
{
	destination=des;
}


public File getSource()
{
	return source;
}

public void setSource(File source)
{
	this.source=source;
}

public String getStatus()
{
	return status;
	}

public void setStatus(String status)
{
	this.status=status;
}


public int getRemainder()
{

	return remainder;
}

public void setRemainder(int remain)
{
	remainder=remain;
	
}

public LinkedList getFileData()
{
	return data;
	
}

public void setFileData(LinkedList data)
{
	this.data=new LinkedList(data);
}


}
