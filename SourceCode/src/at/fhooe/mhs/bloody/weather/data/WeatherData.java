package at.fhooe.mhs.bloody.weather.data;

import java.util.Date;
import java.util.HashMap;

import android.content.Context;
import at.fhooe.mhs.bloody.weather.serializer.WeatherSerializer;

import com.survivingwithandroid.weatherapp.model.Weather;

public class WeatherData {

	private static final String FILE_NAME = "WeatherData";
	private HashMap<Date, Weather> weatherData;

	public WeatherData() {
		weatherData = new HashMap<Date, Weather>();
	}

	public HashMap<Date, Weather> getWeatherData() {
		return weatherData;
	}

	public void addWeather(Date date, Weather weather) {
		weatherData.put(date, weather);
	}

	public void removeWeather(Date date) {
		weatherData.remove(date);
	}

	public Weather getWeather(Date date) {
		return weatherData.get(date);
	}

	public void store(Context context) {
		WeatherSerializer.serialize(context, FILE_NAME, weatherData);
	}

	public void load(Context context) {
		HashMap<Date, Weather> ret = WeatherSerializer.deserialize(context,
				FILE_NAME);
		if (ret != null) {
			weatherData = ret;
		}
	}
}
