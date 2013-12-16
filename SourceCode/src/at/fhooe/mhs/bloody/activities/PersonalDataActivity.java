package at.fhooe.mhs.bloody.activities;

import android.R.id;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.fragments.IDContactFragment;
import at.fhooe.mhs.bloody.locationservice.GPSService;

public class PersonalDataActivity extends Activity {

	private enum State {
		WELCOME, ID_CONTACT
	}

	private State state;
	private ViewSwitcher viewSwitcher;
	private IDContactFragment idContactFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_data);
		state = State.WELCOME;
		initSwitcherAndViews();
		initButtons();

		System.out.println(GPSService.getInstance(this).hasValidLocation());
		System.out.println(GPSService.getInstance(this).getLatitude() + ", "
				+ GPSService.getInstance(this).getLongitude());
	}

	private void initSwitcherAndViews() {
		viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcherPD);
		idContactFragment = (IDContactFragment) getFragmentManager()
				.findFragmentById(R.id.fragment_id_email);
	}

	public void initButtons() {
		((Button) findViewById(R.id.buttonNext))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						switchStateAndDoAction();
					}
				});
	}

	public void switchStateAndDoAction() {
		switch (state) {
		case WELCOME:
			state = State.ID_CONTACT;
			viewSwitcher.setDisplayedChild(1);
			break;
		case ID_CONTACT:
			if (idContactFragment.isFilled()) {
				state = State.WELCOME;
				viewSwitcher.setDisplayedChild(0);
				(Toast.makeText(this, "Todo save to Personal Data", Toast.LENGTH_LONG)).show();
			}else{
				(Toast.makeText(this, "Enter values", Toast.LENGTH_LONG)).show();
			}
			break;
		}
	}

}
