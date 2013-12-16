package at.fhooe.mhs.bloody.webservice.data;

public class BloodyData {

	private double locationLat;
	private double locationLon;
	private double bloodPressureSystolic;
	private double bloodPressureDiastolic;
	private double heartRate;

	public BloodyData(double locationLat, double locationLon,
			double bloodPressureSystolic, double bloodPressureDiastolic,
			double heartRate) {
		this.locationLat = locationLat;
		this.locationLon = locationLon;
		this.bloodPressureSystolic = bloodPressureSystolic;
		this.bloodPressureDiastolic = bloodPressureDiastolic;
		this.heartRate = heartRate;
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
		return "BloodyData [lat=" + locationLat + ", lon=" + locationLon
				+ ", systolic=" + bloodPressureSystolic + ", diastolic="
				+ bloodPressureDiastolic + ", heartrate=" + heartRate + "]";
	}
}
