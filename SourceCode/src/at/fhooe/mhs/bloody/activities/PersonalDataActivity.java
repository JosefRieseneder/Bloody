package at.fhooe.mhs.bloody.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ViewFlipper;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.fragments.DateOfBirthFragment;
import at.fhooe.mhs.bloody.fragments.GenderFragment;
import at.fhooe.mhs.bloody.fragments.IDContactFragment;
import at.fhooe.mhs.bloody.locationservice.GPSService;

public class PersonalDataActivity extends Activity {

	private enum State {
		WELCOME, ID_CONTACT, DATE_OF_BIRTH, GENDER, WEIGHT, HEIGHT, ADDRESS
	}

	private State state;
	private ViewFlipper viewFlipper;
	private IDContactFragment idContactFragment;
	private DateOfBirthFragment dateOfBirthFragment;
	private GenderFragment genderFragment;

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
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipperPD);
		idContactFragment = (IDContactFragment) getFragmentManager()
				.findFragmentById(R.id.fragment_id_email);
		dateOfBirthFragment = (DateOfBirthFragment) getFragmentManager()
				.findFragmentById(R.id.fragment_date_of_birth);
		genderFragment = (GenderFragment) getFragmentManager()
				.findFragmentById(R.id.fragment_gender);
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
			viewFlipper.setDisplayedChild(1);
			break;
		case ID_CONTACT:
			// if (idContactFragment.isFilled()) {
			state = State.DATE_OF_BIRTH;
			viewFlipper.setDisplayedChild(2);
			// } else {
			// (Toast.makeText(this, "Enter values", Toast.LENGTH_LONG))
			// .show();
			// }
			break;
		case DATE_OF_BIRTH:
			// if (dateOfBirthFragment.isFilled()) {
			state = State.GENDER;
			viewFlipper.setDisplayedChild(3);
			// } else {
			// (Toast.makeText(this, "Enter values", Toast.LENGTH_LONG))
			// .show();
			// }
			break;
		case GENDER:
			state = State.WEIGHT;
			viewFlipper.setDisplayedChild(4);
			break;
		case WEIGHT:
			state = State.HEIGHT;
			viewFlipper.setDisplayedChild(5);
			break;
		case HEIGHT:
			state = State.ADDRESS;
			viewFlipper.setDisplayedChild(6);
			break;
		case ADDRESS:
			finish();
			break;
		}
	}

}
