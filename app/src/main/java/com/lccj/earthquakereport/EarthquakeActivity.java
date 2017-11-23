package com.lccj.earthquakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

  public static final String LOG_TAG = EarthquakeActivity.class.getName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.earthquake_activity);

    // Create a fake list of Earthquake objects.
    ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();

    final CustomAdapter adapter = new CustomAdapter(this, earthquakes);

    // Get a reference to the ListView, and attach the adapter to the listView.
    ListView listView = (ListView) findViewById(R.id.list);
    listView.setAdapter(adapter);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        // Find the current earthquake that was clicked on
        Earthquake currentEarthquake = adapter.getItem(position);

        // Convert the String URL into a URI object (to pass into the Intent constructor)
        Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

        // Create a new intent to view the earthquake URI
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

        // Send the intent to launch a new activity
        startActivity(websiteIntent);
      }
    });


  }
}
