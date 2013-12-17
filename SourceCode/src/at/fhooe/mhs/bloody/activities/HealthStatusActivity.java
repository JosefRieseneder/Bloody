/**
 * 
 */
package at.fhooe.mhs.bloody.activities;

import java.io.IOException;
import java.io.InputStream;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.measurementdata.Measurement;

/**
 * @author Elisabeth
 * 
 */
public class HealthStatusActivity extends Activity {

	private double healthRating;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health_status);

		Measurement m = (Measurement) getIntent().getSerializableExtra(MeasurementActivity.EXTRA_MEAS);

		String fileName = "healthevaluation.fcl";
		AssetManager manager = getAssets();

		try {
			InputStream is = manager.open(fileName);
			FIS fis = FIS.load(is, false);
			is.close();

	        // Error while loading?
	        if(fis == null ) { 
	            System.err.println("Can't load file: '" + fileName + "'");
	            return;
	        }
	
	        FunctionBlock functionBlock = fis.getFunctionBlock("healthevaluation");
	
	        // Set inputs
	        System.out.println(m.getDiastolic());
	        System.out.println(m.getSystolic());
	        fis.setVariable("diastolicPressure", m.getDiastolic());
	        fis.setVariable("systolicPressure", m.getSystolic());
	        double height = (double) m.getHeight() / 100.0;
	        fis.setVariable("bmi", (double) m.getWeight() / (height * height));
	
	        // Evaluate
	        fis.evaluate();

	        // Show output variable's chart
	        Variable health = functionBlock.getVariable("health");
	        healthRating = health.getValue();
	        System.out.println(health.getValue());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void initButtons() {
		((Button) findViewById(R.id.button_share))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(HealthStatusActivity.this,
								MapActivity.class));
					}
				});

		((Button) findViewById(R.id.button_timeline))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(HealthStatusActivity.this,
								TimelineActivity.class));
					}
				});
	}
}
