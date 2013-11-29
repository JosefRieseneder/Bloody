package at.fhooe.mhs.bloody.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ViewSwitcher;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.fragments.IDContactFragment;

public class PersonalDataActivity extends Activity{
	

	
	private enum State{
		WELCOME, ID_CONTACT
	}
	
	private State state;
	private ViewSwitcher viewSwitcher;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_data);		
		state = State.WELCOME;
		viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcherPD);
		initButtons();
		
	}
	
	public void initButtons() {
		((Button) findViewById(R.id.buttonNext)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switchStateAndDoAction();
			}
		});
	}
	
	public void switchStateAndDoAction(){
		switch (state) {
		case WELCOME:
			state = State.ID_CONTACT;
			viewSwitcher.setDisplayedChild(1);
			break;
		case ID_CONTACT:
			state = State.WELCOME;
			viewSwitcher.setDisplayedChild(0);
			break;
		}
	}
	


}
