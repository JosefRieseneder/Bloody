/**
 * 
 */
package at.fhooe.mhs.bloody.measurementdata;

/**
 * @author Patrick Hutflesz
 * 
 */
public class BloodPressureMeasurement {

	private double systolic, diastolic;

	public double getDiastolic() {
		return diastolic;
	}

	public double getSystolic() {
		return systolic;
	}

	public void setDiastolic(double diastolic) {
		this.diastolic = diastolic;
	}

	public void setSystolic(double systolic) {
		this.systolic = systolic;
	}
}
