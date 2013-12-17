package at.fhooe.mhs.bloody.webservice.service;

import java.util.List;

import android.os.AsyncTask;
import at.fhooe.mhs.bloody.webservice.data.BloodyData;
import at.fhooe.mhs.bloody.webservice.listener.BloodyDataListener;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class DownloadBloodyDataTask extends
		AsyncTask<Void, Void, List<BloodyData>> {

	private BloodyDataListener bloodyDataListener;

	@Override
	protected List<BloodyData> doInBackground(Void... params) {
		List<BloodyData> bloodyData = null;
		String data = ((new BloodyHttpClient()).getBloodyData());

		try {
			Gson gson = new Gson();
			bloodyData = gson.fromJson(data, new TypeToken<List<BloodyData>>() {
			}.getType());
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return bloodyData;
	}

	@Override
	protected void onPostExecute(List<BloodyData> bloodyData) {
		super.onPostExecute(bloodyData);

		if (bloodyDataListener != null) {
			bloodyDataListener.onBloodyDataReceived(bloodyData);
		}
	}

	public void setBloodyDataListener(BloodyDataListener bloodyDataListener) {
		this.bloodyDataListener = bloodyDataListener;
	}
}
