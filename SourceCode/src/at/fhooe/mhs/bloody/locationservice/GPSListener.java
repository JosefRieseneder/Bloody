package at.fhooe.mhs.bloody.locationservice;


public interface GPSListener {
	
	public void onLocationReceived(boolean newPosition, double latitude, double longitude);
	
	public void onPersonalLocationReceived(double latitude, double longitude);
}
