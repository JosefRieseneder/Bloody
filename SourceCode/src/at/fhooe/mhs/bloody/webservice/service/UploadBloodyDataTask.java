package at.fhooe.mhs.bloody.webservice.service;

import java.util.List;

import android.os.AsyncTask;
import at.fhooe.mhs.bloody.webservice.data.BloodyData;

import com.google.gson.Gson;

public class UploadBloodyDataTask extends AsyncTask<List<BloodyData>, Void, Boolean> {

	@Override
	protected Boolean doInBackground(List<BloodyData>... params) {

		Gson gson = new Gson();
		String json = gson.toJson(params[0]);

		return ((new BloodyHttpClient()).postBloodyData(json));
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);

		// TODO: forward result
	}
}
