package at.fhooe.mhs.bloody.weather.serializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;

import android.content.Context;

import com.survivingwithandroid.weatherapp.model.Weather;

public class WeatherSerializer {
	public static boolean serialize(Context context, String fileName,
			HashMap<Date, Weather> weather) {
		try {
			FileOutputStream fos = context.openFileOutput(fileName,
					Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(weather);
			os.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static HashMap<Date, Weather> deserialize(Context context,
			String fileName) {
		try {
			FileInputStream fis = context.openFileInput(fileName);
			ObjectInputStream is = new ObjectInputStream(fis);
			HashMap<Date, Weather> weather = (HashMap<Date, Weather>) is
					.readObject();
			is.close();
			return weather;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
