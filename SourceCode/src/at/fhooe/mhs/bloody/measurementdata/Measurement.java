/**
 * 
 */
package at.fhooe.mhs.bloody.measurementdata;

/**
 * @author Patrick Hutflesz
 * 
 */
public class Measurement {

	/**
	 * Heart rate measurement in bpm
	 */
	private int heartRate;
	private int systolic, diastolic;
	/**
	 * Weight in kg.
	 */
	private int weight;
	/**
	 * Height in cm.
	 */
	private int height;
	private double latitude, longitude;
	private String location;
	private int age;

	public Measurement(int heartRate, int systolic, int diastolic,
			int weight, int height, double latitude, double longitude,
			String location, int age) {
		this.heartRate = heartRate;
		this.systolic = systolic;
		this.diastolic = diastolic;
		this.weight = weight;
		this.height = height;
		this.latitude = latitude;
		this.longitude = longitude;
		this.location = location;
		this.age = age;
	}

	public int getDiastolic() {
		return diastolic;
	}

	public int getSystolic() {
		return systolic;
	}

	/**
	 * @return the heart rate in beats per minute.
	 */
	public int getHeartRate() {
		return heartRate;
	}

	public int getHeight() {
		return height;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getLocation() {
		return location;
	}

	public int getWeight() {
		return weight;
	}

	public int getAge() {
		return age;
	}
}