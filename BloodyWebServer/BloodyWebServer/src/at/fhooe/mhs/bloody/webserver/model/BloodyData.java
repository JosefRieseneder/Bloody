package at.fhooe.mhs.bloody.webserver.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BloodyData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date timestamp;
	private double locationLat;
	private double locationLon;
	private double bloodPressureSystolic;
	private double bloodPressureDiastolic;
	private double heartRate;

	public BloodyData() {
	}

	public BloodyData(double locationLat, double locationLon,
			double bloodPressureSystolic, double bloodPressureDiastolic,
			double heartRate) {
		this.locationLat = locationLat;
		this.locationLon = locationLon;
		this.bloodPressureSystolic = bloodPressureSystolic;
		this.bloodPressureDiastolic = bloodPressureDiastolic;
		this.heartRate = heartRate;
	}

	public Long getId() {
		return id;
	}

	public void setTimestamp() {
		this.timestamp = Calendar.getInstance(
				TimeZone.getTimeZone("America/Los_Angeles"), Locale.US)
				.getTime();
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public double getLocationLat() {
		return locationLat;
	}

	public double getLocationLon() {
		return locationLon;
	}

	public double getBloodPressureSystolic() {
		return bloodPressureSystolic;
	}

	public double getBloodPressureDiastolic() {
		return bloodPressureDiastolic;
	}

	public double getHeartRate() {
		return heartRate;
	}

	@Override
	public String toString() {
		return "BloodyData [timestamp=" + timestamp.toString() + "lat="
				+ locationLat + ", lon=" + locationLon + ", systolic="
				+ bloodPressureSystolic + ", diastolic="
				+ bloodPressureDiastolic + ", heartrate=" + heartRate + "]";
	}
}
