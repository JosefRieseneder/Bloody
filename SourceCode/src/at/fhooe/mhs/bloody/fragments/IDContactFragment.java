/**
 * 
 */
package at.fhooe.mhs.bloody.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import at.fhooe.mhs.bloody.R;

/**
 * @author Elisabeth
 *
 */
public class IDContactFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_id_and_contact, container, false);
	}
	
	public String getInsuranceNumber(){
		return ((EditText) getView().findViewById(R.id.et_nat_ins_number)).getText().toString();
	}
	
	public String getEmail(){
		return ((EditText) getView().findViewById(R.id.et_doc_email)).getText().toString();
	}
	
	public boolean isFilled(){
		return ! getInsuranceNumber().equals("") && !getEmail().equals("");
	}

}
