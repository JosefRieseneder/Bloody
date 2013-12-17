package com.survivingwithandroid.weatherapp;

import org.json.JSONException;

import android.os.AsyncTask;
import at.fhooe.mhs.bloody.weather.listener.WeatherListener;

import com.survivingwithandroid.weatherapp.model.Weather;

/*
 * Copyright (C) 2013 Surviving with Android (http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class JSONWeatherTask extends AsyncTask<Double, Void, Weather> {

	private WeatherListener weatherListener;
	
	@Override
	protected Weather doInBackground(Double... params) {
		Weather weather = new Weather();
		String data = ((new WeatherHttpClient()).getWeatherData(params[0], params[1]));

		try {
			weather = JSONWeatherParser.getWeather(data);

			// Let's retrieve the icon
			weather.iconData = ((new WeatherHttpClient())
					.getImage(weather.currentCondition.getIcon()));

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return weather;

	}

	@Override
	protected void onPostExecute(Weather weather) {
		super.onPostExecute(weather);

		if (weatherListener != null) {
			weatherListener.onWeatherReceived(weather);
		}
		
		// if (weather.iconData != null && weather.iconData.length > 0) {
		// Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0,
		// weather.iconData.length);
		// imgView.setImageBitmap(img);
		// }
		//
		// cityText.setText(weather.location.getCity() + ","
		// + weather.location.getCountry());
		// condDescr.setText(weather.currentCondition.getCondition() + "("
		// + weather.currentCondition.getDescr() + ")");
		// temp.setText("" + Math.round((weather.temperature.getTemp() -
		// 273.15))
		// + "�C");
		// hum.setText("" + weather.currentCondition.getHumidity() + "%");
		// press.setText("" + weather.currentCondition.getPressure() + " hPa");
		// windSpeed.setText("" + weather.wind.getSpeed() + " mps");
		// windDeg.setText("" + weather.wind.getDeg() + "�");

	}

	public void setWeatherListener(WeatherListener weatherListener) {
		this.weatherListener = weatherListener;
	}
}
