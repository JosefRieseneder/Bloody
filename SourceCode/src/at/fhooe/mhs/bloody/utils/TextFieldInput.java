/**
 * 
 */
package at.fhooe.mhs.bloody.utils;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import android.widget.TextView;

/**
 * @author Elisabeth
 * 
 */
public class TextFieldInput
{

	public static void setDateText(TextView tv, int year, int month, int day)
	{
		if (tv == null)
			return;
		GregorianCalendar date = new GregorianCalendar(year, month, day);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		String dateString = sdf.format(date.getTime());
		tv.setText(dateString);
	}

	public static void setTimeText(TextView tv, int hour, int minute)
	{
		if (tv == null)
			return;
		GregorianCalendar date = new GregorianCalendar(0, 0, 0, hour, minute, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String dateString = sdf.format(date.getTime());
		tv.setText(dateString);
	}

}
