/**
 * 
 */
package at.fhooe.mhs.bloody.measurementdata;

import java.io.Serializable;
import java.util.Date;

import com.survivingwithandroid.weatherapp.model.Weather;

/**
 * @author Patrick Hutflesz
 * 
 */
public class Measurement implements Serializable
{

	/**
	 * Heart rate measurement in bpm
	 */
	private int heartRate;
	private int systolic, diastolic;
	private String date;
	/**
	 * Weight in kg.
	 */
	private int weight;
	/**
	 * Height in cm.
	 */
	private int height;
	private double latitude, longitude;
	private String location;
	private int age;

	private Weather weather;

	public Measurement(int heartRate, int systolic, int diastolic, int weight,
			int height, double latitude, double longitude, String location,
			int age, Weather weather, String date)
	{
		this.heartRate = heartRate;
		this.systolic = systolic;
		this.diastolic = diastolic;
		this.weight = weight;
		this.height = height;
		this.latitude = latitude;
		this.longitude = longitude;
		this.location = location;
		this.age = age;
		this.weather = weather;
		this.date = date;
	}

	public int getDiastolic()
	{
		return diastolic;
	}

	public int getSystolic()
	{
		return systolic;
	}

	/**
	 * @return the heart rate in beats per minute.
	 */
	public int getHeartRate()
	{
		return heartRate;
	}

	public int getHeight()
	{
		return height;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public String getLocation()
	{
		return location;
	}

	public int getWeight()
	{
		return weight;
	}

	public int getAge()
	{
		return age;
	}

	public Weather getWeather()
	{
		return weather;
	}

	public String getDate()
	{
		return date;
	}
	
	
}
