/**
 * 
 */
package at.fhooe.mhs.bloody.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.GetChars;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.fragments.NumberPickerDialog;
import at.fhooe.mhs.bloody.fragments.NumberPickerListener;

/**
 * @author Elisabeth
 * 
 */
public class MeasurementActivity extends Activity implements
		NumberPickerListener, OnDateSetListener, OnTimeSetListener {

	private static String TAG = MeasurementActivity.class.getSimpleName();

	private EditText etSystolic;
	private EditText etAsystolic;
	private EditText etHeartRate;
	private EditText etDate;
	private EditText etTime;
	
	final Calendar calendar = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_measurement);
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
				etSystolic.requestFocus();
				NumberPickerDialog npd = new NumberPickerDialog();
				npd.doSettings(MeasurementActivity.this, R.id.etSystolic,
						getResources().getString(R.string.systolic_value), 0,
						250, 120);
				npd.show(getFragmentManager(), TAG);
			}
		});

		//---      asystolic      ---
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
				etHeartRate.requestFocus();
				NumberPickerDialog npd = new NumberPickerDialog();
				npd.doSettings(MeasurementActivity.this, R.id.etHeartRate,
						getResources().getString(R.string.heartrate_value), 0,
						250, 75);
				npd.show(getFragmentManager(), TAG);
			}
		});

		//---      date      ---
		etDate = (EditText) findViewById(R.id.etDate);
		setDateText(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
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
		setTimeText(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
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

	
	// --------------------------------------
	// date Picker Listener
	// -------------------------------------
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		setDateText(year, monthOfYear, dayOfMonth);
	}
	
	// --------------------------------------
	// time Picker Listener
	// -------------------------------------
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		setTimeText(hourOfDay, minute);
		
	}
	
	private void setDateText(int year, int month, int day){
		GregorianCalendar date = new GregorianCalendar(year, month, day);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	    String dateString = sdf.format(date.getTime());
		etDate.setText(dateString);
	}
	
	private void setTimeText(int hour, int minute){
		GregorianCalendar date = new GregorianCalendar(0, 0, 0, hour, minute, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    String dateString = sdf.format(date.getTime());
		etTime.setText(dateString);
	}



}
