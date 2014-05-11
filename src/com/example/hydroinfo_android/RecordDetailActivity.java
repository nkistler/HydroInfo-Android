package com.example.hydroinfo_android;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecordDetailActivity extends Activity 
{
	private AssociatedUserDataObject data;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record_detail);
		
		TextView txtFields = (TextView)findViewById(R.id.RecordTextView);
		
		//Get data passed by intent
        Intent intent = getIntent();
        String selected = intent.getStringExtra("recordString");
        int idNumber = intent.getIntExtra("idNumber", 0);
        this.data = (AssociatedUserDataObject)intent.getSerializableExtra("data");
        
        //Parse data from intent
        String dateStringMatch1 = selected.substring(0, 17);
        String dateStringMatch2 = selected.substring(0, 18); 
        List<SensorObject> nodeList = this.data.getNodeList();
        SensorObject node = null;
        MeasurementObject measurement = null;
        //Get node
        for (int i=0; i<nodeList.size(); i++)
        {
        	if (nodeList.get(i).getId()==idNumber)
        	{
        		node = nodeList.get(i);
        	}
        }
        //Get list of measurements
        List<MeasurementObject> measurements = node.getMeasurementList();
        for (int i=0; i<measurements.size(); i++)
        {
        	String timestamp = measurements.get(i).getTimestamp();
        	if (timestamp.equals(dateStringMatch1) || timestamp.equals(dateStringMatch2))
        	{
        		measurement = measurements.get(i);
        	}
        }
        
        //Display
        txtFields.setText("Measurement ID: "+measurement.getId()+"\r\n"+"Datetime: "
        		+measurement.getTimestamp()+"\r\nMeasurements: "+measurement.getMeasurement1()
        		+", "+measurement.getMeasurement2()+", "+measurement.getMeasurement3()
        		+"\r\nTemperature: "+measurement.getTemperature()+"\r\nWeather Condition: "
        		+measurement.getWeatherCondition()+"\r\nWind Speed: "+measurement.getWindSpeed()
        		+"\r\nWind Direction: "+measurement.getWindDirection()+"\r\nHumidity: "
        		+measurement.getHumidity()+"\r\nPrecipitation: "+measurement.getPrecipitation());
	}
}
