package at.fhooe.mhs.bloody.personalData;

import android.app.Activity;
import android.os.Bundle;
import at.fhooe.mhs.bloody.R;

public class PersonalDataActivity extends Activity{
	
	private enum State{
		WELCOME, ID_CONTACT
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_data);
	}

}
