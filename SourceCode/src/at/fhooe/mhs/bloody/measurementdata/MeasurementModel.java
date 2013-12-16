/**
 * 
 */
package at.fhooe.mhs.bloody.measurementdata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.content.Context;

/**
 * @author Patrick Hutflesz
 *
 */
public class MeasurementModel implements Serializable {

	private static final long serialVersionUID = -8020701936888186372L;
	private static final String FILE_NAME = "measurementmodel";

	private static MeasurementModel instance;

	public static synchronized MeasurementModel getInstance(Context context) {
		if (context == null) {
			throw new NullPointerException("Context for MeasurementModel may not be null!");
		}
		if (instance == null) {
			// First invocation, create a new instance
			boolean exception = false;
			try {
				// Try to read it from internal storage
				FileInputStream fis = context.openFileInput(FILE_NAME);
				ObjectInputStream ois = new ObjectInputStream(fis);
				instance = (MeasurementModel) ois.readObject();
				ois.close();
				fis.close();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
				exception = true;
			}
			catch (StreamCorruptedException e) {
				e.printStackTrace();
				exception = true;
			}
			catch (IOException e) {
				e.printStackTrace();
				exception = true;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				exception = true;
			}
			if (exception) {
				// Not able to read from internal storage, create empty instance
				instance = new MeasurementModel();
			}
		}

		return instance;
	}

	public static synchronized void save(Context context, MeasurementModel model) {
		try {
			context.deleteFile(FILE_NAME);
			FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(model);
			oos.close();
			fos.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<Measurement> measurements;

	public MeasurementModel() {
		measurements = new ArrayList<Measurement>();
	}

	public void addMeasurement(Measurement m) {
		measurements.add(m);
	}

	public int size() {
		return measurements.size();
	}

	public Measurement get(int index) {
		return measurements.get(index);
	}
}
