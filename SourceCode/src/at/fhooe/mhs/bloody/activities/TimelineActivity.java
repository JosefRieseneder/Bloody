package at.fhooe.mhs.bloody.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.charts.BloodPressureChartView;

public class TimelineActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		BloodPressureChartView view = new BloodPressureChartView(this);
		view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(view);
	}

}
