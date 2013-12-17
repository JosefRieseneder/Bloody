package at.fhooe.mhs.bloody.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.webservice.data.BloodyData;
import at.fhooe.mhs.bloody.webservice.listener.BloodyDataListener;
import at.fhooe.mhs.bloody.webservice.service.DownloadBloodyDataTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity implements BloodyDataListener {

	private static final LatLng HAGENBERG = new LatLng(48.363889, 14.519444);
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		// Move the camera instantly to target with a zoom of 15.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAGENBERG, 15));
		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(1), 2000, null);

		// get bloody data
		DownloadBloodyDataTask task = new DownloadBloodyDataTask();
		task.setBloodyDataListener(this);
		task.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	@Override
	public void onBloodyDataReceived(List<BloodyData> bloodyData) {
		for (BloodyData data : bloodyData) {
			map.addMarker(new MarkerOptions()
					.position(
							new LatLng(data.getLocationLat(), data
									.getLocationLon()))
					.title("Blood pressure: "
							+ data.getBloodPressureDiastolic() + " / "
							+ data.getBloodPressureSystolic())
					.snippet("Heart rate: " + data.getHeartRate())
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.mapmarker)));
		}
	}

}
