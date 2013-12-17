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
public class WeightFragment extends Fragment implements NumberPickerListener {
	final static String TAG = WeightFragment.class.getSimpleName();
	int weight;
	EditText etWeight;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_weight, container, false);
		initGUIAndSetListener(v);
		return v;
	}

	public boolean isFilled() {
		return !etWeight.equals("");
	}

	private void initGUIAndSetListener(View v) {

		etWeight = (EditText) v.findViewById(R.id.et_weight_amount);

		etWeight.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				etWeight.requestFocus();
				return false;
			}
		});
		etWeight.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				etWeight.requestFocus();
				NumberPickerDialog npd = new NumberPickerDialog();
				npd.doSettings(WeightFragment.this, R.id.et_weight_amount,
						getResources().getString(R.string.weight), 0, 600, 75);
				npd.show(getFragmentManager(), TAG);

			}
		});

	}

	@Override
	public void onNumberChanged(int id, int value) {
		EditText et = (EditText) getActivity().findViewById(id);
		et.setText("" + value);
		weight = value;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	

}
