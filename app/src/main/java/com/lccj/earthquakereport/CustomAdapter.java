package com.lccj.earthquakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by apple on 21/11/17.
 */

public class CustomAdapter extends ArrayAdapter<Earthquake> {

  String primaryLocation;
  String locationOffset;

  private static final String LOCATION_SEPARATOR = " of ";

  private static final String LOG_TAG = CustomAdapter.class.getSimpleName();

  /**
   * This is our own custom constructor.
   * The context is used to inflate the layout file, and the list is the data we want
   * to populate into the lists.
   *
   * @param context        The current context. Used to inflate the layout file.
   * @param earthquakes A List of AndroidFlavor objects to display in a list
   */
  public CustomAdapter(Activity context, ArrayList<Earthquake> earthquakes) {

    super(context, 0, earthquakes );
  }

  /**
   * Provides a view the CustomAdapter
   *
   * @param position The position in the list of data that should be displayed in the
   *                 list item view.
   * @param convertView The recycled view to populate.
   * @param parent The parent ViewGroup that is used for inflation.
   * @return The View for the position in the AdapterView.
   */

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    // Check if the existing view is being reused, otherwise inflate the view
    View listItemView = convertView;
    if (listItemView == null) {
      listItemView = LayoutInflater.from(getContext()).inflate(
          R.layout.list_item, parent, false);
    }

    // Get the {@link Earthquake} object located at this position in the list
    Earthquake currentEarthquake = getItem(position);

    // Find the TextView in the list_item.xml layout with the ID version_name
    TextView magTextView = listItemView.findViewById(R.id.magnitudeView);
    // Get the current Earthquake object and
    // set on the name TextView

    String magFormatted = formatMagnitude(currentEarthquake.getMag());
    magTextView.setText(magFormatted);

    GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();
    int magnitudeColor = getMagnitudeColor(currentEarthquake.getMag());
    magnitudeCircle.setColor(magnitudeColor);


    //split string in 2 parts at LOCATION_SEPARATOR ("of")
    //if there is no LOCATION_SEPARATOR use the originalLocation with R.string.near_the
    String originalLocation = currentEarthquake.getCity();

    if (originalLocation.contains(LOCATION_SEPARATOR)) {
      String[] parts = originalLocation.split(LOCATION_SEPARATOR);
      locationOffset = parts[0] + LOCATION_SEPARATOR;
      primaryLocation = parts[1];
    } else {
      locationOffset = getContext().getString(R.string.near_the);
      primaryLocation = originalLocation;
    }

    //get locationOffset on screen
    TextView kmTextView = listItemView.findViewById(R.id.location_offsetView);
    kmTextView.setText(locationOffset);

    //get primaryLocation on screen
    TextView cityTextView = listItemView.findViewById(R.id.primary_locationView);
    cityTextView.setText(primaryLocation);

    Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

    //get date formatted on screen
    TextView dateTextView = listItemView.findViewById(R.id.dateView);
    String formattedDate = formatDate(dateObject);
    dateTextView.setText(formattedDate);

    //get time formatted on screen
    TextView timeTextView = listItemView.findViewById(R.id.timeView);
    String formattedTime = formatTime(dateObject);
    timeTextView.setText(formattedTime);

    return listItemView;
  }

  /**
   * Formate the magnitude to 0.00
   * @param mag
   * @return String
   */
  private String formatMagnitude(double mag) {
    DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
    return magnitudeFormat.format(mag);
  }

  /**
   * Set the appropriate color according the magnitude level
   * @param mag
   * @return int color id
   */
  private int getMagnitudeColor(double mag){
    int magnitudeColorResourceId;
    int magnitudeFloor = (int) Math.floor(mag);
    switch (magnitudeFloor) {
      case 0:
      case 1:
        magnitudeColorResourceId = R.color.magnitude1;
        break;
      case 3:
        magnitudeColorResourceId = R.color.magnitude3;
        break;
      case 4:
        magnitudeColorResourceId = R.color.magnitude4;
        break;
      case 5:
        magnitudeColorResourceId = R.color.magnitude5;
        break;
      case 6:
        magnitudeColorResourceId = R.color.magnitude6;
        break;
      case 7:
        magnitudeColorResourceId = R.color.magnitude7;
        break;
      case 8:
        magnitudeColorResourceId = R.color.magnitude8;
        break;
      case 9:
        magnitudeColorResourceId = R.color.magnitude9;
        break;
      default:
        magnitudeColorResourceId = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
        break;
    }
    //call ContextCompat getColor() to convert the color resource ID
    //into an actual integer color value,
    return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
  }

  /**
   * Method to format date
   * @param dateObject
   * @return String with month date, year
   */
  public String formatDate (Date dateObject){

    SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, YYYY");
    return dateFormat.format(dateObject);
  }

  /**
   * Method to format time
   * @param dateObject
   * @return String with hour, minutes am/pm
   */
  public String formatTime (Date dateObject){

    SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
    return timeFormat.format(dateObject);
  }

}
