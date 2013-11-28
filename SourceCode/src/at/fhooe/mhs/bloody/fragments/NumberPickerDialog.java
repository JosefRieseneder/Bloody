package at.fhooe.mhs.bloody.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder b = new AlertDialog.Builder(getActivity())
				.setTitle(title).setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								curValue = numberPicker.getValue();
								if (listener != null) {
									listener.onNumberChanged(id, curValue);
								}
								dialog.dismiss();
							}
						});

		LayoutInflater inflater = getActivity().getLayoutInflater();
		View v = inflater.inflate(R.layout.dialog_number_picker, null);
		numberPicker = (NumberPicker) v.findViewById(R.id.dialog_nr_picker);

		numberPicker.setMinValue(minValue);
		numberPicker.setMaxValue(maxValue);
		numberPicker.setValue(curValue);
		numberPicker.setOnValueChangedListener(this);

		b.setView(v);
		return b.create();

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

	@Override
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
		curValue = newVal;

	}
}
