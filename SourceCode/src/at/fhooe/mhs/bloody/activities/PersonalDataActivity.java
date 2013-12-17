package at.fhooe.mhs.bloody.activities;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.R.string;
import at.fhooe.mhs.bloody.fragments.AddressFragment;
import at.fhooe.mhs.bloody.fragments.DateOfBirthFragment;
import at.fhooe.mhs.bloody.fragments.GenderFragment;
import at.fhooe.mhs.bloody.fragments.HeightFragment;
import at.fhooe.mhs.bloody.fragments.IDContactFragment;
import at.fhooe.mhs.bloody.fragments.WeightFragment;
import at.fhooe.mhs.bloody.locationservice.GPSService;
import at.fhooe.mhs.bloody.locationservice.GeoCoderService;
import at.fhooe.mhs.bloody.measurementdata.Measurement;
import at.fhooe.mhs.bloody.measurementdata.MeasurementModel;
import at.fhooe.mhs.bloody.utils.AgeCalculator;

public class PersonalDataActivity extends Activity {

	private enum State {
		WELCOME, ID_CONTACT, DATE_OF_BIRTH, GENDER, WEIGHT, HEIGHT, ADDRESS
	}

	private State state;
	private ViewFlipper viewFlipper;
	private IDContactFragment idContactFragment;
	private DateOfBirthFragment dateOfBirthFragment;
	private GenderFragment genderFragment;
	private WeightFragment weightFragment;
	private MeasurementModel measModel;
	private HeightFragment heightFragment;
	private AddressFragment addressFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_data);
		state = State.WELCOME;
		initSwitcherAndViews();
		initButtons();

		measModel = MeasurementModel.getInstance(this);

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
		weightFragment = (WeightFragment) getFragmentManager()
				.findFragmentById(R.id.fragment_weight);
		heightFragment = (HeightFragment) getFragmentManager()
				.findFragmentById(R.id.fragment_height);
		addressFragment = (AddressFragment) getFragmentManager()
				.findFragmentById(R.id.fragment_address);
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
		
		//scroll 
		ScrollView scrV = (ScrollView) findViewById(R.id.scrollViewPD);
		scrV.fullScroll(ScrollView.FOCUS_UP);
		
		switch (state) {
		case WELCOME:
			state = State.ID_CONTACT;
			viewFlipper.setDisplayedChild(1);
			break;
		case ID_CONTACT:
			if (idContactFragment.isFilled()) {
				state = State.DATE_OF_BIRTH;
				viewFlipper.setDisplayedChild(2);
			} else {
				(Toast.makeText(this,
						getResources().getString(string.enter_values),
						Toast.LENGTH_LONG)).show();
			}
			break;
		case DATE_OF_BIRTH:
			if (dateOfBirthFragment.isFilled()) {
				state = State.GENDER;
				viewFlipper.setDisplayedChild(3);
			} else {
				(Toast.makeText(this,
						getResources().getString(string.enter_values),
						Toast.LENGTH_LONG)).show();
			}
			break;
		case GENDER:
			if (genderFragment.isFilled()) {
				state = State.WEIGHT;
				viewFlipper.setDisplayedChild(4);
			} else {
				(Toast.makeText(this,
						getResources().getString(string.enter_values),
						Toast.LENGTH_LONG)).show();
			}
			break;
		case WEIGHT:
			if (weightFragment.isFilled()) {
				state = State.HEIGHT;
				viewFlipper.setDisplayedChild(5);
			} else {
				(Toast.makeText(this,
						getResources().getString(string.enter_values),
						Toast.LENGTH_LONG)).show();
			}
			break;
		case HEIGHT:
			if (heightFragment.isFilled()) {
				state = State.ADDRESS;
				viewFlipper.setDisplayedChild(6);
			} else {
				(Toast.makeText(this,
						getResources().getString(string.enter_values),
						Toast.LENGTH_LONG)).show();
			}
			break;
		case ADDRESS:
			if (addressFragment.isFilled()) {
				setPersonalData();
				finish();
			} else {
				(Toast.makeText(this,
						getResources().getString(string.enter_values),
						Toast.LENGTH_LONG)).show();
			}
			break;

		}
	}

	private void setPersonalData() {
		measModel.getPersonalData().setId(idContactFragment.getInsuranceNumber());
		measModel.getPersonalData().setContactEmail(idContactFragment.getEmail());
		measModel.getPersonalData().setDateOfBirth(dateOfBirthFragment.getDateOfBirth());
		measModel.getPersonalData().setAge(AgeCalculator.getAge(dateOfBirthFragment.getDateOfBirth()));
		
		measModel.getPersonalData().setGender(genderFragment.getGenderValue());
		measModel.getPersonalData().setWeight(weightFragment.getWeight());
		measModel.getPersonalData().setHeight(heightFragment.getHeight());
		
		measModel.getPersonalData().setLocation(addressFragment.getAddress());
		double[] latLong = GeoCoderService.getLocationFromString(this, addressFragment.getAddress());
		measModel.getPersonalData().setLocationLat(latLong[0]);
		measModel.getPersonalData().setLocationLon(latLong[1]);
		
	}

}
