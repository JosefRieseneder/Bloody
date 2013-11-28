/**
 * 
 */

package at.fhooe.mhs.bloody.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

/**
 * @author Patrick Hutflesz
 * 
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

	private TimePickerListener listener;
	private int id;
	private int hour;
	private int minute;

	public void doSettings(TimePickerListener listener, int id, int hour, int minute) {
		this.listener = listener;
		this.id = id;
		this.hour = hour;
		this.minute = minute;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
	}

	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		if (listener != null) {
			listener.onTimeSet(id, hourOfDay, minute);
		}
	}
}
