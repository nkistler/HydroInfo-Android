package com.example.hydroinfo_android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
{
	private EditText usernameField,passwordField;
	private TextView status;
	private AssociatedUserDataObject data;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		usernameField = (EditText)findViewById(R.id.usernameEditText);
		passwordField = (EditText)findViewById(R.id.passwordEditText);
		status = (TextView)findViewById(R.id.loginStatusDisplayTextView);
	}
	public void login(View view) throws InterruptedException, ExecutionException
	{
		String username = usernameField.getText().toString();
		String password = passwordField.getText().toString();
		AsyncTask<String, Void, String> session = new SigninActivity(this,status).execute(username,password);
		String statusString = session.get().toString();
		//The string returned from the script will start with "Success" or "Error..." depending on if the login info matches existing account.
		if (statusString.contains("Success"))
		{
			//Convert string returned from script into array list containing our data
			ArrayList<String> items = new  ArrayList<String>(Arrays.asList
					(statusString.split(",")));
			//Set up user object, pass associated data object to new activity
			int userId = Integer.parseInt(items.get(1));
			String userLoginName = items.get(2);
			String userDisplayName = items.get(3);
			String userHashedPassword = items.get(4);
			String userTitle = items.get(5);
			UserObject user = new UserObject(userId, userLoginName, userDisplayName, 
					userHashedPassword, userTitle);
			this.data = new AssociatedUserDataObject(user);
			int numberOfNodes = Integer.parseInt(items.get(6));
			int maxNodeIndex = (3*numberOfNodes)+6;
			int maxIndex = items.size()-1;
			ArrayList<SensorObject> nodeList = this.data.getNodeList();
			//Grab data from all nodes and sort into objects
			for (int i=7; i<=maxNodeIndex; i++)
			{
				int sensorId = Integer.parseInt(items.get(i));
				i++;
				String latitude = items.get(i);
				i++;
				String longitude = items.get(i);
				SensorObject s = new SensorObject(sensorId, latitude, longitude);
				nodeList.add(s);
			}
			//Grab data from all measurements and sort into objects
			for (int i=maxNodeIndex+2; i<=maxIndex; i++)
			{
				int sensorId = Integer.parseInt(items.get(i));
				i++;
				int measurementId = Integer.parseInt(items.get(i));
				i++;
				String measurementDate = items.get(i);
				i++;
				double m1 = Double.parseDouble(items.get(i));
				i++;
				double m2 = Double.parseDouble(items.get(i));
				i++;
				double m3 = Double.parseDouble(items.get(i));
				i++;
				int temperature = Integer.parseInt(items.get(i));
				i++;
				String weatherCondition = items.get(i);
				i++;
				double windSpeed = Double.parseDouble(items.get(i));
				i++;
				String windDirection= items.get(i);
				i++;
				String humidity = items.get(i);
				i++;
				String precipitation = items.get(i);
				MeasurementObject measurement = new MeasurementObject(measurementId, 
						measurementDate, m1, m2, m3, temperature, weatherCondition, 
						windSpeed, windDirection, humidity, precipitation);
				int sensorIndex = 0;
				//Find location of node in the array which matches what the measurement returns as its associated node
				for(int j=0; j<nodeList.size(); j++)
				{
					if (nodeList.get(j).getId() == sensorId)
					{
						sensorIndex = j;
						break;
					}
				}
				this.data.getNodeList().get(sensorIndex).getMeasurementList().add(measurement);
			}
			Intent intent = new Intent(this, MapActivity.class);
			intent.putExtra("data", this.data);
		    startActivity(intent);
		}
	}
}

