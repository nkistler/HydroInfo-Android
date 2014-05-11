package com.example.hydroinfo_android;

import com.androidplot.xy.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class GraphActivity extends Activity
{
	private AssociatedUserDataObject data;
	private XYPlot plot;
	private int idNum;
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_graph);
        
        //Grab layout elements
        TextView txtSelection = (TextView)findViewById(R.id.sensor_label);
        TextView txtFields = (TextView)findViewById(R.id.table_fields);
        final ListView listview = (ListView)findViewById(R.id.listView);
        final ArrayList<String> list = new ArrayList<String>();
        this.plot = (XYPlot)findViewById(R.id.mySimpleXYPlot);
        
        //Get data passed by intent
        Intent intent = getIntent();
        String selected = intent.getStringExtra("sensorString");
        this.idNum = Integer.parseInt(selected.substring(4, 6).trim());
        this.data = (AssociatedUserDataObject)intent.getSerializableExtra("data");
        
        //Create needed lists
        List<SensorObject> nodeList = this.data.getNodeList();
        List<MeasurementObject> measurements = null;

        for (int j=0; j<nodeList.size(); j++)
        {
        	if(nodeList.get(j).getId()==idNum)
        	{
        		measurements = nodeList.get(j).getMeasurementList();
        		break;
        	}
        }
        
        //Create arrays of y-values to plot:
        Number[] series1Numbers = new Number[measurements.size()];
        Number[] series2Numbers = new Number[measurements.size()];
        Number[] series3Numbers = new Number[measurements.size()];
        
        //Extract data for display
        for (int i = 0; i < measurements.size(); i++) 
        {
        	Double m1 = measurements.get(i).getMeasurement1();
        	Double m2 = measurements.get(i).getMeasurement2();
        	Double m3 = measurements.get(i).getMeasurement3();
        	list.add(measurements.get(i).getTimestamp()+
        			"\r\n"+Double.toString(m1)+
        			"\t\t"+Double.toString(m2)+
        			"\t\t"+Double.toString(m3)
        			);
        	series1Numbers[i]=m1; 
        	series2Numbers[i]=m2;
        	series3Numbers[i]=m3;
        }
        
        //This contains format of individual items on list
        final StableArrayAdapter adapter = new StableArrayAdapter(this, 
        		android.R.layout.simple_list_item_1, list);

        // Turn the above arrays into XYSeries':
        // SimpleXYSeries takes a List so turn our array into a List
        // Y_VALS_ONLY means use the element index as the x value
        // Set the display title of the series
        XYSeries series1 = new SimpleXYSeries(Arrays.asList(series1Numbers), 
        		SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "s1");                             

        // same as above
        XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers), 
        		SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "s2");
        
        // same as above
        XYSeries series3 = new SimpleXYSeries(Arrays.asList(series3Numbers), 
        		SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "s3");

        // Create a formatter to use for drawing a series using LineAndPointRenderer
        LineAndPointFormatter series1Format = new LineAndPointFormatter();
        series1Format.setPointLabelFormatter(new PointLabelFormatter());
        series1Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf1);
        plot.addSeries(series1, series1Format);

        // same as above:
        LineAndPointFormatter series2Format = new LineAndPointFormatter();
        series2Format.setPointLabelFormatter(new PointLabelFormatter());
        series2Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf2);
        plot.addSeries(series2, series2Format);
        
        // same as above:
        LineAndPointFormatter series3Format = new LineAndPointFormatter();
        series3Format.setPointLabelFormatter(new PointLabelFormatter());
        series3Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf3);
        plot.addSeries(series3, series3Format);

        // reduce the number of range labels
        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget().setDomainLabelOrientation(-45);
        
        //Display
        listview.setAdapter(adapter);
        txtSelection.setText(selected);
        txtFields.setText("Date\r\nSensor1\t\tSensor2\t\tSensor3");
        
        //Set up clickable items on the list to allow user to view record detail
        listview.setOnItemClickListener(new OnItemClickListener() 
        {
          public void onItemClick(AdapterView<?> parent, View view, int position, 
        		  long id) 
          {
        	  // selected item 
        	  String s = ((TextView)view).getText().toString();
        	  
        	  // Launching new Activity on selecting single List Item
        	  Intent intent = new Intent(getApplicationContext(), RecordDetailActivity.class);
        	  // sending data to new activity
        	  intent.putExtra("idNumber", idNum);
        	  intent.putExtra("recordString", s);
        	  intent.putExtra("data", data);
        	  startActivity(intent);
          }
        });
	}
	
}