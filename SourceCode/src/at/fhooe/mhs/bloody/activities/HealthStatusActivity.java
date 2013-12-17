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
import android.widget.ImageView;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.measurementdata.Measurement;

/**
 * @author Elisabeth
 * 
 */
public class HealthStatusActivity extends Activity {

	private double healthRating;		//-35 - + 35
	private int VERY_GOOD = 21; // health rating till this value very GOOD
	private int GOOD = 7; //health rating till this value GOOD
	private int MEDIUM = -7; //health rating till this value MEDIUM
	private int BAD = -21;//health rating till this value BAD
	//all values belwo -21 VERY BAD
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
	
	private void setHealthStatusImage(){
	ImageView healthImage = (ImageView)findViewById(R.id.imageView1);
		if(healthRating>=VERY_GOOD){
			healthImage.setImageDrawable(getResources().getDrawable(R.drawable.status_super));
		}else if(healthRating>=GOOD){
			healthImage.setImageDrawable(getResources().getDrawable(R.drawable.status_normal));
		}else if(healthRating>=MEDIUM){
			healthImage.setImageDrawable(getResources().getDrawable(R.drawable.status_medium));
		}else if(healthRating>=BAD){
			healthImage.setImageDrawable(getResources().getDrawable(R.drawable.status_bad));
		}else {
			healthImage.setImageDrawable(getResources().getDrawable(R.drawable.status_very_bad));
		}
	}
}
