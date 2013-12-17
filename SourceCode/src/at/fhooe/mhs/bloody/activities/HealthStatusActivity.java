/**
 * 
 */
package at.fhooe.mhs.bloody.activities;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.measurementdata.Measurement;

/**
 * @author Elisabeth
 *
 */
public class HealthStatusActivity extends Activity{

	private double healthRating;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health_status);

		Measurement m = (Measurement) getIntent().getSerializableExtra(MeasurementActivity.EXTRA_MEAS);

		String fileName = "healthevaluation.fcl";
        FIS fis = FIS.load(fileName,true);

        // Error while loading?
        if( fis == null ) { 
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        FunctionBlock functionBlock = fis.getFunctionBlock("healthevaluation");
        // Show 
        JFuzzyChart.get().chart(functionBlock);

        // Set inputs
        fis.setVariable("diastolicPressure", m.getDiastolic());
        fis.setVariable("systolicPressure", m.getSystolic());
        fis.setVariable("bmi", m.getHeartRate());

        // Evaluate
        fis.evaluate();

        // Show output variable's chart
        Variable health = functionBlock.getVariable("health");
        healthRating = health.getValue();
        System.out.println(health.getValue());
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
