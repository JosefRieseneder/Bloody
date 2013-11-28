package at.fhooe.mhs.bloody;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import at.fhooe.mhs.bloody.personalData.PersonalData;
import at.fhooe.mhs.bloody.personalData.PersonalDataActivity;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initButtons();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
		
	}

	public void initButtons() {
		((Button) findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openBloodPressureActivity();
				
			}
		});
		
	((Button) findViewById(R.id.button2)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openPersonalDAtaActivity();
			}
		});
	}
	
	private void openBloodPressureActivity(){
		
	}
	
	private void openPersonalDAtaActivity(){
		startActivity(new Intent(this, PersonalDataActivity.class));
	}


	

}
