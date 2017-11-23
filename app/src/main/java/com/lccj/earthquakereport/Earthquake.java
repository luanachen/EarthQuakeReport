package com.lccj.earthquakereport;

/**
 * Created by apple on 21/11/17.
 */

public class Earthquake {

  private double mag;
  private String city;
  private long timeInMilliseconds;
  private String url;


  public Earthquake(double mag, String city, long timeInMilliseconds, String url){

    this.mag = mag;
    this.city = city;
    this.timeInMilliseconds = timeInMilliseconds;
    this.url = url;
  }

  public double getMag() {
    return mag;
  }

  public String getCity() {
    return city;
  }

  public long getTimeInMilliseconds() {
    return timeInMilliseconds;
  }

  public String getUrl() {
    return url;
  }
}
