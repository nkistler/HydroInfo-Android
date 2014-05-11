package com.example.hydroinfo_android;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MapActivity extends Activity
{
	private AssociatedUserDataObject data;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        this.data = (AssociatedUserDataObject)getIntent().getSerializableExtra("data");
        ArrayList<SensorObject> nodeList = this.data.getNodeList();

        final ListView listview = (ListView)findViewById(R.id.sensorListView);
        final ArrayList<String> list = new ArrayList<String>();
        
        for (int i = 0; i < nodeList.size(); i++) 
        {
        	list.add("ID: "+Integer.toString(nodeList.get(i).getId())+
        			"\t\tLocation: "+nodeList.get(i).getLatitude()+
        			","+nodeList.get(i).getLongitude());
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this, 
        		android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        // listening to single list item on click
        listview.setOnItemClickListener(new OnItemClickListener() 
        {
          public void onItemClick(AdapterView<?> parent, View view, int position, 
        		  long id) 
          {
        	  // selected item 
        	  String s = ((TextView)view).getText().toString();
        	  
        	  // Launching new Activity on selecting single List Item
        	  Intent intent = new Intent(getApplicationContext(), GraphActivity.class);
        	  // sending data to new activity
        	  intent.putExtra("sensorString", s);
        	  intent.putExtra("data", data);
        	  startActivity(intent);
          }
        });
    }  
}
