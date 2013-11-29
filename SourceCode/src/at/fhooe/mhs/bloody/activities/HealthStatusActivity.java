/**
 * 
 */
package at.fhooe.mhs.bloody.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import at.fhooe.mhs.bloody.R;

/**
 * @author Elisabeth
 *
 */
public class HealthStatusActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health_status);
	}
	
	public void initButtons() {
		((Button) findViewById(R.id.button_share)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				(Toast.makeText(HealthStatusActivity.this, "not impl yet", Toast.LENGTH_SHORT)).show();
			}
		});
		
	((Button) findViewById(R.id.button_timeline)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(HealthStatusActivity.this, TimelineActivity.class));
			}
		});
	}
}
