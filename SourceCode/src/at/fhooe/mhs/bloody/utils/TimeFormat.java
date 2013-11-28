/**
 * 
 */
package at.fhooe.mhs.bloody.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Patrick Hutflesz
 * 
 */
public class TimeFormat extends android.text.format.Time implements Serializable {

	private static final long serialVersionUID = -7156339510066186149L;
	private int hour, minute;

	public TimeFormat(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
		set();
	}

	public int getHour() {
		return hour;
	}

	public TimeFormat setHour(int hour) {
		this.hour = hour;
		set();
		return this;
	}

	public int getMinute() {
		return minute;
	}

	public TimeFormat setMinute(int minute) {
		this.minute = minute;
		set();
		return this;
	}

	public TimeFormat set(TimeFormat other) {
		this.hour = other.hour;
		this.minute = other.minute;
		set();
		return this;
	}

	public boolean isValid() {
		return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
	}

	private void set() {
		set(0, minute, hour, 1, 1, 1970);
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(hour);
		out.writeInt(minute);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		hour = in.readInt();
		minute = in.readInt();
		set();
	}
}
