package at.fhooe.mhs.bloody.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.measurementdata.Measurement;
import at.fhooe.mhs.bloody.measurementdata.MeasurementModel;
import at.fhooe.mhs.bloody.webservice.data.BloodyData;
import at.fhooe.mhs.bloody.webservice.listener.BloodyDataListener;
import at.fhooe.mhs.bloody.webservice.service.DownloadBloodyDataTask;
import at.fhooe.mhs.bloody.webservice.service.UploadBloodyDataTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity implements BloodyDataListener {

	private static final LatLng HAGENBERG = new LatLng(48.363889, 14.519444);
	private GoogleMap map;
	private Measurement lastMeasurement;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		// get last measurement
		lastMeasurement = MeasurementModel.getInstance(this).getLast();

		// init. map
		setUpMap();

		// upload last measurement
		if (lastMeasurement != null) {
			ArrayList<BloodyData> bloodyData = new ArrayList<BloodyData>();
			bloodyData.add(new BloodyData(lastMeasurement.getLatitude(),
					lastMeasurement.getLongitude(), lastMeasurement
							.getSystolic(), lastMeasurement.getDiastolic(),
					lastMeasurement.getHeartRate()));
			UploadBloodyDataTask upTask = new UploadBloodyDataTask();
			upTask.setBloodyDataListener(this);
			upTask.execute(bloodyData);
		}else{
			// no measurement, just download others
			onBloodyDataUploaded();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	private void setUpMap() {
		if (map == null) {
			// get home pos
			LatLng homePos = HAGENBERG;
			if (lastMeasurement != null) {
				homePos = new LatLng(lastMeasurement.getLatitude(),
						lastMeasurement.getLongitude());
			}

			// init. map
			map = ((MapFragment) getFragmentManager()
					.findFragmentById(R.id.map)).getMap();

			// Move the camera instantly to target with a zoom of 15.
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(homePos, 15));
			// Zoom in, animating the camera.
			map.animateCamera(CameraUpdateFactory.zoomTo(1), 2000, null);
		}
	}

	@Override
	public void onBloodyDataUploaded() {
		// get bloody data
		DownloadBloodyDataTask downTask = new DownloadBloodyDataTask();
		downTask.setBloodyDataListener(this);
		downTask.execute();
	}

	@Override
	public void onBloodyDataReceived(List<BloodyData> bloodyData) {
		setUpMap();

		View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.custom_marker_layout, null);
		TextView tvMapSystolic = (TextView) marker
				.findViewById(R.id.tv_mapsystolic);
		TextView tvMapDiastolic = (TextView) marker
				.findViewById(R.id.tv_mapdiastolic);

		for (BloodyData data : bloodyData) {
			tvMapSystolic.setText("" + data.getBloodPressureSystolic());
			tvMapDiastolic.setText("" + data.getBloodPressureDiastolic());

			map.addMarker(new MarkerOptions()
					.position(
							new LatLng(data.getLocationLat(), data
									.getLocationLon()))
					.title("Blood pressure: "
							+ data.getBloodPressureDiastolic() + " / "
							+ data.getBloodPressureSystolic())
					.snippet("Heart rate: " + data.getHeartRate())
					.icon(BitmapDescriptorFactory
							.fromBitmap(createDrawableFromView(this, marker))));
		}
	}

	private static Bitmap createDrawableFromView(Context context, View view) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
		view.layout(0, 0, displayMetrics.widthPixels,
				displayMetrics.heightPixels);
		view.buildDrawingCache();
		Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
				view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmap);
		view.draw(canvas);

		return bitmap;
	}
}
