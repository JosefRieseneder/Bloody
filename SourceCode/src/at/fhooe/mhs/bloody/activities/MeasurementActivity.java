/**
 * 
 */
package at.fhooe.mhs.bloody.activities;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.fragments.NumberPickerDialog;
import at.fhooe.mhs.bloody.fragments.NumberPickerListener;
import at.fhooe.mhs.bloody.measurementdata.Measurement;
import at.fhooe.mhs.bloody.measurementdata.MeasurementModel;
import at.fhooe.mhs.bloody.personalData.PersonalData;
import at.fhooe.mhs.bloody.utils.TextFieldInput;

/**
 * @author Elisabeth
 * 
 */
public class MeasurementActivity extends Activity implements
		NumberPickerListener, OnDateSetListener, OnTimeSetListener {

	private static String TAG = MeasurementActivity.class.getSimpleName();
	public static final String EXTRA_MEAS = "EXTRA_MEAS";

	private EditText etSystolic;
	private EditText etDiastolic;
	private EditText etHeartRate;
	private EditText etDate;
	private EditText etTime;
	private MeasurementModel model;
	
	final Calendar calendar = Calendar.getInstance();

	private int systolic, diastolic, heartRate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_measurement);
		model = MeasurementModel.getInstance(this);
		// init variables
		initGUIAndSetListener();
	}

	private void initGUIAndSetListener() {
		//---      systolic      ---
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
				Measurement lastMeasurement = model.getLast();
				int value = lastMeasurement != null ? lastMeasurement.getSystolic() : 120;
				etSystolic.requestFocus();
				NumberPickerDialog npd = new NumberPickerDialog();
				npd.doSettings(MeasurementActivity.this, R.id.etSystolic,
						getResources().getString(R.string.systolic_value), 0,
						250, value);
				npd.show(getFragmentManager(), TAG);
			}
		});

		//---      diastolic      ---
		etDiastolic = (EditText) findViewById(R.id.etDiastolic);
		etDiastolic.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				etDiastolic.requestFocus();
				return false;
			}
		});
		etDiastolic.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Measurement lastMeasurement = model.getLast();
				int value = lastMeasurement != null ? lastMeasurement.getDiastolic() : 80;
				etDiastolic.requestFocus();
				NumberPickerDialog npd = new NumberPickerDialog();
				npd.doSettings(MeasurementActivity.this, R.id.etDiastolic,
						getResources().getString(R.string.diastolic_value), 0,
						250, value);
				npd.show(getFragmentManager(), TAG);
			}
		});

		//---      heartrate      ---
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
				Measurement lastMeasurement = model.getLast();
				int value = lastMeasurement != null ? lastMeasurement.getHeartRate() : 75;
				etHeartRate.requestFocus();
				NumberPickerDialog npd = new NumberPickerDialog();
				npd.doSettings(MeasurementActivity.this, R.id.etHeartRate,
						getResources().getString(R.string.heartrate_value), 0,
						250, value);
				npd.show(getFragmentManager(), TAG);
			}
		});

		//---      date      ---
		etDate = (EditText) findViewById(R.id.etDate);
		TextFieldInput.setDateText(etDate, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		etDate.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				etDate.requestFocus();
				return false;
			}
		});
		etDate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				etDate.requestFocus();

				
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog dpd = new DatePickerDialog(
						MeasurementActivity.this, MeasurementActivity.this,
						year, month, day);
				dpd.show();

			}
		});
		
		//---      time      ---
		etTime = (EditText) findViewById(R.id.etTime);
		TextFieldInput.setTimeText(etTime,calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
		etTime.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				etTime.requestFocus();
				return false;
			}
		});
		etTime.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				etTime.requestFocus();
				
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int minute = calendar.get(Calendar.MINUTE);
				TimePickerDialog tpd = new TimePickerDialog(MeasurementActivity.this, MeasurementActivity.this, hour, minute, true);
				tpd.show();
			}
		});

		// Buttons
		Button save = (Button) findViewById(R.id.buttonMeasurementSave);
		Button cancel = (Button) findViewById(R.id.buttonMeasurementCancel);

		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (model.getPersonalData().isValid()) {
					PersonalData pd = model.getPersonalData();
					Measurement m = new Measurement(heartRate,
							systolic, diastolic,
							pd.getWeight(), pd.getHeight(),
							pd.getLocationLat(), pd.getLocationLon(),
							pd.getLocation(), pd.getAge());
					model.addMeasurement(m);
					Intent i = new Intent(MeasurementActivity.this,
							HealthStatusActivity.class);
					i.putExtra(EXTRA_MEAS, m);
					startActivity(i);
				}
				else {
					AlertDialog.Builder builder = new AlertDialog.Builder(MeasurementActivity.this);
					builder.setTitle(R.string.missing_pd_heading)
							.setMessage(R.string.missing_pd_text)
							.setPositiveButton(R.string.ok,
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog, int id) {
											startActivity(new Intent(MeasurementActivity.this,
													PersonalDataActivity.class));
										}
									})
							.setNegativeButton(R.string.cancel,
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog, int id) {}
									});
			        builder.create().show();
				}
			}
		});

		cancel.setOnClickListener(new View.OnClickListener() {

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
		if (id == R.id.etDiastolic) {
			diastolic = value;
		}
		else if (id == R.id.etSystolic) {
			systolic = value;
		}
		else if (id == R.id.etHeartRate) {
			heartRate = value;
		}
	}

	
	// --------------------------------------
	// date Picker Listener
	// -------------------------------------
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		TextFieldInput.setDateText(etDate, year, monthOfYear, dayOfMonth);
	}
	
	// --------------------------------------
	// time Picker Listener
	// -------------------------------------
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		TextFieldInput.setTimeText(etTime, hourOfDay, minute);
		
	}
	




}
