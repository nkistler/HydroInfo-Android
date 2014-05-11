package com.example.hydroinfo_android;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserObject implements Serializable
{
	private int id;
	private String userName;
	private String displayName;
	private String password;
	private String title;
	public UserObject(int userId, String userUserName, String userDisplayName, String userPassword, String userTitle)
	{
		this.id = userId;
		this.userName = userUserName;
		this.displayName = userDisplayName;
		this.password = userPassword;
		this.title = userTitle;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getDisplayName()
	{
		return displayName;
	}
	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
}
