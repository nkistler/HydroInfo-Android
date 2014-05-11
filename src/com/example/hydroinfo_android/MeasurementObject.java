package com.example.hydroinfo_android;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MeasurementObject implements Serializable
{
	private int id;
	private String timestamp;
	private double measurement1;
	private double measurement2;
	private double measurement3;
	private int temperature;
	private String weatherCondition;
	private double windSpeed;
	private String windDirection;
	private String humidity;
	private String precipitation;
	
	public MeasurementObject()
	{
		this.id = -1;
		this.timestamp = null;
		this.measurement1 = -1;
		this.measurement2 = -1;
		this.measurement3 = -1;
		this.temperature = -1;
		this.weatherCondition = null;
		this.windSpeed = -1;
		this.windDirection = null;
		this.humidity = null;
		this.precipitation = null;
	}
	public MeasurementObject(int measurementId, String measurementTimestamp, double m1, double m2, double m3, int measurementTemp, String measurementWeatherCondition, double measurementWindSpeed, String measurementWindDirection, String measurementHumidity, String measurementPrecipitation)
	{
		this.id = measurementId;
		this.timestamp = measurementTimestamp;
		this.measurement1 = m1;
		this.measurement2 = m2;
		this.measurement3 = m3;
		this.temperature = measurementTemp;
		this.weatherCondition = measurementWeatherCondition;
		this.windSpeed = measurementWindSpeed;
		this.windDirection = measurementWindDirection;
		this.humidity = measurementHumidity;
		this.precipitation = measurementPrecipitation;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getTimestamp()
	{
		return timestamp;
	}
	public void setTimestamp(String timestamp)
	{
		this.timestamp = timestamp;
	}
	public double getMeasurement1()
	{
		return measurement1;
	}
	public void setMeasurement1(double measurement1)
	{
		this.measurement1 = measurement1;
	}
	public double getMeasurement2()
	{
		return measurement2;
	}
	public void setMeasurement2(double measurement2)
	{
		this.measurement2 = measurement2;
	}
	public double getMeasurement3() {
		return measurement3;
	}
	public void setMeasurement3(double measurement3)
	{
		this.measurement3 = measurement3;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature)
	{
		this.temperature = temperature;
	}
	public String getWeatherCondition()
	{
		return weatherCondition;
	}
	public void setWeatherCondition(String weatherCondition)
	{
		this.weatherCondition = weatherCondition;
	}
	public double getWindSpeed()
	{
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getWindDirection()
	{
		return windDirection;
	}
	public void setWindDirection(String windDirection)
	{
		this.windDirection = windDirection;
	}
	public String getHumidity()
	{
		return humidity;
	}
	public void setHumidity(String humidity)
	{
		this.humidity = humidity;
	}
	public String getPrecipitation()
	{
		return precipitation;
	}
	public void setPrecipitation(String precipitation)
	{
		this.precipitation = precipitation;
	}
}
