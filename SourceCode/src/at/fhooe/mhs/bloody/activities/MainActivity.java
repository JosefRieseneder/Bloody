package at.fhooe.mhs.bloody.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.locationservice.GPSListener;
import at.fhooe.mhs.bloody.locationservice.GPSService;
import at.fhooe.mhs.bloody.measurementdata.MeasurementModel;
import at.fhooe.mhs.bloody.personalData.PersonalData;
import at.fhooe.mhs.bloody.weather.data.WeatherData;
import at.fhooe.mhs.bloody.weather.listener.WeatherListener;

import com.survivingwithandroid.weatherapp.JSONWeatherTask;
import com.survivingwithandroid.weatherapp.model.Weather;

public class MainActivity extends Activity implements GPSListener,
		WeatherListener {

	private GPSService gps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initButtons();

		gps = GPSService.getInstance(this);
		gps.setGPSListener(this);

		if (!gps.canGetLocation())
		{
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		} else if (gps.hasValidLocation()) {
			Toast.makeText(
					getApplicationContext(),
					"Your Location is " + gps.getLocationString()
							+ " - \nLat: " + gps.getLatitude() + "\nLong: "
							+ gps.getLongitude(), Toast.LENGTH_LONG).show();
		}

		startPersonalizationWizardIfNecessary();
	}

	private void startPersonalizationWizardIfNecessary() {
		String id = MeasurementModel.getInstance(this).getPersonalData()
				.getId();
		if (id == null || id.equals("")) {
			startActivity(new Intent(this, PersonalDataActivity.class));
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void initButtons() {
		((Button) findViewById(R.id.button1))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(MainActivity.this,
								MeasurementActivity.class));

					}
				});

		((Button) findViewById(R.id.button2))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(MainActivity.this,
								PersonalDataActivity.class));
					}
				});
	}

	@Override
	public void onLocationReceived(boolean newPosition, double latitude,
			double longitude) {
		if (!newPosition) {
			PersonalData pd = MeasurementModel.getInstance(this)
					.getPersonalData();
			if (pd.getId() != null && !pd.getId().equals("")) {
				getWeather(pd.getLocationLat(), pd.getLocationLon());
			}
		} else {
			getWeather(latitude, longitude);
		}
	}

	@Override
	public void onPersonalLocationReceived(double _latitude, double _longitude) {
		if (WeatherData.getInstance().getWeather() == null) {
			PersonalData pd = MeasurementModel.getInstance(this)
					.getPersonalData();
			if (pd.getId() != null && !pd.getId().equals("")) {
				getWeather(pd.getLocationLat(), pd.getLocationLon());
			}
		}
	}

	private void getWeather(double latitude, double longitude) {
		JSONWeatherTask task = new JSONWeatherTask();
		task.setWeatherListener(this);
		task.execute(new Double[] { latitude, longitude });
	}

	@Override
	public void onWeatherReceived(Weather weather) {
		if (weather != null) {
			WeatherData.getInstance().setWeather(weather);
		}
	}
}
