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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
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
public class GenderFragment extends Fragment implements NumberPickerListener {
	final static String TAG = GenderFragment.class.getSimpleName();
	
	private Gender genderValue;

	private EditText etGender;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_gender, container, false);
		initGUIAndSetListener(v);
		return v;
	}

	public boolean isFilled() {
		return !etGender.getText().equals("");
	}

	private void initGUIAndSetListener(View v) {

		final Calendar calendar = Calendar.getInstance();
		etGender = (EditText) v.findViewById(R.id.et_gender);

		etGender.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				etGender.requestFocus();
				return false;
			}
		});
		etGender.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				etGender.requestFocus();

				NumberPickerDialog npd = new NumberPickerDialog();
				npd.doSettings(
						GenderFragment.this,
						R.id.et_gender,
						getResources().getString(R.string.gender),
						Gender.MALE.getValue(),
						Gender.OTHER.getValue(),
						Gender.MALE.getValue(),
						new String[] {
								getResources().getString(R.string.gender_male),
								getResources()
										.getString(R.string.gender_female),
								getResources().getString(R.string.gender_other) });
				npd.show(getFragmentManager(), TAG);
			}
		});

	}

	@Override
	public void onNumberChanged(int id, int value) {
		
		switch (value) {
		case 0:
			genderValue = Gender.MALE;
			etGender.setText(getResources().getString(R.string.gender_male));
			break;
		case 1:
			genderValue = Gender.FEMALE;
			etGender.setText(getResources().getString(R.string.gender_female));
			break;
		case 2:
			genderValue = Gender.OTHER;
			etGender.setText(getResources().getString(R.string.gender_other));
			break;

		default:
			break;
		}

	}

	/**
	 * @return the genderValue
	 */
	public Gender getGenderValue() {
		return genderValue;
	}

	/**
	 * @param genderValue the genderValue to set
	 */
	public void setGenderValue(Gender genderValue) {
		this.genderValue = genderValue;
	}


	
	

}
