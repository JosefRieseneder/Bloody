package at.fhooe.mhs.bloody.weather.listener;

import com.survivingwithandroid.weatherapp.model.Weather;

public interface WeatherListener {
	
	public void onWeatherReceived(Weather weather);
}
