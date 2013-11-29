/**
 * 
 */
package at.fhooe.mhs.bloody.activities;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.GetChars;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.fragments.NumberPickerDialog;
import at.fhooe.mhs.bloody.fragments.NumberPickerListener;

/**
 * @author Elisabeth
 * 
 */
public class MeasurementActivity extends Activity implements
		NumberPickerListener {

	private static String TAG = MeasurementActivity.class.getSimpleName();

	private EditText etSystolic;
	private EditText etAsystolic;
	private EditText etHeartRate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_measurement);
		// init variables
		initGUIAndSetListener();
	}

	private void initGUIAndSetListener() {

		etSystolic = (EditText) findViewById(R.id.etSystolic);
		etSystolic.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				etSystolic.requestFocus();
				return false;
			}
		});
		etSystolic.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				etSystolic.requestFocus();
				NumberPickerDialog npd = new NumberPickerDialog();
				npd.doSettings(MeasurementActivity.this, R.id.etSystolic,
						getResources().getString(R.string.systolic_value), 0,
						250, 120);
				npd.show(getFragmentManager(), TAG);
			}
		});

		etAsystolic = (EditText) findViewById(R.id.etAsystolic);
		etAsystolic.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				etAsystolic.requestFocus();
				return false;
			}
		});
		etAsystolic.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				etAsystolic.requestFocus();
				NumberPickerDialog npd = new NumberPickerDialog();
				npd.doSettings(MeasurementActivity.this, R.id.etAsystolic,
						getResources().getString(R.string.asystolic_value), 0,
						250, 80);
				npd.show(getFragmentManager(), TAG);
			}
		});

		etHeartRate = (EditText) findViewById(R.id.etHeartRate);
		etHeartRate.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				etHeartRate.requestFocus();
				return false;
			}
		});
		etHeartRate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				etHeartRate.requestFocus();
				NumberPickerDialog npd = new NumberPickerDialog();
				npd.doSettings(MeasurementActivity.this, R.id.etHeartRate,
						getResources().getString(R.string.heartrate_value), 0,
						250, 75);
				npd.show(getFragmentManager(), TAG);
			}
		});

		// Buttons
		Button save = (Button) findViewById(R.id.buttonMeasurementSave);
		Button cancle = (Button) findViewById(R.id.buttonMeasurementCancle);

		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MeasurementActivity.this,
						HealthStatusActivity.class));
			}
		});

		cancle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	// --------------------------------------
	// number Picker Listener
	// -------------------------------------
	@Override
	public void onNumberChanged(int id, int value) {
		EditText et = (EditText) findViewById(id);
		et.setText("" + value);
	}

}
