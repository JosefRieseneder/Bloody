/**
 * 
 */
package at.fhooe.mhs.bloody.fragments;

import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.activities.HealthStatusActivity;
import at.fhooe.mhs.bloody.activities.MeasurementActivity;
import at.fhooe.mhs.bloody.personalData.Gender;
import at.fhooe.mhs.bloody.utils.TextFieldInput;

/**
 * @author Elisabeth
 * 
 */
public class AddressFragment extends Fragment implements NumberPickerListener {
	final static String TAG = AddressFragment.class.getSimpleName();

	EditText etAdress;
	Button btGPS;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_address, container, false);
		initGUIAndSetListener(v);
		return v;
	}

	public boolean isFilled() {
		return !etAdress.equals("");
	}

	private void initGUIAndSetListener(View v) {

		etAdress = (EditText) v.findViewById(R.id.et_adress);

		etAdress.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				etAdress.requestFocus();
				return false;
			}
		});
		etAdress.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				etAdress.requestFocus();
			}
		});

		btGPS = (Button) v.findViewById(R.id.bt_gps);
		btGPS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i(TAG, "activate GPS");
			}
		});
	}

	@Override
	public void onNumberChanged(int id, int value) {
		EditText et = (EditText) getActivity().findViewById(id);
		et.setText("" + value);
	}

}
