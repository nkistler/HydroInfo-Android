package com.example.hydroinfo_android;


import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class AssociatedUserDataObject implements Serializable
{
	private UserObject user;
	private ArrayList<SensorObject> nodeList;
	
	public AssociatedUserDataObject(UserObject newUser)
	{
		//initialize objects
		this.user = newUser;
		this.nodeList = new ArrayList<SensorObject>();
	}
	public UserObject getUser()
	{
		return user;
	}
	public void setUser(UserObject user)
	{
		this.user = user;
	}
	public ArrayList<SensorObject> getNodeList()
	{
		return nodeList;
	}
	public void setNodeList(ArrayList<SensorObject> nodeList)
	{
		this.nodeList = nodeList;
	}
}
