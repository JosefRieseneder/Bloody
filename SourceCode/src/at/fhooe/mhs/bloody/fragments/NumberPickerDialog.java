package at.fhooe.mhs.bloody.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import at.fhooe.mhs.bloody.R;

public class NumberPickerDialog extends DialogFragment implements
		OnValueChangeListener {

	private NumberPicker numberPicker;
	private NumberPickerListener listener;
	private int id;
	private String title;
	private int minValue;
	private int maxValue;
	private int curValue;
	private String[] stringValues;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppTheme);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getDialog().setTitle(title);
		
		View v = inflater.inflate(R.layout.dialog_number_picker, null);
		numberPicker = (NumberPicker) v.findViewById(R.id.dialog_nr_picker);

		numberPicker.setMinValue(minValue);
		numberPicker.setMaxValue(maxValue);
		numberPicker.setValue(curValue);
		
		if(stringValues!=null){
			numberPicker.setDisplayedValues(stringValues);
		}
		
		numberPicker.setOnValueChangedListener(this);
		
		Button okButton = (Button)v.findViewById(R.id.dialog_nr_buttonOK);
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				curValue = numberPicker.getValue();
				if (listener != null) {
					listener.onNumberChanged(id, curValue);
				}
				dismiss();
			}
		});
		
		return v;
	}

	public void doSettings(NumberPickerListener listener, int id, String title,
			int minValue, int maxValue, int curValue) {
		this.listener = listener;
		this.id = id;
		this.title = title;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.curValue = curValue;
	}
	
	public void doSettings(NumberPickerListener listener, int id, String title,
			int minValue, int maxValue, int curValue, String[] stringValues) {
		doSettings(listener, id, title, minValue, maxValue, curValue);
		this.stringValues = stringValues;
	}

	@Override
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
		curValue = newVal;
	}
	
}
