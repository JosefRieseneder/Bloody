/**
 * 
 */
package at.fhooe.mhs.bloody.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import at.fhooe.mhs.bloody.R;

/**
 * @author Elisabeth
 *
 */
public class WelcomeFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_welcome, container, false);
	}
	


}