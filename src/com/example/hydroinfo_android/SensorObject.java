package com.example.hydroinfo_android;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SensorObject implements Serializable
{
	private int id;
	private String latitude;
	private String longitude;
	private ArrayList<MeasurementObject> measurementList;
	
	public SensorObject()
	{
		this.id = -1;
		this.latitude = null;
		this.longitude = null;
		this.measurementList = new ArrayList<MeasurementObject>();
	}
	
	public SensorObject(int sensorId, String sensorLatitude, String sensorLongitude)
	{
		this.id = sensorId;
		this.latitude = sensorLatitude;
		this.longitude = sensorLongitude;
		this.measurementList = new ArrayList<MeasurementObject>();
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getLatitude()
	{
		return latitude;
	}
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	public String getLongitude()
	{
		return longitude;
	}
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}
	public ArrayList<MeasurementObject> getMeasurementList()
	{
		return measurementList;
	}
	public void setMeasurementList(ArrayList<MeasurementObject> measurementList)
	{
		this.measurementList = measurementList;
	}
	
}
