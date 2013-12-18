/**
 * 
 */
package at.fhooe.mhs.bloody.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import at.fhooe.mhs.bloody.R;

/**
 * @author Elisabeth
 * 
 */
public class HeightFragment extends Fragment implements NumberPickerListener {
	final static String TAG = HeightFragment.class.getSimpleName();
	int height;
	EditText etHeight;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_height, container, false);
		initGUIAndSetListener(v);
		return v;
	}

	public boolean isFilled() {
		return !etHeight.equals("");
	}

	private void initGUIAndSetListener(View v) {

		height = 175;
		etHeight = (EditText) v.findViewById(R.id.et_height_amount);

		etHeight.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				etHeight.requestFocus();
				return false;
			}
		});
		etHeight.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				etHeight.requestFocus();
				NumberPickerDialog npd = new NumberPickerDialog();
				npd.doSettings(HeightFragment.this, R.id.et_height_amount,
						getResources().getString(R.string.height), 0, 300, height);
				npd.show(getFragmentManager(), TAG);

			}
		});

	}

	@Override
	public void onNumberChanged(int id, int value) {
		EditText et = (EditText) getActivity().findViewById(id);
		et.setText("" + value);
		this.height = value;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	
	
}
