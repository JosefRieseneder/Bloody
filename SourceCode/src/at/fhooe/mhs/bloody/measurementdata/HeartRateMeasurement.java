/**
 * 
 */
package at.fhooe.mhs.bloody.measurementdata;

/**
 * @author Patrick Hutflesz
 *
 */
public class HeartRateMeasurement {

	/**
	 * Heart rate measurement in bpm
	 */
	private double rate;

	/**
	 * @param rate the heart rate in beats per minute
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * @return the heart rate in beats per minute.
	 */
	public double getRate() {
		return rate;
	}
}
