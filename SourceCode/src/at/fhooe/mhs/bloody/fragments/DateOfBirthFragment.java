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
import at.fhooe.mhs.bloody.utils.TextFieldInput;

/**
 * @author Elisabeth
 * 
 */
public class DateOfBirthFragment extends Fragment implements OnDateSetListener {

	Date dateOfBirth;
	EditText etDateOfBirth;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_date_of_birth, container,
				false);
		initGUIAndSetListener(v);
		return v;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public boolean isFilled() {
		return dateOfBirth != null;
	}

	private void initGUIAndSetListener(View v) {

		final Calendar calendar = Calendar.getInstance();
		etDateOfBirth = (EditText) v.findViewById(R.id.et_date_of_birth);
		TextFieldInput.setDateText(etDateOfBirth, calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));

		etDateOfBirth.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				etDateOfBirth.requestFocus();
				return false;
			}
		});
		etDateOfBirth.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				etDateOfBirth.requestFocus();

				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog dpd = new DatePickerDialog(
						DateOfBirthFragment.this.getActivity(),
						DateOfBirthFragment.this, year, month, day);
				dpd.show();
			}
		});

	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, monthOfYear);
		cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

		TextFieldInput.setDateText(etDateOfBirth, cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH));
		dateOfBirth = cal.getTime();
	}

}
