package at.fhooe.mhs.bloody.weather.data;

import com.survivingwithandroid.weatherapp.model.Weather;

public class WeatherData {

	private Weather weather;

	private static WeatherData instance;

	public static WeatherData getInstance() {
		if (instance == null) {
			instance = new WeatherData();
		}
		return instance;
	}

	private WeatherData() {
		this.weather = null;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public Weather getWeather() {
		return this.weather;
	}
}
