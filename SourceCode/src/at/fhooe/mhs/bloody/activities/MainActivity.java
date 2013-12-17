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
import at.fhooe.mhs.bloody.locationservice.GPSService;
import at.fhooe.mhs.bloody.charts.ChartsActivity;
import at.fhooe.mhs.bloody.personalData.PersonalData;

public class MainActivity extends Activity
{

	private GPSService gps;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent intent = new Intent(this, ChartsActivity.class);
		startActivity(intent);

		initButtons();

		gps = GPSService.getInstance(this);

		if (!gps.canGetLocation())
		{
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}
		else if (gps.hasValidLocation())
		{
			Toast.makeText(
					getApplicationContext(),
					"Your Location is " + gps.getLocationString()
							+ " - \nLat: " + gps.getLatitude() + "\nLong: "
							+ gps.getLongitude(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void initButtons()
	{
		((Button) findViewById(R.id.button1))
				.setOnClickListener(new OnClickListener()
				{

					@Override
					public void onClick(View v)
					{
						startActivity(new Intent(MainActivity.this,
								MeasurementActivity.class));

					}
				});

		((Button) findViewById(R.id.button2))
				.setOnClickListener(new OnClickListener()
				{

					@Override
					public void onClick(View v)
					{
						startActivity(new Intent(MainActivity.this,
								PersonalDataActivity.class));
					}
				});
	}
}
