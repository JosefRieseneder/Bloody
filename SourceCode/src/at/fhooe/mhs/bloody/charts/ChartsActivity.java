package at.fhooe.mhs.bloody.charts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class ChartsActivity extends Activity
{
	/**
	 * Called when the activity is starting.
	 * 
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		BloodPressureChartView mView = new BloodPressureChartView(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(mView);
	}

}