/**
 * 
 */
package at.fhooe.mhs.bloody.locationservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

/**
 * @author Patrick Hutflesz
 * 
 */
public class GPSService extends Service implements LocationListener {

	private static GPSService instance;

	public static GPSService getInstance(Context context) {
		if (instance == null) {
			instance = new GPSService(context);
		}
		return instance;
	}

	private Context context;
	private GPSListener gpsListener;

	private boolean isGPSEnabled;
	private boolean isNetworkEnabled;
	private boolean canGetLocation;
	private boolean hasValidLocation;

	private Location location;
	private double latitude;
	private double longitude;
	private String locationString;

	// The minimum distance to change Updates in meters
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;

	// The minimum time between updates in milliseconds
	private static final long MIN_TIME_BW_UPDATES = 10000;

	// Declaring a Location Manager
	private LocationManager locationManager;

	public GPSService(Context context) {
		this.context = context;
		this.canGetLocation = false;
		getLocation();
	}

	public Location getLocation() {
		stopUsingGPS();
		try {
			locationManager = (LocationManager) context
					.getSystemService(LOCATION_SERVICE);
			// getting GPS status
			isGPSEnabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);
			// getting network status
			isNetworkEnabled = locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (isGPSEnabled || isNetworkEnabled) {
				this.canGetLocation = true;
				String provider = "";
				if (isNetworkEnabled) {
					// Log.d("GPSService", "Network enabled");
					provider = LocationManager.NETWORK_PROVIDER;
				} else {
					// Log.d("GPSService", "GPS enabled");
					provider = LocationManager.GPS_PROVIDER;
				}

				location = locationManager.getLastKnownLocation(provider);
				if (location == null) {
					locationManager.requestLocationUpdates(provider,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
				} else {
					onLocationChanged(location);
				}
			} else {
				if (gpsListener != null) {
					gpsListener.onLocationReceived(false, Double.MIN_VALUE,
							Double.MIN_VALUE);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return location;
	}

	/**
	 * Stop using GPS listener Calling this function will stop using GPS in your
	 * app
	 * */
	public void stopUsingGPS() {
		if (locationManager != null) {
			locationManager.removeUpdates(this);
		}
	}

	/**
	 * Function to get latitude
	 * */
	public double getLatitude() {
		if (location != null) {
			latitude = location.getLatitude();
		}

		return latitude;
	}

	/**
	 * Function to get longitude
	 * */
	public double getLongitude() {
		if (location != null) {
			longitude = location.getLongitude();
		}

		return longitude;
	}

	public String getLocationString() {
		return locationString;
	}

	/**
	 * Function to check GPS/wifi enabled
	 * 
	 * @return boolean
	 * */
	public boolean canGetLocation() {
		return this.canGetLocation;
	}

	/**
	 * Function to show settings alert dialog On pressing Settings button will
	 * lauch Settings Options
	 * */
	public void showSettingsAlert() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

		// Setting Dialog Title
		alertDialog.setTitle("GPS is settings");
		// Setting Dialog Message
		alertDialog
				.setMessage("GPS is not enabled. Do you want to go to settings menu?");

		// On pressing Settings button
		alertDialog.setPositiveButton("Settings",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(
								Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						context.startActivity(intent);
					}
				});

		// on pressing cancel button
		alertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		// Showing Alert Message
		alertDialog.show();
	}

	public boolean hasValidLocation() {
		return hasValidLocation;
	}

	@Override
	public void onLocationChanged(Location location) {
		this.hasValidLocation = true;
		latitude = location.getLatitude();
		longitude = location.getLongitude();
		Log.d("GPSService", "lat: " + latitude + ", lon: " + longitude);
		Geocoder geocoder = new Geocoder(context);
		List<Address> addresses = new ArrayList<Address>();
		try {
			addresses = geocoder.getFromLocation(latitude, longitude, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (addresses.size() > 0) {
			Address address = addresses.get(0);
			locationString = address.getSubAdminArea() + ", "
					+ address.getCountryCode();
		}

		if (gpsListener != null) {
			gpsListener.onLocationReceived(true, latitude, longitude);
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	public void setGPSListener(GPSListener gpsListener) {
		this.gpsListener = gpsListener;
	}
}